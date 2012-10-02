package shadow.geometry.geometries;

import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;
import shadow.system.SFException;

public class SFDerivedTexCoord extends SFMeshGeometry{

	private SFMeshGeometry geometry=new SFMeshGeometry();
	private SFDerivedTexCoordFunctionuv derivedTexCoordFunction;
	
	public SFDerivedTexCoord() {
		// TODO Auto-generated constructor stub
	}
	
	public void addMeshGeometry(SFMeshGeometry geometry) throws SFException {
		this.geometry=geometry;
	}
	
	public void setDerivedTexCoordFunction(
			SFDerivedTexCoordFunctionuv derivedTexCoordFunction) {
		this.derivedTexCoordFunction = derivedTexCoordFunction;
	}

	@Override
	public void compile() {
		super.compile();
		
		//TODO: verify this have already 1 element.
		
		int positionGridIndex=0;
		int normalGridIndex=0;
		
		SFPrimitive primitive=geometry.getPrimitive();
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			if(primitive.getBlock(gridIndex)==SFPrimitiveBlock.POSITION){
				positionGridIndex=gridIndex;
			}
			if(primitive.getBlock(gridIndex)==SFPrimitiveBlock.NORMAL){
				normalGridIndex=gridIndex;
			}
		}
		
		SFIndexRange rangePosition=geometry.getMesh().getPrimitiveDataRanges()[positionGridIndex];
		SFIndexRange rangeNormal=geometry.getMesh().getPrimitiveDataRanges()[normalGridIndex];
		SFArray<SFValuenf> positionData=geometry.getArray().getPrimitiveData(positionGridIndex);
		SFArray<SFValuenf> normalData=geometry.getArray().getPrimitiveData(normalGridIndex);
		SFArray<SFValuenf> data=getArray().getPrimitiveData(0);
		int dataIndex=data.generateElements(rangePosition.getSize());
		
		SFVertex3f positionValue=new SFVertex3f();
		SFVertex3f normalValue=new SFVertex3f();
		for (int i = 0; i < rangePosition.getSize(); i++) {
			positionData.getElement(i+rangePosition.getPosition(), positionValue);
			normalData.getElement(i+rangeNormal.getPosition(), normalValue);
			SFVertex2f texCoords=derivedTexCoordFunction.getTexCoord(positionValue.getX(),
					positionValue.getY(), positionValue.getZ(),normalValue.getX(),
					normalValue.getY(), normalValue.getZ());
			data.setElement(dataIndex+i, texCoords);
		}
		
		SFPrimitiveIndices geometryIndices=geometry.getArray().generateSample();
		SFPrimitiveIndices indices=getArray().generateSample();
		int indicesPosition=primitive.getIndicesPositions()[positionGridIndex];
		int indicesPositionSize=primitive.getIndicesSizes()[positionGridIndex];
		
		int texCoordIndicesPosition=getArray().generateElements(geometry.getMesh().getSize());
		//geometry.getFirstElement() must be zero..
		for (int i = geometry.getFirstElement(); i < geometry.getLastElement(); i++) {
			geometry.getArray().getElement(i, geometryIndices);
			for (int j = 0; j < indicesPositionSize; j++) {
				indices.getPrimitiveIndices()[j]=geometryIndices.getPrimitiveIndices()[indicesPosition+j];
			}
			getArray().setElement(i-geometry.getFirstElement()+texCoordIndicesPosition, indices);
		}
		
		setFirstElement(geometry.getFirstElement());
		setLastElement(geometry.getLastElement());
		getMesh().evaluateRanges();//TODO  should really avoid this.
	}
	
}
