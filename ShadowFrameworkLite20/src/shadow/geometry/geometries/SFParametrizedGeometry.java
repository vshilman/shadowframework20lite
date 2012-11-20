package shadow.geometry.geometries;

import java.util.Arrays;

import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.operational.grid.SFGridEngine;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;
import shadow.system.SFException;

public class SFParametrizedGeometry extends SFMeshGeometry{

	private SFMeshGeometry parametersGeometry;

	private SFSurfaceFunction[] functions;
	
	public SFParametrizedGeometry() {
		super();
	}
	
	public void addMeshGeometry(SFMeshGeometry geometry) throws SFException {
		parametersGeometry=geometry;
	}

	public SFParametrizedGeometry(SFMeshGeometry parametersGeometry){
		super();
		this.parametersGeometry=parametersGeometry;
	}
	
	public SFParametrizedGeometry(SFPrimitive primitive,SFMeshGeometry parametersGeometry){
		setPrimitive(primitive);
		this.parametersGeometry=parametersGeometry;
		functions=new SFSurfaceFunction[primitive.getBlocks().length];
	}
	
	@Override
	public void compile() {
		super.compile();
		
		parametersGeometry.init();
		
		//TODO: check parametersGeometry to be, effectively, a parameters Geometry
		//so, i think i should have all...
		
		SFArray<SFValuenf> parameters=parametersGeometry.getArray().getPrimitiveData(0);
		//parametersGeometry.getMesh().evaluateRanges();
		SFIndexRange range=parametersGeometry.getMesh().getPrimitiveDataRanges()[0];
		
		int[] deltaPosition=new int[getPrimitive().getGridsCount()];
		
		for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
			SFPrimitiveBlock block=getPrimitive().getBlock(gridIndex);
			SFSurfaceFunction function=this.functions[gridIndex];
			int position=function.extractParametrizedModel(parameters, range, getArray(), block, gridIndex);
			deltaPosition[gridIndex]=position-range.getPosition();
		}
		
		int elementsPosition=getArray().generateElements(parametersGeometry.getElementsCount());
		
		SFPrimitiveIndices indices=getArray().generateSample();
		SFPrimitiveIndices paramIndices=parametersGeometry.getArray().generateSample();
		
		for (int i = 0; i < parametersGeometry.getElementsCount(); i++) {
			parametersGeometry.getArray().getElement(i+parametersGeometry.getFirstElement(), paramIndices);
			
			for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
				int[] paramIndicesVec=paramIndices.getPrimitiveIndices();
				for (int j = 0; j < paramIndicesVec.length; j++) {
					indices.getPrimitiveIndices()[gridIndex*paramIndicesVec.length+j]=paramIndicesVec[j]+deltaPosition[gridIndex];	
				}
			}
			System.err.println(Arrays.toString(indices.getPrimitiveIndices()));
			getArray().setElement(i+elementsPosition, indices);
		}
		
		setFirstElement(elementsPosition);
		setLastElement(elementsPosition+parametersGeometry.getElementsCount());
		
		getMesh().evaluateRanges();
	}
	
	
	@Override
	public void update() {
		super.update();
		
		if(getArray()==null)
			return;
		
		for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
			SFArray<SFValuenf> parameters=parametersGeometry.getArray().getPrimitiveData(0);
			SFPrimitiveBlock block=getPrimitive().getBlock(gridIndex);
			SFSurfaceFunction function=this.functions[gridIndex];
			int position=getMesh().getPrimitiveDataRanges()[gridIndex].getPosition();
			SFIndexRange range=parametersGeometry.getMesh().getPrimitiveDataRanges()[0];
			function.updateParametrizedModel(position, parameters, range, getArray(), block, gridIndex);
		}
		SFGridEngine.correctValues(getMesh());
	}
	
	/**
	 * 
	 * @param block a Primitive Block to which this Surface Function is Assigned
	 * @param function
	 * @throws SFException when this SFQuadsSurfaceGeometry Primitive does not make use of the block
	 */
	public void setFunction(SFPrimitiveBlock block,SFSurfaceFunction function) throws SFException{
		SFPrimitiveBlock[] blocks=getPrimitive().getBlocks();
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i]==block){
				functions[i]=function;
				return;
			}
		}
		throw new SFException("Cannot assign a function to a QuadsSurfaceGeometry on the Primitive Block '"+block+"' because this QuadsSurfaceGeometry primitive does not use " +
				"the Primitive Block '"+block+"'");
	}
	
	public SFSurfaceFunction[] getFunctions() {
		return functions;
	}

	/**
	 * Set up the function to be used on TexCoord Evaluation
	 * @param function
	 * @throws SFException when this SFQuadsSurfaceGeometry cannot use TexCoord 
	 */
	public void setTexCoordGeometry(SFSurfaceFunction function) throws SFException{
		setFunction(SFPrimitiveBlock.TXO, function);
	}
	
	public void setMainGeometryFunction(SFSurfaceFunction function){
		SFPrimitiveBlock[] blocks=getPrimitive().getBlocks();
		if(functions==null)
			functions=new SFSurfaceFunction[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i]==SFPrimitiveBlock.POSITION ||
					blocks[i]==SFPrimitiveBlock.NORMAL ||
					blocks[i]==SFPrimitiveBlock.DU ||
					blocks[i]==SFPrimitiveBlock.DV){
				functions[i]=function;
			}
		}
	}
	
}
