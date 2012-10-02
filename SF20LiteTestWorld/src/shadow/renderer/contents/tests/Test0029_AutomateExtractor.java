package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFCurves;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
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

public class Test0029_AutomateExtractor extends SFAbstractTest{
	
	private static final String FILENAME = "test0029";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0029_AutomateExtractor());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	
	private static SFObjectModel model;

	@Override
	public void viewTestData() {

		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer

		SFGeometry geometry = generateHouseModelGeometry();

		model = new SFObjectModel();
		model.getModel().setRootGeometry(geometry);
		model.getModel().getTransformComponent().setProgram("BasicPNTransform");
		model.getModel().getMaterialComponent().setProgram("BasicMat");
		float[][] color={{1.5f,0,0}};
			SFStructureArray materialArray=CommonMaterial.generateMaterial(color); 
		model.getModel().getMaterialComponent().addData(new SFStructureReference(materialArray, 0));
		
		SFAbstractReferenceNode node = new SFReferenceNode();
		node.addNode(model);

		SFViewer viewer=SFViewer.generateFrame(model);
		viewer.setWireframe(true);
	}

	private static SFGeometry generateHouseModelGeometry() {
		SFBicurvedLoftedSurface function = new SFBicurvedLoftedSurface();

		SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		spline1.addCurve(SFCurves.generateBezier33f(-0.5f, -0.5f, 0, 0.5f,-0.5f,0));
		
		SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline2 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		spline2.addCurve(SFCurves.generateBezier33f(-0.5f, 0.5f, 0, 0, 0.9f, 0, 0.5f,0.5f,0));

		function.setA(spline1);
		function.setB(spline2);
		
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PN");
		
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 2, 2,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		
		geometry.setFunction(SFPrimitiveBlock.POSITION, function);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, function);
		geometry.init();
		
		return geometry;
	}
	
	@Override
	public void buildTestData() {
		//nothing to Do for this test
	}
}
