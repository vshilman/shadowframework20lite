package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.bitmaps.data.SFSimplePerlinNoiseData;
import shadow.image.data.SFBitmapTextureData;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFTextureReference;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.system.data.SFDataCenter;

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
public class Test0003_BitmapTexture {

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

		// In order to work properly load utilities will need the Datacenter to
		// contain a valid DatasetFactory
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//Also prepare the pipeline
		final CommonPipeline pipeline=new CommonPipeline();

		SFSimplePerlinNoiseData perlinNoise = CommonTextures.generateSimplePerlinNoiseData();
		
		SFBitmapTextureData textureData=new SFBitmapTextureData();
			textureData.setBitmap(perlinNoise);
			
		// 2) Store the Surface Function in file 'testsData\test0003.sf'
		SFDataUtility.saveDataset(root, "test0003.sf", textureData);

		// 3) Retrieve the Surface Function from file 'testsData\test0003.sf'
						
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFBitmapTextureData retrievedData = (SFBitmapTextureData) SFDataUtility
				.loadDataset(root, "test0003.sf");
		
		SFRenderedTexturesSet resource=retrievedData.getResource();
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedGeometryFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");

		//resource will be instance of 'SFCurvedTubeFunction'; we are no more using 'SFCurvedTubeFunctionData'
		SFSurfaceFunction geometryResource=retrievedGeometryFunction.getResource();
		SFGeometry geometry = new SFQuadsSurfaceGeometry(pipeline.getTexturePrimitive(), geometryResource,
				new SFSimpleTexCoordGeometryuv(1,1), 8, 8);
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			node.getModel().getTextures().add(new SFTextureReference(0, new SFTexture(resource,0)));
			
			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
			node.getModel().addMaterial("TexturedMat");

		SFViewer.generateFrame(node);
	}

}
