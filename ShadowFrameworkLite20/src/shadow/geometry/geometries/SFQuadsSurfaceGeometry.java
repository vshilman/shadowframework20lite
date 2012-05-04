package shadow.geometry.geometries;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.operational.SFStandardQuadExtractor;
import shadow.operational.grid.SFGridOperations;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFPrimitiveGrid;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

/**
 * A Mesh Geometry Built Upon a SurfaceFunction
 * 
 * @author Alessandro Martinelli
 */
public class SFQuadsSurfaceGeometry extends SFMeshGeometry{

	private int Nv,Nu;
	private float vSplits[];
	private float uSplits[];
	private SFSurfaceGeometryTexCoordFunctionuv functionuv;
	private int positions[]=new int[0];
	private SFSurfaceFunction surfaceFunction;
	
	public SFQuadsSurfaceGeometry() {
	}


	public SFQuadsSurfaceGeometry(SFPrimitive primitive,
			SFSurfaceFunction function,
			SFSurfaceGeometryTexCoordFunctionuv texCoord,int N_u,int N_v) {
		super(primitive);
		this.setFunction(function);
		this.setTexCoord(texCoord);
		setNu(N_u);
		setNv(N_v);
	}
	

	@Override
	public void compile() {
		
		if(getArray()==null)			
			setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(getPrimitive()));
		
		//clearElements();
		int pr=getPrimitive().isQuad()?1:2;
		
		updateElements(getNu(),getNv(),pr);
		
		SFPrimitive primitive=getPrimitive();
		
		//for each blocks, what should i do?

		SFPrimitiveIndices[] indices=new SFPrimitiveIndices[pr];
		indices[0]=new SFPrimitiveIndices(primitive);
		for (int k = 1; k < pr; k++) {
			indices[k]=indices[0].clone();
		}
		try {
			//int index=0;
			
			SFPrimitiveGrid grid[] = primitive.getGridInstances();
			
			for (int gridIndex = 0; gridIndex < grid.length; gridIndex++) {
				SFArray<SFValuenf> values=getArray().getPrimitiveData(gridIndex);
				compileBlockIndices(pr, indices, gridIndex, values);
				
			}
			
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		
	}


	private void compileBlockIndices(int pr, SFPrimitiveIndices[] indices,
			int gridIndex, SFArray<SFValuenf> values)
			throws SFArrayElementException {
		PrimitiveBlock block=getPrimitive().getGridInstances()[gridIndex].getBlock();
		SFPipelineGrid grid=getPrimitive().getGridInstances()[gridIndex].getGrid();

		SFStandardQuadExtractor extractor=new SFStandardQuadExtractor(grid);
		
		int n1=SFGridOperations.getGridDimension(grid);
		SFValuenf[] vertices = getDataArray(getNu(), getNv(), n1, block);//all positions are built up...
		getPositions()[gridIndex]=values.generateElements(vertices.length);
		
		int[][] prIndices=new int[pr][];
		for(int k=0;k<pr;k++){
			prIndices[k]=indices[k].getPrimitiveIndices();
		}	//That's doing it...
		

		for(int i=0;i<getNv()-1;i++){
			for(int j=0;j<getNu()-1;j++){//for each element in the grid
				extractor.extractIndices(prIndices, getPrimitive().getIndicesPositions()[gridIndex],
						values, vertices, getPositions()[gridIndex], getNu(), getNv() , i, j);
				
				for(int k=0;k<pr;k++){
					int indexElement=(i*(getNu()-1)+j)*pr+k;
					getArray().setElementData(indexElement, indices[k], gridIndex);
				}
			}	
		}
		
	}

