package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFCurves;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFViewer;

public class Test0029_AutomateExtractor {

	private static SFObjectModel houseModel;
	private static CommonPipeline pipeline;

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		pipeline = new CommonPipeline();

		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer

		SFGeometry geometry = generateHouseModelGeometry();

		houseModel = new SFObjectModel();
		houseModel.getModel().setRootGeometry(geometry);
		float[][] color={{1.5f,0,0}};
			SFStructureArray materialArray=CommonMaterial.generateMaterial(color); 
		houseModel.getModel().addMaterial(new SFProgramStructureReference("BasicMat", new SFStructureReference(materialArray, 0)));
		houseModel.getModel().getMaterialsComponents().add("BasicMat");

		SFReferenceNode node = new SFReferenceNode();
		node.addNode(houseModel);

		SFViewer viewer=SFViewer.generateFrame(houseModel);
		viewer.setWireframe(true);
	}

	private static SFGeometry generateHouseModelGeometry() {
		SFBicurvedLoftedSurface function = new SFBicurvedLoftedSurface();

		SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		spline1.addCurve(SFCurves.generateBezier33f(-0.5f, -0.5f, 0, 0.5f,-0.5f,0));
		
		SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline2 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		spline2.addCurve(SFCurves.generateBezier33f(-0.5f, 0.5f, 0, 0, 0.8f, 0, 0.5f,0.5f,0));

		function.setA(spline1);
		function.setB(spline2);
		
		//SFCompositeGeometryuv geoemtryuv=new SFCompositeGeometryuv(
		//	new SFArcLengthuv(spline2, 100), new SFSimpleTexCoordGeometryuv(0, 3) );
		
		SFPrimitive primitive=pipeline.getPrimitive();
		//SFPrimitive primitive=pipeline.getPrimitive3();
		//SFPrimitive primitive=pipeline.getTrianglesPrimitive();
		
		SFGeometry geometry = new SFQuadsSurfaceGeometry(primitive, function,
				null, 4, 4);
		geometry.init();
		return geometry;
	}
}
