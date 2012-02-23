package shadow.geometry.geometries;

import java.util.List;
import java.util.Map.Entry;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.editing.SFSurfaceQuadsExtractor;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.objects.SFFloatArray;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFIntArray;

/**
 * A Mesh Geometry Built Upon a SurfaceFunction
 * Will use extractors to generate Surface Code
 * 
 * @author Alessandro Martinelli
 */
public class SFQuadsSurfaceGeometry extends SFMeshGeometry{

	private enum Blocks{
		POSITION,
		NORMAL,
		DU,
		DV,
		TXO;
	}
	
	private class SFQuadSurfaceGeometryData extends SFCompositeDataArray{
		
		private SFIntArray positions;
		private SFFloatArray u_splits;
		private SFFloatArray v_splits;
		private SFInt Nu;
		private SFInt Nv;
		private SFDatasetObject surfaceFunction;
		private SFDatasetObject extractor;
		private SFDatasetObject texCoordFunction;
		
		@Override
		public void generateData() {
			positions=new SFIntArray(0);
			u_splits=new SFFloatArray(0);
			v_splits=new SFFloatArray(0);
			Nu=new SFInt(0);
			Nv=new SFInt(0);
			surfaceFunction=new SFDatasetObject(null);
			extractor=new SFDatasetObject(null);
			texCoordFunction=new SFDatasetObject(null);
			addDataObject(positions);
			addDataObject(u_splits);
			addDataObject(v_splits);
			addDataObject(Nu);
			addDataObject(Nv);
			addDataObject(surfaceFunction);
			addDataObject(extractor);
			addDataObject(texCoordFunction);
			System.err.println("Primitive Data "+getPrimitiveData());
			addDataObject(getPrimitiveData());
		}
		
		@Override
		public SFQuadSurfaceGeometryData clone() {
			return new SFQuadSurfaceGeometryData();
		}
	}
	
	
	public SFQuadsSurfaceGeometry() {
		setData(new SFQuadSurfaceGeometryData());
	}


	public SFQuadsSurfaceGeometry(SFPrimitive primitive,
			SFSurfaceFunction function,
			SFSurfaceGeometryTexCoordFunctionuv texCoord,
			SFSurfaceQuadsExtractor extractor,int N_u,int N_v) {
		super(primitive);
		setData(new SFQuadSurfaceGeometryData());
		this.setFunction(function);
		this.setExtractor(extractor);
		this.setTexCoord(texCoord);

		initSplits(N_u, N_v);
		
	}
	

	private void initSplits(int N_u, int N_v) {
		this.setNu(N_u);
		this.setNv(N_v);
		setU_splits(new float[N_u]);
		setV_splits(new float[N_v]);
		float stepU=1.0f/(N_u-1.0f);
		float stepV=1.0f/(N_v-1.0f);
		for (int i = 0; i < N_u; i++) {
			getU_splits()[i]=i*stepU;
		}
		for (int i = 0; i < N_v; i++) {
			getV_splits()[i]=i*stepV;
		}
	}
	

