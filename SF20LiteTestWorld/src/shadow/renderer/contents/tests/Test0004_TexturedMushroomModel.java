package shadow.renderer.contents.tests;

import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.image.data.SFBitmapTextureData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFObjectsLibrary;

/**
 * 
 * Objective: verify the capability to store a RenderedTexture, load it,
 * apply it to some geometry and show the result on a Viewer.
 * 
 * <br/>
 * <h1>SF Contents Test/Tutorial Series</h1>
 * This Test/Tutorial is a part of a series whose aim is showing SF contents.
 * Each test is performed following the basic steps of any SF content lifetime.
 * <ol>
 * 		<li>Editing</li>
 * 		<li>Storing</li>
 * 		<li>Viewing</li>		
 * </ol>
 * Each SF content Module has a '*Data' counterpart which is involved in data processing.
 * The '*.Data' module will generate a proper SF module, and will be discarded when
 * unnecessary. 
 * 
 * @author Alessandro Martinelli
 */
public class Test0004_TexturedMushroomModel {

	private static final String root = "testsData";
	
	/**
	 * This test has 3 steps
	 * 
	 * 1) Create an ObjectModel (Mushroom) and edit its Data. and Store the ObjectModel in file 'testsData\test0002.sf'
	 * 2) Retrieve the ObjectModel from file 'testsData\test0002.sf' 
	 * 3) Show the ObjectModel on an SFViewer
	 * 
	 * After step (1) 'testsData\test0002.sf' is created, containing an assets library with the following structure.
	 * 	
	 * 	ObjectsLibrary
	 * 		|	
	 * 		Mushroom : Asset of Type SFQuadsSurfaceGeometryData
	 * 		|	|	
	 * 		|	|----- value Nu (int) = 6
	 * 		|	|----- value Nv (int) = 8
	 * 		|	|----- extractor : AssetComponent of Type SFConcreteTriangleExtractorData
	 * 		|	|----- surfaceFunction : AssetComponent of Type SFCurvedTubeFunctionData
	 * 		|	|		|
	 * 		|	|		| ----- centralCurve : AssetComponent of Type SFBasisSpline3DData
	 * 		|	|		| ----- rayCurve : AssetComponent of Type SFBasisSpline3DData
	 * 		|	|		|
	 * 		|	|----- texCoordFunction : AssetComponent of Type TexCoordFunction *
	 * 		|	|----- value primitive (SFPrimitiveData)
	 * 		|	
	 * 		Red : Asset of Type SFStructureArrayData
	 * 		|	|	
	 * 		|	|----- value SFStructureArray
	 * 		|	|		|----- data[0]:
	 * 		|	|		|		|----- (0) (vertex3f) = (0.5,0,0)
	 * 		|	|		|		|----- (1) (vertex3f) = (0.5,0,0) 
	 * 		|
	 * 		RedMushroom : Asset of Type SFObjectModelData
	 * 		|	|	
	 * 		|	|----- value position (vertex3f) = (0,0,0)
	 * 		|	|----- material[0]:
	 * 		|	|	   |-------------- value program (String) = (BasicMat)
	 * 		|	|	   |-------------- value data (Reference) = "Red"
	 * 		|	|----- rootGeometry (Reference) = "Mushroom"	 
	 * 		
	 * 
	 *  (*) TexCoordFunction is not part of the definitive core of SF Contents and will be removed some day...
	 */
	public static void main(String[] args) {
		
		//Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		SFObjectsLibrary library=objectsLibrary.getLibrary();
		CommonPipeline pipeline=new CommonPipeline();

		// 1) Create a Surface Function (Mushroom) and edit its Data.
		// At Editing time we need to work directly on Data version of each
		// Asset
		//Geometry Function for this Object Model is retrieved from Test0001
		SFCurvedTubeFunctionData retrievedFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");
			
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
			geometry.setup(retrievedFunction, 8, 8, new SFSimpleTexCoordGeometryuvData(), pipeline.getTexturePrimitive());
			library.put("TexturedMushroom", geometry);
	
		SFBitmapTextureData textureData=(SFBitmapTextureData)SFDataUtility.loadDataset(root, "test0003.sf");	
			library.put("PerlinTexture", textureData);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.placeGeometryAndScale("TexturedMushroom", 0, -0.6f, 0, 2);
			objectModel.addTexture("TexturedMat", "PerlinTexture");
			//we insert the material in the library
			library.put("PerlinMushroom", objectModel);
				
		// 2) Store the library containing all elements into 'testsData\test0002.sf'
		SFDataUtility.saveDataset(root, "test0004.sf", library);

		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0004.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				SFViewer.generateFrame(model,SFViewer.getLightStepController());
			}
		});
	}

}
