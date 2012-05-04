package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.geometries.SFArcLengthuv;
import shadow.geometry.geometries.SFCompositeGeometryuv;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleObjPlaneTexCoordGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.SFTextureDataToken;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFTextureReference;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFOneStepAlgorithmData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.SFStructureArrayData;
import shadow.renderer.data.SFStructureReferenceData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.objects.SFString;

public class Test0027_TexturedMeshGeometry {

	private static final String root = "testsData";
	
	private static SFObjectModel houseModel;
	private static CommonPipeline pipeline;

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {


		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		pipeline=new CommonPipeline();
		
		SFViewerObjectsLibrary test0002Library=new SFViewerObjectsLibrary(root, "test0002.sf");
			library.put("BasicMatColours", test0002Library.getLibrary().retrieveDataset("BasicMatColours"));

		SFGeometry geometry = generateHouseModelGeometry(library);

		houseModel = new SFObjectModel();
		houseModel.getTransform().setPosition(new SFVertex3f(0, -0.8f, 0));
		houseModel.getModel().setRootGeometry(geometry);
		houseModel.getModel().getMaterialsComponents().add("TexturedMat");

		SFReferenceNode node = new SFReferenceNode();
		node.addNode(houseModel);

		SFViewer viewer = SFViewer.generateFrame(houseModel);
		viewer.setRotateModel(true, 0.03f);

		{	
			generateRectangle(library,0,0,1,1, 16 ,0);
			generateRectangle(library,-1,-1,1,1, 16  ,1);
			generateRectangle(library,-1,0,1,1, 10 ,2);
			generateRectangle(library,0,-1,1,1, 10 ,3);
			
			SFReferenceNodeData referenceNode=new SFReferenceNodeData();
				referenceNode.addNode("OBTilesModel0");
				referenceNode.addNode("OBTilesModel1");
				referenceNode.addNode("OBTilesModel2");
				referenceNode.addNode("OBTilesModel3");
			library.put("OBTilesDrawnTextureNode", referenceNode);
			
			SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
			SFRendererData renderer=new SFRendererData();
				renderer.setAlgorithm(new SFOneStepAlgorithmData("BasicColor"));
				SF2DCameraData cameraData=new SF2DCameraData();
					cameraData.setDimensions(0.5f, 0.5f);
				renderer.setCamera(cameraData);
			drawnTexture.setRenderer(renderer);
			drawnTexture.setNode("OBTilesDrawnTextureNode");
			drawnTexture.addOutputTexture(new SFTextureDataToken(400, 400, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT));
			library.put("OBTilesTexture", drawnTexture);
			
		}


		SFDataUtility.saveDataset(root, "test0027.sf", library);
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("OBTilesTexture",
				new SFDataCenterListener<SFDataAsset<SFRenderedTexturesSet>>() {
			@Override
			public void onDatasetAvailable(String name,
					SFDataAsset<SFRenderedTexturesSet> dataset) {
				houseModel.getModel().getTextures().add(new SFTextureReference(0, new SFTexture(dataset.getResource(), 0)));
				//SFTextureViewer.generateFrame(new SFTexture(dataset.getResource(), 0));
			}
		});
	}

	private static void generateRectangle(SFObjectsLibrary library,float x,float y,float w,float h,int materialIndex,int index) {

		SFQuadsSurfaceGeometryData rectangle1=new SFQuadsSurfaceGeometryData();
		SFRectangle2DFunctionData rectangle2d=new SFRectangle2DFunctionData(x, y, w, h);
		rectangle1.setNuNv(2, 2);
		rectangle1.getSurfaceFunction().setDataset(rectangle2d);
		rectangle1.getTexCoordFunction().setDataset(new SFSimpleTexCoordGeometryuvData());
		rectangle1.getPrimitive().setPrimitive(pipeline.getTexturePrimitive());
			library.put("OBTilesRectangle"+index, rectangle1);
		
		SFObjectModelData model1=new SFObjectModelData();
				model1.getPosition().getVertex3f().set3f(0, 0, 0);
				//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
				model1.addMaterialsStructures( new SFStructureReferenceData("BasicMatColours", materialIndex));
				model1.getMaterialsProgramComponents().add(new SFString("BasicMat"));
				model1.getRootGeometryReference().setReference("OBTilesRectangle"+index);
			//we insert the material in the library
			library.put("OBTilesModel"+index, model1);
	}

	private static SFGeometry generateHouseModelGeometry(SFObjectsLibrary library) {
		
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
		library.put("Spline1Data", spline1Data);

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
		library.put("Spline2Data", spline2Data);

		function.getFirstCurve().setReference("Spline1Data");
		function.getSecondCurve().setReference("Spline2Data");
		
		//TODO : missing Data version of SFCompositeGeometryuv, SFSimpleObjPlaneTexCoordGeometry, SFArcLengthuv
		
		SFCompositeGeometryuv geoemtryuv=new SFCompositeGeometryuv(
			new SFArcLengthuv(spline1Data.getResource(), 12, 100), new SFSimpleObjPlaneTexCoordGeometry(1,0,0,0,10,0) );

		SFGeometry geometry = new SFQuadsSurfaceGeometry(pipeline.getTexturePrimitive(), function.getResource(),
				geoemtryuv, 24, 2);
		
		geometry.init();
		return geometry;
	}
}
