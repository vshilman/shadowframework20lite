package shadow.geometry.geometries;

import java.util.List;
import java.util.Map.Entry;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.editing.SFSurfaceQuadsExtractor;
import shadow.geometry.editing.SFSurfaceTexCoordQuadsExtractor;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;

/**
 * 
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
	
	
	private SFSurfaceFunction function;
	
	private SFSurfaceQuadsExtractor extractor;
	
	private SFSurfaceGeometryTexCoordFunctionuv texCoord;
	
	//Tessellation 
	private int Nu=10;
	private int Nv=10;
	private float u_splits[];
	
	private float v_splits[];
	
	private int positions[];
	
	public SFQuadsSurfaceGeometry(SFPrimitive primitive,
			SFSurfaceFunction function,
			SFSurfaceGeometryTexCoordFunctionuv texCoord,
			SFSurfaceQuadsExtractor extractor,int N_u,int N_v) {
		super(primitive);
		this.function = function;
		this.extractor = extractor;
		this.texCoord=texCoord;

		initSplits(N_u, N_v);
		
		int blocksSize=primitive.getPrimitiveMap().size();
		positions=new int[blocksSize];
	}
	

	private void initSplits(int N_u, int N_v) {
		this.Nu=N_u;
		this.Nv=N_v;
		u_splits=new float[N_u];
		v_splits=new float[N_v];
		float stepU=1.0f/(N_u-1.0f);
		float stepV=1.0f/(N_v-1.0f);
		for (int i = 0; i < N_u; i++) {
			u_splits[i]=i*stepU;
		}
		for (int i = 0; i < N_v; i++) {
			v_splits[i]=i*stepV;
		}
	}
	

	@Override
	public void compile() {

		//clearElements();		
		int pr=extractor.getPrimitivesNumber();
		
		updateElements(Nu,Nv,pr);
		
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
			getArray().generateElements(pr*(Nu-1)*(Nv-1));
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

		int n1=extractor.getN1();
		SFValuenf[] vertices = getDataArray(Nu, Nv, n1, block);//all positions are built up...
		positions[index]=values.generateElements(vertices.length);
		
		int[][] prIndices=new int[pr][];
		for(int k=0;k<pr;k++){
			prIndices[k]=indices[k].getPrimitiveIndices()[index];
		}	//That's doing it...

		for(int i=0;i<Nv-1;i++){
			for(int j=0;j<Nu-1;j++){//for each element in the grid
				
				extractor.extractIndices(prIndices,
						values, vertices, positions[index], Nu, Nv , i, j);
				for(int k=0;k<pr;k++){
					int indexElement=(i*(Nu-1)+j)*pr+k;
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
			float v=v_splits[I]+(Ires>0?Ires*stepn1*(v_splits[I+1]-v_splits[I]):0);
			for(int j=0;j<(Nu-1)*n1+1;j++){
				int J=j/n1;
				int Jres=j-n1*(J);
				float u=u_splits[J]+(Jres>0?Jres*stepn1*(u_splits[J+1]-u_splits[J]):0);
				switch (block) {
					case POSITION: vertices[index]=function.getPosition(u, v); break;
					case NORMAL: vertices[index]=function.getNormal(u, v); break;
					case DU: vertices[index]=function.getDu(u, v); break;
					case DV: vertices[index]=function.getDv(u, v); break;
					case TXO: SFVertex3f vertex=function.getPosition(u, v); 
						vertices[index]=texCoord.getTexCoord(u, v,vertex.getX(),vertex.getY(),vertex.getZ()); break;
				}
				index++;
			}
		}
		
		return vertices;
	}
	
	
	public float[] getU_splits() {
		return u_splits;
	}


	public void setU_splits(float[] uSplits) {
		u_splits = uSplits;
	}


	public float[] getV_splits() {
		return v_splits;
	}


	public void setV_splits(float[] vSplits) {
		v_splits = vSplits;
	}

}
