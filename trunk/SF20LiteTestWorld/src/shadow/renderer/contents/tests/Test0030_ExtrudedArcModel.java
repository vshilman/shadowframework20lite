package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFCurves;
import shadow.geometry.curves.SFUniformCurvesSpline;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleObjPlaneTexCoordGeometry;
import shadow.image.SFTexture;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFTextureReference;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0030_ExtrudedArcModel {

	private static final String root = "testsData";
	private static CommonPipeline pipeline;

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		pipeline = new CommonPipeline();

		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer

		SFGeometry geometry[] = generateHouseModelGeometry();
		SFReferenceNode node = new SFReferenceNode();
		
		final SFObjectModel models[]=new SFObjectModel[geometry.length];
		
		for (int i = 0; i < geometry.length; i++) {
			SFObjectModel model1 = new SFObjectModel();
			model1.getTransform().setOrientation(SFMatrix3f.getRotationX(0.2f));
			model1.getModel().setRootGeometry(geometry[i]);
			model1.getModel().getMaterialsComponents().add("TexturedMat");
			node.addNode(model1);
			models[i]=model1;
		}
		
		SFViewer viewer=SFViewer.generateFrame(node);
		viewer.setRotateModel(true, 0.01f);

		//Loading a Texture to attach to each models
		
		// In order to work properly load utilities will need the Datacenter to
		// contain a valid DatasetFactory
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0027.sf"));
		

		SFDataCenter.getDataCenter().makeDatasetAvailable("OBTilesTexture", new SFDataCenterListener<SFDrawnRenderedTextureData>() {
			@Override
			public void onDatasetAvailable(String name, SFDrawnRenderedTextureData dataset) {
				SFTexture texture=new SFTexture(dataset.getResource(), 0);
				for (int i = 0; i < models.length; i++) {
					models[i].getModel().getTextures().add(new SFTextureReference(0,texture));
				}
			}
		});		
	}

	private static SFGeometry[] generateHouseModelGeometry() {
		
		SFBicurvedLoftedSurface function = new SFBicurvedLoftedSurface();

		SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		spline1.addCurve(SFCurves.generateBezier33f(-0.7f, -0.5f, 0, -0.5f, 0, 0, 0, 0.6f, 0));
		spline1.addCurve(SFCurves.generateBezier33f( 0, 0.6f, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0));
		
		SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>> spline2 = new SFUniformCurvesSpline<SFVertex3f, SFBezier3<SFVertex3f>>();

		spline2.addCurve(SFCurves.generateBezier33f(0.0f, -0.5f, 0, -0.5f, -0.2f, 0, 0, 0.5f, 0));
		spline2.addCurve(SFCurves.generateBezier33f( 0, 0.5f, 0, 0.5f, -0.2f, 0, 0.4f, -0.5f, 0));

		function.setA(spline1);
		function.setB(spline2);
		
		SFPrimitive primitive=pipeline.getPrimitive();
		SFSimpleObjPlaneTexCoordGeometry texCoords = new SFSimpleObjPlaneTexCoordGeometry(10,0,0,0,10,0);
		
		SFMeshGeometry geometry = new SFQuadsSurfaceGeometry(primitive, function, texCoords, 3, 2);
		
		geometry.init();
		int data[]=geometry.getArray().extrude(geometry.getFirstElement(), geometry.getLastElement(),new SFVertex3f(0,0.0f,0.1f));

		SFMeshGeometry[] geometries=new SFMeshGeometry[1];
		geometries[0]=geometry;
//		geometries[1]=new SFMeshGeometry();
//		geometries[1].setArray(geometries[0].getArray());
//		geometries[1].setFirstElement(data[0]);
		geometries[0].setLastElement(data[1]);
		//geometries[1].setPrimitive(geometry.getPrimitive());
		
		return geometries;
	}
}
