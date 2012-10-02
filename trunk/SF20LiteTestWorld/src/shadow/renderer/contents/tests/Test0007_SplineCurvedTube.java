package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFLineData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
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
 * Objective: TODO
 * 
 * @author Alessandro Martinelli
 */
public class Test0007_SplineCurvedTube extends SFAbstractTest{

	private static final String FILENAME="test0007";
	
	public static void main(String[] args) {
		execute(new Test0007_SplineCurvedTube());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	
	
	@Override
	public void viewTestData() {

		SFSplineCurvedTubeFunctionData retrievedData = loadDataset();

		SFSurfaceFunction resource=retrievedData.getResource();

		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PN");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 2, 8,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.POSITION, resource);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, resource);
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			float[][] colours={{0.5f,0,0}};
			SFStructureArray array=CommonMaterial.generateMaterial(colours); 
			SFStructureReference materialReference=new SFStructureReference(array, 0);
			node.getModel().getMaterialComponent().addData(materialReference);
			node.getModel().getTransformComponent().setProgram("BasicPNTransform");
			node.getModel().getMaterialComponent().setProgram("BasicMat");

		SFViewer.generateFrame(node);	
	}
	
	@Override
	public void buildTestData() {

		SFSplineCurvedTubeFunctionData curvedTubefunction=new SFSplineCurvedTubeFunctionData();
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexListData16(),
					new SFVertex3f(-0.8f,-0.26f,0),
					new SFVertex3f(-0.35f,-0.1f,0),
					new SFVertex3f(0.2f,-0.8f,0),
					new SFVertex3f(0.9f,-0.08f,0)));
			
			curvedTubefunction.addCurve(new SFLineData(new SFVertexListData16(),
					new SFVertex3f(-0.75f,0,0.6f),
					new SFVertex3f(0.86f,0,0.6f)));
			
			curvedTubefunction.addCurve(new SFBasisSplineData(new SFVertexListData16(),
					new SFVertex3f(-0.8f,0.26f,0),
					new SFVertex3f(-0.35f,0.1f,0),
					new SFVertex3f(0.3f,0.6f,0),
					new SFVertex3f(0.9f,0.02f,0)));
		
		store(curvedTubefunction);
	}
}
