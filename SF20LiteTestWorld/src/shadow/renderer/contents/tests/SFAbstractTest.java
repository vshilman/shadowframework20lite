package shadow.renderer.contents.tests;

import java.util.ArrayList;

import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.renderer.SFNode;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFObjectsLibraryDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFFrameController;
import shadow.renderer.viewer.SFTextureViewer;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFException;
import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFObjectsLibrary.RecordData;

/**
 * A generic abstract class for all contents Tests.
 * 
 * <br/>
 * <h1>SF Contents Test/Tutorial Series</h1> This Tests/Tutorials are a part of
 * a series whose aim is testing/showing SF Rendering contents. Each test is
 * performed (see the <i>execute</i> method) following the basic steps of any SF
 * content lifetime.
 * <ol>
 * <li>Editing</li>
 * <li>Storing</li>
 * <li>Viewing</li>
 * </ol>
 * 
 * SFData will be generated each time into two different format. The '.sf'
 * format and the '.xml'. The '.xml' format should be used only for editing
 * purposes, and you can switch at any time from one to the other.
 * 
 * @author Alessandro Martinelli
 */
public abstract class SFAbstractTest {

	/**
	 * The folder where all testsData will be placed
	 */
	protected static String root = "testsData";
	/**
	 * The folder where all testsData will be placed
	 */
	protected static String rootInput = "testsDataInput";

	/**
	 * Setting this to true/false will allow you to activate/deactivate the
	 * buildAndStoreTestData() part of each test.
	 */
	protected static boolean storeData = true;
	
	/**
	 * Affects the loadLibraryAsDataCenter() method; the library
	 * will be loaded from the xml file. Setting this to 'true'
	 * and 'storeData' to false will allow you to edit xml
	 * files in order to see the changes.
	 * 
	 * Works only on test using loadLibraryAsDataCenter(), that is
	 * the ones which build, store and load a complete library
	 */
	protected static boolean loadLibrariesAsXml = false;

	/**
	 * A ready-to-use ObjectsLibrary; Most tests will need it.
	 */
	protected SFObjectsLibrary library;
	

	/**
	 * Execute this test
	 */
	public void execute() {

		setupAmbient();

		if (storeData) {

			buildTestData();
		}

		viewTestData();

	}
	
	/**
	 * A static version of the execute method
	 * @param test
	 */
	public static void execute(SFAbstractTest test){
		test.execute();
	}

	public <S extends SFDataset> S loadDataset() {
		return loadDataset(getFilename());
	}

	/**
	 * Load a {@link SFDataset} generated from some other test
	 * 
	 * @param testName the name of the test filename which generated the desired data
	 * @return the desired {@link SFDataset}
	 */
	@SuppressWarnings("unchecked")
	public <S extends SFDataset> S loadDataset(String testName) throws SFException{
		S s;
		try {
			/*the warning on-this line is suppressed; IF there is any casting-check problem, it will be 
			 * caught as an SF exception and rightly messaged to this method caller*/
			s = (S) SFDataUtility.loadDataset(root, testName + ".sf");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("Couldn't load an asset from file " + testName + ".sf");
		}
		return s;
	}

