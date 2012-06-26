package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.proxies.SFClonedArrayReference;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

public class Test0013_ClonedArrayReference {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		SFRadialSurfaceFunctionData radialFunction = new SFRadialSurfaceFunctionData();
			radialFunction.setFirstCurve(new SFBasisSplineData("A",true));
			radialFunction.setSecondCurve(new SFBasisSplineData("B"));

		SFQuadsSurfaceGeometryData geometry = new SFQuadsSurfaceGeometryData();
			geometry.setup(radialFunction, 5, 3, new SFSimpleTexCoordGeometryuvData(), pipeline.getTexturePrimitive());
		
		//copying "PerlinTexture" from Test0004 to library
		SFViewerObjectsLibrary test0004Library=new SFViewerObjectsLibrary(root, "test0004.sf");
			library.put("PerlinTexture", test0004Library.getLibrary().retrieveDataset("PerlinTexture"));
				
		SFObjectModelData objectModel = new SFObjectModelData();
			objectModel.addTexture("TexturedMat", "PerlinTexture");
			objectModel.setGeometry(geometry);

		SFClonedArrayReference clonedArray = new SFClonedArrayReference();
			clonedArray.setNodeModel(objectModel);
			generateAllSplines(clonedArray);
			library.put("PebblesModel", clonedArray);

		// 2) Store the library containing all elements into
		// 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0013.sf", library);
		
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0013.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("PebblesModel",
				new SFDataCenterListener<SFClonedArrayReference>() {
					@Override
					public void onDatasetAvailable(String name,
							SFClonedArrayReference dataset) {
						SFViewer.generateFrame(dataset.getResource());
					}
				});

	}

	
	private static void addSpline(SFClonedArrayReference clonedArray,String name,int index,float... data) {
		SFVertexFixedListData centralSplineData = new SFVertexFixedListData();
		centralSplineData.add(data);
		clonedArray.getMappedProxy().getProxyDataCenter()
			.setData(name, index, centralSplineData);
	}
	
	private static void generateAllSplines(SFClonedArrayReference clonedArray) {
	
		addSpline(clonedArray, "A", 0, 0.6f, 0.4f, 0,
				0.0f, 0.4f, 0,
				-0.1f, -0.4f, 0,
				0.7f, -0.4f, 0);
		addSpline(clonedArray, "B", 0, 0.3f, 0, -0.1f,
				0.7f, 0.0f, -0.1f,
				0.7f, 0.0f, 0);
		
		addSpline(clonedArray, "A", 1, 0.0f, 0.7f, 0,
				-0.7f, 0.8f, 0,
				-0.6f, 0.0f, 0,
				-0.1f, 0.0f, 0);
		addSpline(clonedArray, "B", 1, -0.3f, 0.4f, -0.1f,
				-0.0f, 0.4f, -0.1f,
				-0.0f, 0.4f, 0);
		
		addSpline(clonedArray, "A", 2, -0.1f, -0.1f, 0,
				-0.9f,-0.1f, 0,
				-0.6f, -0.9f, 0,
				-0.2f, -0.9f, 0);
		addSpline(clonedArray, "B", 2, -0.5f, -0.4f, -0.1f,
				-0.2f, -0.4f, -0.1f,
				-0.2f, -0.4f, 0);

		addSpline(clonedArray, "A", 3, 0.9f, 0.9f, 0,
				0.5f, 0.9f, 0,
				0.6f, 0.1f, 0,
				0.9f, 0.0f, 0);
		addSpline(clonedArray, "B", 3, 0.7f, 0.7f, -0.1f,
				1.0f, 0.7f, -0.1f,
				1.0f, 0.7f, 0);

		addSpline(clonedArray, "A", 4, 0.4f, 0.4f, 0,
				0.6f, 1.0f, 0,
				-0.1f, 1.2f, 0,
				0.0f, 0.5f, 0);
		addSpline(clonedArray, "B", 4, 0.2f, 0.8f, -0.1f,
				0.55f, 0.8f, -0.1f,
				0.6f, 0.8f, 0);

		addSpline(clonedArray, "A", 5, 0.1f, -0.5f, 0,
				0.1f, -0.7f, 0,
				1.0f, -1.0f, 0,
				0.7f, -0.5f, 0);
		addSpline(clonedArray, "B", 5, 0.5f, -0.7f, -0.1f,
				0.7f, -0.7f, -0.1f,
				0.8f, -0.7f, 0);

		addSpline(clonedArray, "A", 6, -0.6f, 0.6f, 0,
				-0.6f, 1.0f, 0,
				-1.1f, 0.9f, 0,
				-1.0f, 0.6f, 0);
		addSpline(clonedArray, "B", 6, -0.9f, 0.8f, -0.1f,
				-0.6f, 0.8f, -0.1f,
				-0.6f, 0.8f, 0);

		addSpline(clonedArray, "A", 7, 0.8f, -0.5f, 0,
				1.1f, -0.5f, 0,
				1.2f, 0.8f, 0,
				0.7f, 0.0f, 0);
		addSpline(clonedArray, "B", 7, 1.0f, -0.2f, -0.1f,
				1.2f, -0.2f, -0.1f,
				1.2f, -0.2f, 0);
		
	}
}