	@Override
	public void compile() {
		
		if(getArray()==null)			
			allocateGraphicsMemory();
		
		//clearElements();		
		int pr=getExtractor().getPrimitivesNumber();
		
		updateElements(getNu(),getNv(),pr);
		
		SFPrimitive primitive=getPrimitive();
		
		//Remember to introduce 'block' concept into pipeline
		List<Entry<SFPipelineRegister, SFProgramComponent>> blocks=primitive.getPrimitiveMap();
		
		//for each blocks, what should i do?

		SFPrimitiveIndices[] indices=new SFPrimitiveIndices[pr];
		indices[0]=new SFPrimitiveIndices(primitive);
		for (int k = 1; k < pr; k++) {
			indices[k]=indices[0].clone();
		}
		try {
			int index=0;
			getArray().generateElements(pr*(getNu()-1)*(getNv()-1));
			for (Entry<SFPipelineRegister, SFProgramComponent> entry : blocks) { //Iterate over all the blocks..
				SFPipelineRegister register=entry.getKey();
				SFArray<SFValuenf> values=getArray().getPrimitiveData(index);
				
				Blocks block = getBlock(register);
				compileBlockIndices(pr, indices, index, values,block);
				
				index++;
			}
		} catch (SFArrayElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private static Blocks getBlock(SFPipelineRegister register) {
		String registerName=register.getName();
		char[] rName=registerName.toCharArray(); 
		
		Blocks block=Blocks.POSITION;
		if(rName[0]=='N'){
			block=Blocks.NORMAL;
		}else if(rName.length>1 && rName[0]=='T' && rName[1]=='x'){
			block=Blocks.TXO;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='u'){
			block=Blocks.DU;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='v'){
			block=Blocks.DV;
		}
		return block;
	}


	private void compileBlockIndices(int pr, SFPrimitiveIndices[] indices,
			int index, SFArray<SFValuenf> values, Blocks block)
			throws SFArrayElementException {

		int n1=getExtractor().getN1();
		SFValuenf[] vertices = getDataArray(getNu(), getNv(), n1, block);//all positions are built up...
		getPositions()[index]=values.generateElements(vertices.length);
		
		int[][] prIndices=new int[pr][];
		for(int k=0;k<pr;k++){
			prIndices[k]=indices[k].getPrimitiveIndices()[index];
		}	//That's doing it...

		for(int i=0;i<getNv()-1;i++){
			for(int j=0;j<getNu()-1;j++){//for each element in the grid
				
				getExtractor().extractIndices(prIndices,
						values, vertices, getPositions()[index], getNu(), getNv() , i, j);
				for(int k=0;k<pr;k++){
					int indexElement=(i*(getNu()-1)+j)*pr+k;
					getArray().setElementData(indexElement, indices[k], index);
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
	public void updateElements(int Nu, int Nv, int pr) {
		int countElement=0;
		for (int i = 0; i < (Nu-1)*(Nv-1); i++) {
			for(int k=0;k<pr;k++)
				countElement++;
		}
		if(getLastElement()-getFirstElement()!=countElement){
			if(getLastElement()>getFirstElement())
				getArray().eraseElements(getFirstElement(),getLastElement()-getFirstElement());
			int firstElement=getArray().generateElements(countElement);
			setFirstElement(firstElement);
			setLastElement(firstElement+countElement);
		}
	}
	

	private SFValuenf[] getDataArray(int Nu, int Nv, int n1,Blocks block) {
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
		return getData().u_splits.getFloatValues();
	}


	public void setU_splits(float[] uSplits) {
		getData().u_splits.setFloatValues(uSplits) ;
	}


	public float[] getV_splits() {
		return getData().v_splits.getFloatValues();
	}


	public void setV_splits(float[] vSplits) {
		getData().v_splits.setFloatValues(vSplits) ;
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFQuadsSurfaceGeometry();
	}


	private int getNu() {
		return getData().Nu.getIntValue();
	}


	private void setNu(int nu) {
		getData().Nu.setIntValue(nu);
	}


	private int getNv() {
		return getData().Nv.getIntValue();
	}


	private void setNv(int nv) {
		getData().Nv.setIntValue(nv);
	}


	private int[] getPositions() {
		if(getData().positions.getIntValues().length==0){
			int blocksSize=getPrimitive().getPrimitiveMap().size();
			setPositions(new int[blocksSize]);
		}
		return getData().positions.getIntValues();
	}


	private void setPositions(int positions[]) {
		getData().positions.setIntValues(positions);
	}


	private SFSurfaceFunction getFunction() {
		return (SFSurfaceFunction)(getData().surfaceFunction.getDataset());
	}


	private void setFunction(SFSurfaceFunction function) {
		getData().surfaceFunction.setDataset(function);
	}


	private SFSurfaceQuadsExtractor getExtractor() {
		return (SFSurfaceQuadsExtractor)(getData().extractor.getDataset());
	}


	private void setExtractor(SFSurfaceQuadsExtractor extractor) {
		getData().extractor.setDataset(extractor);
	}


	private SFSurfaceGeometryTexCoordFunctionuv getTexCoord() {
		return (SFSurfaceGeometryTexCoordFunctionuv)(getData().texCoordFunction.getDataset());
	}


	private void setTexCoord(SFSurfaceGeometryTexCoordFunctionuv texCoord) {
		getData().texCoordFunction.setDataset(texCoord);
	}

	private SFQuadSurfaceGeometryData getData() {
		return (SFQuadSurfaceGeometryData)super.getSFDataObject();
	}


}