	/**
	 * Load an asset you assume is already available in the Data Center.
	 * @param assetName the name of the Dataset
	 * @return the asset registered with the given name
	 * @throws SFException if you were wrong
	 */
	@SuppressWarnings("unchecked")
	public <T extends SFInitiable> T getAlreadyAvailableDatasetResource(String assetName) throws SFException{
		T t;
		try {
			/*the warning on-this line is suppressed; IF there is any casting-check problem, it will be 
			 * caught as an SF exception and rightly messaged to this method caller*/
			t=(T)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset(assetName)).getResource();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("The asset " + assetName + " is not an 'already available' asset");
		}
		return t;
	}

	/**
	 * Called to setup the Ambient required for a test to run. Should always be
	 * called once before both buildAndStoreTestData and loadAndViewTestData
	 */
	public void setupAmbient() {
		// Preparation
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		library = objectsLibrary.getLibrary();
		CommonPipeline.prepare();
	}

	/**
	 * Store the '.sf' file and '.xml' file for a given library
	 * 
	 * @param library
	 */
	public void store(SFObjectsLibrary library) {

		String filename = getFilename();

		SFDataUtility.saveDataset(root, filename + ".sf", library);

		//SFDataUtility.saveDatasetJs(root, filename + ".js.sf", library);

		SFDataUtility.saveXMLFile(root, filename, library);
	}

	/**
	 * Store the '.sf' file and '.xml' file for a given asset
	 * 
	 * @param library
	 */
	public void store(SFDataAsset<?> asset) {

		String filename = getFilename();

		SFDataUtility.saveDataset(root, filename + ".sf", asset);

		SFDataUtility.saveXMLFile(root, filename, asset);
	}
	
	/**
	 * Store the '.sf' file for a given dataObject
	 * 
	 * @param library
	 */
	public void store(SFDataObject dataObject) {

		String filename = getFilename();

		SFDataUtility.saveDataObject(root, filename + ".sf", dataObject);
	}
	
	/**
	 * Store the '.sf' file for a given dataObject
	 * 
	 * @param library
	 */
	public void store(SFDataObject dataObject,String elementName) {

		String filename = getFilename();

		SFDataUtility.saveDataObject(root, filename + ".sf", dataObject);

		SFDataUtility.saveXMLFile(root, filename, elementName, dataObject);
	}
		

	/**
	 * Build data for testing and prepare it to be stored. Refer to each test to
	 * know which data is built up.
	 */
	public abstract void buildTestData();

	/**
	 * Reload testData and View them using SFViewers. Refer to each test to know
	 * which data is shown.
	 */
	public abstract void viewTestData();

	/**
	 * @return a name for this tests. This test files will be called
	 *         filename+'.sf' and filename+'.xml'
	 */
	public abstract String getFilename();

	/**
	 * Load the default library for this test (getFilename()+".sf") ad a
	 * DataCenter for viewing purposes. Only tests building/viewing
	 * ObjectsLibrary should use this.
	 */
	public void loadLibraryAsDataCenter() {
		
		if(!loadLibrariesAsXml){
			SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root, getFilename()
					+ ".sf"));	
		}else{
			SFViewerObjectsLibrary library=new SFViewerObjectsLibrary();
			
			SFObjectsLibraryDecoder decoder=new SFObjectsLibraryDecoder(library.getLibrary());
			
			SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
			interpreter.generateInterpretation(root+"/"+getFilename()+".xml");
			
			// 3) Retrieve the library and make model available so that it can be added to a Viewer
			SFDataCenter.setDataCenterImplementation(library);
			
		}
	}

	/**
	 * Copy an array of assets from one library to another.
	 * 
	 * @param fromLibraryFileName The name of the library from which you want to load your Assets. Don't put '.sf' in file filename.
	 * @param toLibrary The {@link SFObjectsLibrary} (usually, it will be SFAbstractTest.library, the default {@link SFObjectsLibrary} of each test)
	 * @param assetsName the name of the asset in the old library.
	 */
	public void copyAssets(String fromLibraryFileName,SFObjectsLibrary toLibrary,String... assetsName){

		SFViewerObjectsLibrary oldLibrary=new SFViewerObjectsLibrary(root, fromLibraryFileName+".sf");
		
		for (int i = 0; i < assetsName.length; i++) {
			String assetName=assetsName[i];
			toLibrary.put(assetName, oldLibrary.getLibrary().retrieveDataset(assetName));
		}
		
	}
	
	/**
	 * Copy an entire library
	 * 
	 * @param fromLibraryFileName The name of the library from which you want to load your Assets. Don't put '.sf' in file filename.
	 * @param toLibrary The {@link SFObjectsLibrary} (usually, it will be SFAbstractTest.library, the default {@link SFObjectsLibrary} of each test)
	 */
	public void copyAssets(String fromLibraryFileName,SFObjectsLibrary toLibrary){

		SFViewerObjectsLibrary oldLibrary=new SFViewerObjectsLibrary(root, fromLibraryFileName+".sf");
		
		for (RecordData recordData : oldLibrary.getLibrary()) {
			System.err.println(recordData.getName());
			toLibrary.put(recordData.getName(), oldLibrary.getLibrary().retrieveDataset(recordData.getName()));
		}
		
	}
	
	/**
	 * Show a model already loaded into DataCenter in the SFViewer Frame , using default Frame Controllers.
	 * @param nodeName the name of the model
	 */
	public void viewNode(String nodeName){
		SFDataCenter.getDataCenter().makeDatasetAvailable(nodeName, new SFDataCenterListener<SFDataAsset<SFNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
				SFViewer.generateFrame(dataset.getResource(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getWireframeController(),
						SFViewer.getZoomController());
			}
		});
	}
	
	/**
	 * Show a model already loaded into DataCenter in the SFViewer Frame , using assigned Frame Controllers.
	 * @param nodeName the name of the model
	 * @param controllers the controllers this frame should use
	 */
	public void viewNode(String nodeName,final SFFrameController... controllers){
		SFDataCenter.getDataCenter().makeDatasetAvailable(nodeName, new SFDataCenterListener<SFDataAsset<SFNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
				SFViewer.generateFrame(dataset.getResource(),controllers);
			}
		});
	}
	
	public String[] getAllNamesInLibraryOfOneType(String... type){
		
		ArrayList<String> names=new ArrayList<String>();
		for (RecordData record : library) {
			for (int i = 0; i < type.length; i++) {
				if(record.getDataset().getClass().getSimpleName().equalsIgnoreCase(type[i])){
					names.add(record.getName());
				}
			}
		}
		return names.toArray(new String[names.size()]);
	}
	

	/**
	 * Show a textures Set already loaded into DataCenter in the SFViewer Frame , using default Frame Controllers.
	 * @param nodeName the name of the model
	 * @param startingTextureIndex the texture index to be used when the frame is shown
	 */
	public void viewTextureSet(String textureSetName,final int startingTextureIndex){
		
		SFDataCenter.getDataCenter().makeDatasetAvailable(textureSetName, new SFDataCenterListener<SFDataAsset<SFRenderedTexturesSet>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFRenderedTexturesSet> dataset) {
				SFRenderedTexturesSet set=dataset.getResource();
				SFTexture texture=new SFTexture(set, startingTextureIndex);
				SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, set.getTextureSize()));
			}
		});	
	}

	public void compileAndLoadXmlFile(String xmlFileName) {
		
		SFObjectsLibraryDecoder decoder=new SFObjectsLibraryDecoder(library);
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation(rootInput+"/"+xmlFileName+".xml");
	}
}
