package shadow.objloader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import objLoader.SimpleObjFile;
import objLoader.TriangleListener;
import objLoader.Vertex3f;
import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;
import shadow.system.SFException;
import shadow.system.data.SFDataset;

/**
 * A utility class which can load Objs as ShadowFramorkData
 * 
 * @author Alessandro Martinelli
 */
public class ShadowObjLoader {

	private static SFPrimitive primitive=new SFPrimitive();

	static{
		try {
			SFProgramComponentLoader.loadComponents(new File("objLoaderPipeline/objLoaderPipeline.txt"),new SFPipelineBuilder());

			try {
				primitive.addPrimitiveElement(PrimitiveBlock.POSITION, (SFProgramComponent)(SFPipeline.getModule("OBJTriangle")));
				primitive.addPrimitiveElement(PrimitiveBlock.NORMAL, (SFProgramComponent)(SFPipeline.getModule("OBJTriangle")));
				primitive.addPrimitiveElement(PrimitiveBlock.TXO, (SFProgramComponent)(SFPipeline.getModule("OBJTriangle")));
				primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("OBJBasicTess")));
			} catch (SFException e) {
				e.printStackTrace();
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Return the default primitive used to draw any obj file
	 * @return 
	 */
	public static SFPrimitive getPrimitive() {
		return primitive;
	}


	/**
	 * Generate an ArraList of SGGeoemtries from a SimpleObjFile, this geoemtries
	 * can be used in composition with other 
	 * 
	 * @param file
	 * @return null if Obj File cannot be translated into an SF model for any reason
	 */
	public ArrayList<SFGeometry> extractGeometries(SimpleObjFile file){
					
		ArrayList<SFGeometry> geometries=new ArrayList<SFGeometry>();
		
		for (int i = 0; i < file.getGeometriesNumber(); i++) {
			
			SFMeshGeometry geometry=new SFMeshGeometry();
			geometry.setPrimitive(primitive);

			SFPrimitiveArray primitiveData=SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);
			geometry.setArray(primitiveData);
			geometries.add(geometry);
			
			final ArrayList<Vertex3f> vertices=new ArrayList<Vertex3f>();
			final ArrayList<Vertex3f> normals=new ArrayList<Vertex3f>();
			final ArrayList<Vertex3f> txCoord=new ArrayList<Vertex3f>();
			TriangleListener trListener=new TriangleListener() {
				@Override
				public void sendTriangle(Vertex3f A, Vertex3f B, Vertex3f C, Vertex3f An,
						Vertex3f Bn, Vertex3f Cn, Vertex3f At, Vertex3f Bt, Vertex3f Ct) {
					vertices.add(A.cloneV());
					vertices.add(B.cloneV());
					vertices.add(C.cloneV());
					normals.add(An.cloneV());
					normals.add(Bn.cloneV());
					normals.add(Cn.cloneV());
					txCoord.add(At.cloneV());
					txCoord.add(Bt.cloneV());
					txCoord.add(Ct.cloneV());
				}
				@Override
				public void begin() {}
				@Override
				public void end() {}
			};
			
			file.drawGeometryOn(trListener, i);
			//System.err.println("v "+vertices.size()+" "+normals.size()+" "+txCoord.size());
			
			int elementIndex=primitiveData.generateElements(vertices.size()/3);
			geometry.setFirstElement(elementIndex);
			geometry.setLastElement(elementIndex+(vertices.size()/3));
			
			try {
				SFArray<SFValuenf> verticesArray=primitiveData.getPrimitiveData(0);
				int verticesIndex=verticesArray.generateElements(vertices.size());
				for (int j = 0; j < vertices.size(); j++) {
					Vertex3f v=vertices.get(j);
					verticesArray.setElement(verticesIndex+j, new SFVertex3f(v.x,v.y,v.z));
				}
				
				if(file.getNormals().length>0){
					SFArray<SFValuenf> normalsArray=primitiveData.getPrimitiveData(1);
					int normalsIndex=normalsArray.generateElements(normals.size());
					for (int j = 0; j < normals.size(); j++) {
						Vertex3f v=normals.get(j);
						normalsArray.setElement(normalsIndex+j, new SFVertex3f(v.x,v.y,v.z));
					}
					
				}
				
				if(file.getTexCoord().length>0){
					SFArray<SFValuenf> texturesArray=primitiveData.getPrimitiveData(2);
					int txIndex=texturesArray.generateElements(normals.size());
					for (int j = 0; j < txCoord.size(); j++) {
						Vertex3f v=txCoord.get(j);
						texturesArray.setElement(txIndex+j, new SFVertex2f(v.x,v.y));
					}	
				}
				
				int size=1;
				if(file.getNormals().length>0){
					size++;
				}
				if(file.getTexCoord().length>0){
					size++;
				}
				
				int[] prIndices=new int[3*size];
				
				for (int j = 0; j < vertices.size(); j+=3) {
					SFPrimitiveIndices indices=new SFPrimitiveIndices(primitive);
					
					for (int k = 0; k < size; k++) {
						prIndices[k*3+0]=j;
						prIndices[k*3+1]=j+1;
						prIndices[k*3+2]=j+2;
					}
					indices.setPrimitiveIndices(prIndices);
					primitiveData.setElement(elementIndex+(j/3), indices);
				}
			
				
			} catch (SFArrayElementException e) {
				e.printStackTrace();
			}
			
		}
			
		return geometries;
		
	}
}
