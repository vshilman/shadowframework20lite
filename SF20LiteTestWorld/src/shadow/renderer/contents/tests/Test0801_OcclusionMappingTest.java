package shadow.renderer.contents.tests;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFValue1f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFViewer;

/**
 * If you need to align this test Data, run the {@link SFGenerateAllTestData} utility once.
 * No data will be generated (so nothing will work) until you do that; as an
 * alternative, you can set SFAbstractTest.storeData to true, and then run each test
 * one by one in test number order.
 * <br/>
 * Go to {@link SFAbstractTest} for general informations about this tests.
 * <br/>
 * Open the related FILENAME.xml file for a detailed view of this test contents. 
 * <br/>
 * Objective: verify the capability to store a Surface Geometry, load it and show
 * it on a Viewer.
 * 
 * 
 * @see SFAbstractTest, SFObjectModel, SFStructureArray, SFStructureReference, SFRegularSurfaceFunction, SFCurvedTubeFunctionData, SFCurvedTubeFunction, SFBasisSplineData, SFViewer
 * 
 * @author Alessandro Martinelli
 */
public class Test0801_OcclusionMappingTest extends SFAbstractTest{

	
	public static void main(String[] args) {
		CommonPipeline.commonPipeline="data/occlusionpipeline";
		execute(new Test0801_OcclusionMappingTest());
	}
	
	@Override
	public String getFilename() {
		return "";//No file
	}
	

	@Override
	public void viewTestData() {
		
		final SFPrimitive primitive=SFPipeline.getPrimitive("TrianglePND1");
		
		SFMeshGeometry geometry=new SFMeshGeometry(primitive){
			@Override
			
			public void compile() {
				super.compile();
				
				//This code will generate only 1 Triangle
				
				int positions=getArray().getPrimitiveData(0).generateElements(3);
				int normals=getArray().getPrimitiveData(1).generateElements(3);
				int datas=getArray().getPrimitiveData(2).generateElements(3);
			
				//vertices of the triangles
				float[] verticesValues={0,0,0,1,0,0,0,1,0};
				
				//normals of the triangles
				float[] normalsValues={0,0,1,0,0,1,0,0,1};
				
				//occlusion of the triangles
				float[] data1Values={1,0.5f,0.0f};
			
				for (int i = 0; i < 3; i++) {
					
					SFVertex3f position=new SFVertex3f(verticesValues[3*i], verticesValues[3*i+1], verticesValues[3*i+2]);
					getArray().getPrimitiveData(0).setElement(positions+i, position);
					
					SFVertex3f normal=new SFVertex3f(normalsValues[3*i], normalsValues[3*i+1], normalsValues[3*i+2]);
					getArray().getPrimitiveData(1).setElement(normals+i, normal);

					SFValue1f occlusion=new SFValue1f(data1Values[i]);
					getArray().getPrimitiveData(2).setElement(datas+i, occlusion);
				}

				int primitiveIndex=getArray().generateElement();

				int[] indices = { positions,positions+1,positions+2   ,  normals,normals+1,normals+2   ,    datas,datas+1,datas+2 };
				
				SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
				prIndices.setPrimitiveIndices(indices);
				
				getArray().setElement(primitiveIndex, prIndices);
			}
		};
		
		SFObjectModel model=new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat") );
		model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1") );
		
		geometry.init();
		model.init();

		SFViewer.generateFrame(model,new SFProgramModuleStructures("BasicColor"));
		
		
	}

	@Override
	public void buildTestData() {
		//nothing to do ...
	}

}
