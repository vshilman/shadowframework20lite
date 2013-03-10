package shadow.renderer.contents.tests;

import java.util.Timer;
import java.util.TimerTask;

import shadow.image.SFDrawnRenderedTexture;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFUpdatable;
import shadow.system.SFUpdater;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

/**
 * If you need to align this test Data, run the {@link SFGenerateAllTestData} utility once.
 * No data will be generated (so nothing will work) until you do that; as an
 * alternative, you can set SFAbstractTest.storeData to true, and then run each test
 * one by one in test number order.
 * <br/>
 * Go to {@link SFAbstractTest} for general informations about this tests.
 * <br/>
 * Open the related FILENAME.xml file for a detailed view of this test contents. 
 * <br/>
 * Objective: verify the capability to store an Object Model, load it and show
 * it on a Viewer. This Test is different from Test0001, because we are
 * storing a whole SFObjectModel and not simply its geometry.
 * 
 * TODO
 * 
 * @author Alessandro Martinelli
 */
public class Test0706_RandomChangeData extends SFAbstractTest{

	private static final String FILENAME="test0706";
	
	public static void main(String[] args) {
		execute(new Test0706_RandomChangeData());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		final SFReferenceNode node=getAlreadyAvailableDatasetResource("PebblesTextureModel");
		java.util.Iterator<SFNode> iterator=node.iterator();
		iterator.next();
		final SFNode pebbles=iterator.next();
		node.removeNode(pebbles);
		
		final SFDrawnRenderedTexture texture=getAlreadyAvailableDatasetResource("PebblesTextures");

		SFDataCenter.getDataCenter().makeDatasetAvailable("StoneMushroom", new SFDataCenterListener<SFDataAsset<SFNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
				SFViewer.generateFrame(dataset.getResource(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getWireframeController(),
						SFViewer.getZoomController());
			}
		});
		
		int time=(int)(Math.random()*4000)+2000;
		
		Timer t=new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				SFUpdater.addUpdatable(new SFUpdatable() {
					@Override
					public void update() {
						node.addNode(pebbles);
						texture.update();
					}
				});
			}
		}, time);
	}
	
	@Override
	public void buildTestData() {
		
		copyAssets("test0015", library,  "OriginalNoise", "PerlinTexture", "PerlinTexture2",
				"PebblesModel","FullScreenRectangle",
				"PebblesTextureModel","PebblesGround",
				"PebblesTextures","BumpMappingsMushroom");

		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("BumpMappingsMushroom");
			objectModel.setTransform(new SFTranslateAndScaleFixed16Data(0, -0.6f, 0, 2.4f));
			objectModel.getTransformComponent().setProgram("BasicBumpMapTransform");
			objectModel.getMaterialComponent().setProgram("ImprovedBumpMappedMat");
			objectModel.getMaterialComponent().addTexture( 1, "PebblesTextures");
			objectModel.getMaterialComponent().addTexture( 0, "PebblesTextures");
			library.put("StoneMushroom", objectModel);
			
		store(library);
	}

}
