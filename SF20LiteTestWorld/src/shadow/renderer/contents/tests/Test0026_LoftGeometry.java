package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
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
public class Test0026_LoftGeometry extends SFAbstractTest{
	
	private static final String FILENAME = "test0026";
	
	public static void main(String[] args) {
		execute(new Test0026_LoftGeometry());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFBicurvedLoftedSurfaceData retrievedData = loadDataset();

		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PN");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 24, 3,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		
		SFSurfaceFunction surfaceFunction=retrievedData.getResource();
		geometry.setFunction(SFPrimitiveBlock.NORMAL, surfaceFunction);
		geometry.setFunction(SFPrimitiveBlock.POSITION, surfaceFunction);
		geometry.init();
		
		SFObjectModel houseModel = new SFObjectModel();
		houseModel.getTransform().setPosition(new SFVertex3f(0, -0.8f, 0));
		houseModel.getModel().setRootGeometry(geometry);
		houseModel.getModel().getTransformComponent().setProgram("BasicPNTransform");
		houseModel.getModel().getMaterialComponent().setProgram("BasicMat");
		SFStructureArray materialArray = CommonMaterial.generateMaterial(CommonMaterial.generateColours());
		houseModel.getModel().getMaterialComponent().addData(new SFStructureReference(materialArray,0));

		SFAbstractReferenceNode node = new SFReferenceNode();
		node.addNode(houseModel);

		SFViewer viewer = SFViewer.generateFrame(houseModel,CommonMaterial.generateColoursController(houseModel),SFViewer.getLightStepController());
		viewer.setRotateModel(true, 0.04f);
	}
	
	@Override
	public void buildTestData() {
		SFBicurvedLoftedSurfaceData function = new SFBicurvedLoftedSurfaceData();

		float x1 = -0.7f;
		float z1 = -0.5f;
		float x2 = 0.3f;
		float z2 = 0.0f;
		float y1 = 0;
		float y2 = 0.5f;
		float y3 = 1.6f;
		float d1 = 0.2f;

		//SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		SFUniformBezier33fData spline1Data=new SFUniformBezier33fData();
		spline1Data.addBezier33f(x1 + d1, y1, z1, x2 - d1, y1, z1);
		spline1Data.addBezier33f(x2 - d1, y1, z1, x2, y1, z1, x2, y1, z1 + d1);
		spline1Data.addBezier33f(x2, y1, z1 + d1, x2, y1, z2 - d1);
		spline1Data.addBezier33f(x2, y1, z2 - d1, x2, y1, z2, x2 - d1, y1, z2);
		spline1Data.addBezier33f(x2 - d1, y1, z2, (x1 + x2) * 0.5f, y1, z2);
		spline1Data.addBezier33f((x1 + x2) * 0.5f, y1, z2, x1 + d1, y1, z2);
		spline1Data.addBezier33f(x1 + d1, y1, z2, x1, y1, z2, x1, y1, z2 - d1);
		spline1Data.addBezier33f(x1, y1, z2 - d1, x1, y1, z1 + d1);
		spline1Data.addBezier33f(x1, y1, z1 + d1, x1, y1, z1, x1 + d1, y1, z1);

		SFUniformBezier33fData spline2Data=new SFUniformBezier33fData();
		spline2Data.addBezier33f(x1 + d1, y2, z1, x2 - d1, y2, z1);
		spline2Data.addBezier33f(x2 - d1, y2, z1, x2, y2, z1, x2, y2, z1 + d1);
		spline2Data.addBezier33f(x2, y2, z1 + d1, x2, y2, z2 - d1);
		spline2Data.addBezier33f(x2, y2, z2 - d1, x2, y2, z2, x2 - d1, y2, z2);
		spline2Data.addBezier33f(x2 - d1, y2, z2, (x1 + x2 * 3) * 0.25f,(y2 + y3 * 3) * 0.25f, z2, (x1 + x2) * 0.5f, y3, z2);
		spline2Data.addBezier33f((x1 + x2) * 0.5f, y3, z2,(x1 * 3 + x2) * 0.25f, (y2 + y3 * 3) * 0.25f, z2, x1 + d1, y2, z2);
		spline2Data.addBezier33f(x1 + d1, y2, z2, x1, y2, z2, x1, y2, z2 - d1);
		spline2Data.addBezier33f(x1, y2, z2 - d1, x1, y2, z1 + d1);
		spline2Data.addBezier33f(x1, y2, z1 + d1, x1, y2, z1, x1 + d1, y2, z1);

		function.getFirstCurve().setDataset(spline1Data);
		function.getSecondCurve().setDataset(spline2Data);
		
		store(function);
	}
}