	/**
	 * Update the number of this QuadGeometry elements
	 * @param Nu
	 * @param Nv
	 * @param pr
	 */
	public int updateElements(int Nu, int Nv, int pr) {
		int countElement=(Nu-1)*(Nv-1)*pr;
		if(getLastElement()-getFirstElement()!=countElement){
			if(getLastElement()>getFirstElement())
				getArray().eraseElements(getFirstElement(),getLastElement()-getFirstElement());
			int firstElement=getArray().generateElements(countElement);
			setFirstElement(firstElement);
			setLastElement(firstElement+countElement);
		}
		return countElement;
	}
	

	private SFValuenf[] getDataArray(int Nu, int Nv, int n1,PrimitiveBlock block) {
		SFValuenf vertices[]=new SFValuenf[((Nv-1)*n1+1)*((Nu-1)*n1+1)];
		float stepn1=1.0f/n1;
		int index=0;
		for(int i=0;i<(Nv-1)*n1+1;i++){
			int I=i/n1;
			int Ires=i-n1*(I);
			float v=getV_splits()[I]+(Ires>0?Ires*stepn1*(getV_splits()[I+1]-getV_splits()[I]):0);
			for(int j=0;j<(Nu-1)*n1+1;j++){
				int J=j/n1;
				int Jres=j-n1*(J);
				float u=getU_splits()[J]+(Jres>0?Jres*stepn1*(getU_splits()[J+1]-getU_splits()[J]):0);
				switch (block) {
					case POSITION: vertices[index]=getFunction().getPosition(u, v); break;
					case NORMAL: vertices[index]=getFunction().getNormal(u, v); break;
					case DU: vertices[index]=getFunction().getDu(u, v); break;
					case DV: vertices[index]=getFunction().getDv(u, v); break;
					case TXO: SFVertex3f vertex=getFunction().getPosition(u, v); 
						vertices[index]=getTexCoord().getTexCoord(u, v,vertex.getX(),vertex.getY(),vertex.getZ()); break;
				}
				index++;
			}
		}
		
		return vertices;
	}
	
	
	public float[] getU_splits() {
		return uSplits;
	}


	public void setU_splits(float[] uSplits) {
		this.uSplits=uSplits;
	}


	public float[] getV_splits() {
		return vSplits;
	}


	public void setV_splits(float[] vSplits) {
		this.vSplits=vSplits;
	}


	public int getNu() {
		return Nu;
	}


	public void setNu(int nu) {
		this.Nu=nu;
		setU_splits(new float[nu]);
		float stepU=1.0f/(nu-1.0f);
		for (int i = 0; i < nu; i++) {
			getU_splits()[i]=i*stepU;
		}
	}


	public int getNv() {
		return Nv;
	}


	public void setNv(int nv) {
		this.Nv=nv;
		setV_splits(new float[nv]);
		float stepV=1.0f/(nv-1.0f);
		for (int i = 0; i < nv; i++) {
			getV_splits()[i]=i*stepV;
		}
	}


	public int[] getPositions() {
		if(positions.length==0){
			int blocksSize=getPrimitive().getBlocks().length;
			setPositions(new int[blocksSize]);
		}
		return positions;
	}

	public void setPositions(int positions[]) {
		this.positions=positions;
	}


	public SFSurfaceFunction getFunction() {
		return surfaceFunction;
	}


	public void setFunction(SFSurfaceFunction surfaceFunction) {
		this.surfaceFunction=surfaceFunction;
	}


	public SFSurfaceGeometryTexCoordFunctionuv getTexCoord() {
		return this.functionuv;
	}


	public void setTexCoord(SFSurfaceGeometryTexCoordFunctionuv texCoord) {
		this.functionuv=texCoord;
	}


	public SFSurfaceGeometryTexCoordFunctionuv getFunctionuv() {
		return functionuv;
	}


	public void setFunctionuv(SFSurfaceGeometryTexCoordFunctionuv functionuv) {
		this.functionuv = functionuv;
	}


	public SFSurfaceFunction getSurfaceFunction() {
		return surfaceFunction;
	}


	public void setSurfaceFunction(SFSurfaceFunction surfaceFunction) {
		this.surfaceFunction = surfaceFunction;
	}

	
}
