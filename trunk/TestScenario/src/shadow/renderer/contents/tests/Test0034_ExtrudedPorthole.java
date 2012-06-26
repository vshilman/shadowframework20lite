package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.SFBezier2;
import shadow.geometry.curves.SFRationalCurve3f;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFViewer;

public class Test0034_ExtrudedPorthole {

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
		houseModel.getTransform().setOrientation(SFMatrix3f.getRotationX(0.2f));
		houseModel.getModel().setRootGeometry(geometry);
			float[][] color={{1,0,0}};
			SFStructureArray materialArray=CommonMaterial.generateMaterial(color); 
		houseModel.getModel().addMaterial(new SFProgramStructureReference("BasicMat", new SFStructureReference(materialArray, 0)));
		houseModel.getModel().getMaterialsComponents().add("BasicMat");

		SFReferenceNode node = new SFReferenceNode();
		node.addNode(houseModel);

		SFViewer viewer=SFViewer.generateFrame(houseModel);
		viewer.setRotateModel(true, 0.02f);
	}

	private static SFGeometry generateHouseModelGeometry() {
		
		SFBicurvedLoftedSurface function = new SFBicurvedLoftedSurface();
		
		float r1=0.8f;
		float r2=0.6f;
		float rad=(float)Math.sqrt(2)*0.5f;

		SFUniformCurvesSpline<SFVertex3f, SFRationalCurve3f> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFRationalCurve3f>();

		spline1.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(r1,0,0,1), new SFVertex4f(r1*rad,r1*rad,0,rad), new SFVertex4f(0,r1,0,1))));
		spline1.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(0,r1,0,1), new SFVertex4f(-r1*rad,r1*rad,0,rad), new SFVertex4f(-r1,0,0,1))));
		spline1.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(-r1,0,0,1), new SFVertex4f(-r1*rad,-r1*rad,0,rad), new SFVertex4f(0,-r1,0,1))));
		spline1.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(0,-r1,0,1), new SFVertex4f(r1*rad,-r1*rad,0,rad), new SFVertex4f(r1,0,0,1))));
		
		SFUniformCurvesSpline<SFVertex3f, SFRationalCurve3f> spline2 = new SFUniformCurvesSpline<SFVertex3f, SFRationalCurve3f>();

		spline2.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(r2,0,0,1), new SFVertex4f(r2*rad,r2*rad,0,rad), new SFVertex4f(0,r2,0,1))));
		spline2.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(0,r2,0,1), new SFVertex4f(-r2*rad,r2*rad,0,rad), new SFVertex4f(-r2,0,0,1))));
		spline2.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(-r2,0,0,1), new SFVertex4f(-r2*rad,-r2*rad,0,rad), new SFVertex4f(0,-r2,0,1))));
		spline2.addCurve(new SFRationalCurve3f(new SFBezier2<SFVertex4f>(new SFVertex4f(0,-r2,0,1), new SFVertex4f(r2*rad,-r2*rad,0,rad), new SFVertex4f(r2,0,0,1))));
		
		function.setA(spline1);
		function.setB(spline2);
		
		SFPrimitive primitive=pipeline.getPrimitive3();
		
		SFMeshGeometry geometry = new SFQuadsSurfaceGeometry(primitive, function,
				null, 7, 2);
		
		geometry.init();
		int data[]=geometry.getArray().extrude(geometry.getFirstElement(), geometry.getLastElement(),new SFVertex3f(0,0,0.6f));

		geometry.setLastElement(data[1]);
		return geometry;
	}
}
