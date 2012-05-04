package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.system.data.SFDataCenter;

public class Test0026_LoftGeometry {

	private static final String root = "testsData";
	
	private static SFObjectModel houseModel;
	private static CommonPipeline pipeline;

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		pipeline = new CommonPipeline();

		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer

		SFGeometry geometry = generateHouseModelGeometry();

		houseModel = new SFObjectModel();
		houseModel.getTransform().setPosition(new SFVertex3f(0, -0.8f, 0));
		houseModel.getModel().setRootGeometry(geometry);
		SFStructureArray materialArray = CommonMaterial.generateMaterial(CommonMaterial.generateColours());
		houseModel.getModel().addMaterial(
				new SFProgramStructureReference("BasicMat", new SFStructureReference(materialArray,
						0)));
		houseModel.getModel().getMaterialsComponents().add("BasicMat");

		SFReferenceNode node = new SFReferenceNode();
		node.addNode(houseModel);

		SFViewer viewer = SFViewer.generateFrame(houseModel,CommonMaterial.generateColoursController(houseModel),SFViewer.getLightStepController());
		viewer.setRotateModel(true, 0.04f);
	}

	private static SFGeometry generateHouseModelGeometry() {
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
		
		// 2) Store the Surface Function in file 'testsData\test0001.sf'
		SFDataUtility.saveDataset(root, "test0026.sf", function);

		// 3) Retrieve the Surface Function from file 'testsData\test0001.sf'
						
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFBicurvedLoftedSurfaceData retrievedData = (SFBicurvedLoftedSurfaceData) SFDataUtility
				.loadDataset(root, "test0026.sf");
		
		SFGeometry geometry = new SFQuadsSurfaceGeometry(pipeline.getPrimitive(), retrievedData.getResource(),
				null, 24, 2);
		geometry.init();
		
		return geometry;
	}
}
