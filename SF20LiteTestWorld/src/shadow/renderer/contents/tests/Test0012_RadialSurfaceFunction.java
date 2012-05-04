package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.math.SFVertex3f;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFStructureArrayData;
import shadow.renderer.data.proxies.SFClonedArrayReference;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

public class Test0012_RadialSurfaceFunction {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		// 1) Create a Surface Function (Mushroom) and edit its Data.
		// At Editing time we need to work directly on Data version of each
		// Asset
		// Geometry Function for this Object Model is retrieved from Test0001
		SFRadialSurfaceFunctionData radialFunction1 = new SFRadialSurfaceFunctionData();
			radialFunction1.setFirstCurve(new SFBasisSplineData(new SFVertexFixedListData(),true,
					new SFVertex3f(0.6f, 0.4f, 0),new SFVertex3f(0.0f, 0.4f, 0),
					new SFVertex3f(-0.1f, -0.4f, 0), new SFVertex3f(0.7f, -0.4f, 0)
					));
			radialFunction1.setSecondCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(0.3f, 0, -0.1f),new SFVertex3f(0.7f, 0.0f, -0.1f),
					new SFVertex3f(0.7f, 0.0f, 0)
					));
			
		SFRadialSurfaceFunctionData radialFunction2 = new SFRadialSurfaceFunctionData();
			radialFunction2.setFirstCurve(new SFBasisSplineData(new SFVertexFixedListData(),true,
					new SFVertex3f(-0.1f, 0.4f, 0),new SFVertex3f(-0.7f, 0.4f, 0),
					new SFVertex3f(-1.0f, -0.4f, 0), new SFVertex3f(0.0f, -0.4f, 0)
					));
			radialFunction2.setSecondCurve(new SFBasisSplineData(new SFVertexFixedListData(),
					new SFVertex3f(-0.4f, 0, -0.3f),new SFVertex3f(0.4f, 0.0f, -0.3f),
					new SFVertex3f(0.4f, 0.0f, 0)
					));


		SFQuadsSurfaceGeometryData geometry = new SFQuadsSurfaceGeometryData();
			geometry.setup("Func", 11, 5, new SFSimpleTexCoordGeometryuvData(), pipeline.getPrimitive());

		float[][] colour={{0.5f,0.9f,0.4f}};
		SFStructureArrayData material = CommonMaterial.generateMaterialData(colour);

		SFObjectModelData objectModel = new SFObjectModelData();
			objectModel.setGeometry(geometry);
			objectModel.addMaterial("BasicMat", material, 0);
		
			
		SFClonedArrayReference clonedArray = new SFClonedArrayReference();
		clonedArray.setNodeModel(objectModel);
		clonedArray.setData("Func", 0, radialFunction1);
		clonedArray.setData("Func", 1, radialFunction2);
		library.put("ArrayModel", clonedArray);

		// 2) Store the library containing all elements into
		// 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0012.sf", library);

		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0012.sf"));

		SFDataCenter.getDataCenter().makeDatasetAvailable("ArrayModel",
				new SFDataCenterListener<SFClonedArrayReference>() {
					@Override
					public void onDatasetAvailable(String name,
							SFClonedArrayReference dataset) {
						SFViewer.generateFrame(dataset.getResource(),SFViewer.getLightStepController());
					}
				});

	}
}
