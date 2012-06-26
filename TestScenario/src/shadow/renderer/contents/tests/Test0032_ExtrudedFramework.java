package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.SFLine;
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
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.SFTextureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0032_ExtrudedFramework {

	private static final String root = "testsData";
	
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
		houseModel.getModel().getMaterialsComponents().add("TexturedMat");

		SFReferenceNode node = new SFReferenceNode();
		node.addNode(houseModel);

		SFViewer viewer=SFViewer.generateFrame(houseModel);
		viewer.setRotateModel(true, 0.01f);
		

		// In order to work properly load utilities will need the Datacenter to
		// contain a valid DatasetFactory
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0027.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("OBTilesTexture", new SFDataCenterListener<SFDrawnRenderedTextureData>() {
			@Override
			public void onDatasetAvailable(String name, SFDrawnRenderedTextureData dataset) {
				SFTexture texture=new SFTexture(dataset.getResource(), 0);
				houseModel.getModel().getTextures().add(new SFTextureReference(0,texture));
			}
		});	
	}

	private static SFGeometry generateHouseModelGeometry() {
		
		SFBicurvedLoftedSurface function = new SFBicurvedLoftedSurface();
		
		float dimX1=0.4f;
		float dimX2=0.5f;
		float dimY1=0.5f;
		float dimY2=0.6f;

		SFUniformCurvesSpline<SFVertex3f, SFLine<SFVertex3f>> spline1 = new SFUniformCurvesSpline<SFVertex3f, SFLine<SFVertex3f>>();

		spline1.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(dimX1,dimY1,0), new SFVertex3f(-dimX1,dimY1,0)));
		spline1.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(-dimX1,dimY1,0), new SFVertex3f(-dimX1,-dimY1,0)));
		spline1.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(-dimX1,-dimY1,0), new SFVertex3f(dimX1,-dimY1,0)));
		spline1.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(dimX1,-dimY1,0), new SFVertex3f(dimX1,dimY1,0)));
		
		
		SFUniformCurvesSpline<SFVertex3f, SFLine<SFVertex3f>> spline2 = new SFUniformCurvesSpline<SFVertex3f, SFLine<SFVertex3f>>();

		spline2.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(dimX2,dimY2,0), new SFVertex3f(-dimX2,dimY2,0)));
		spline2.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(-dimX2,dimY2,0), new SFVertex3f(-dimX2,-dimY2,0)));
		spline2.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(-dimX2,-dimY2,0), new SFVertex3f(dimX2,-dimY2,0)));
		spline2.addCurve(new SFLine<SFVertex3f>(new SFVertex3f(dimX2,-dimY2,0), new SFVertex3f(dimX2,dimY2,0)));
		
		function.setA(spline1);
		function.setB(spline2);
		
		SFPrimitive primitive=pipeline.getTrianglesTexturePrimitive();
		SFSimpleObjPlaneTexCoordGeometry texCoords = new SFSimpleObjPlaneTexCoordGeometry(10,0,0,0,10,0);
		
		SFMeshGeometry geometry = new SFQuadsSurfaceGeometry(primitive, function,
				texCoords, 5, 2);
		geometry.init();
		int data[]=geometry.getArray().extrude(geometry.getFirstElement(), geometry.getLastElement(),new SFVertex3f(0,0,0.1f));
		
		geometry.setLastElement(data[1]);
		
		return geometry;
	}
}
