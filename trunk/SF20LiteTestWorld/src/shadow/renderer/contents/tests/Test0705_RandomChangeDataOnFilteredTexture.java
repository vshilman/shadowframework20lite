package shadow.renderer.contents.tests;

import java.util.Timer;
import java.util.TimerTask;

import shadow.geometry.SFGeometry;
import shadow.image.SFFilteredRenderedTexture;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.SFTextureDataToken;
import shadow.math.SFVertex3f;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFInitiator;
import shadow.system.SFUpdatable;
import shadow.system.SFUpdater;

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
public class Test0705_RandomChangeDataOnFilteredTexture extends SFAbstractTest{

	private static final String FILENAME="test0705";
	
	private static SFReferenceNode sceneNode;
	private static SFObjectModel model01;
	private static SFObjectModel model02;
	private static SFObjectModel model03;
	private static SFFilteredRenderedTexture renderedTexture;
	
	
	public static void main(String[] args) {
		execute(new Test0705_RandomChangeDataOnFilteredTexture());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		sceneNode=new SFReferenceNode();
		
		model01 = createMushroom("Texture");
		model01.getTransform().setPosition(new SFVertex3f(0,-0.5f,0));
		//model01.getTransform().setOrientation(SFMatrix3f.getAmpl(0.5f, 0.5f,0.5f));
		sceneNode.addNode(model01);

		model02 = createMushroom("Texture");
		model02.getTransform().setPosition(new SFVertex3f(-0.5f,0,0));
		//model02.getTransform().setOrientation(SFMatrix3f.getAmpl(0.5f, 0.5f,0.5f));
		sceneNode.addNode(model02);
		
		model03 = createMushroom("PerlinTexture");
		model03.getTransform().setPosition(new SFVertex3f(0,0.5f,0));
		//model03.getTransform().setOrientation(SFMatrix3f.getAmpl(0.5f, 0.5f,0.5f));
		sceneNode.addNode(model03);

		int time=(int)(Math.random()*4000)+2000;
		
		Timer t=new Timer();
		
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				SFUpdater.addUpdatable(new SFUpdatable() {
					
					@Override
					public void update() {

						renderedTexture=new SFFilteredRenderedTexture();
						
						renderedTexture.getMaterialComponent().setProgram("TexturedMat");
						SFRenderedTexturesSet defaultTexture=getAlreadyAvailableDatasetResource("Texture");
						renderedTexture.getMaterialComponent().getTextures().add(new SFTexture(defaultTexture, 0));
						renderedTexture.getLightComponent().setProgram("ReddishGrayAndBright");
						
						SFPipelineTexture[] textures={new SFTextureDataToken(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT),
								new SFTextureDataToken(200, 200, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT)};

						renderedTexture.setTextures(textures);
						
						SFInitiator.addInitiable(renderedTexture);
						
						model01.getModel().getMaterialComponent().getTextures().get(0).setTexturesSet(renderedTexture);
						model01.getModel().getMaterialComponent().getTextures().get(0).setIndex(0);
						model02.getModel().getMaterialComponent().getTextures().get(0).setTexturesSet(renderedTexture);
						model02.getModel().getMaterialComponent().getTextures().get(0).setIndex(1);
						
					}
				});
				
			}
		}, time);
		
		
		SFViewer.generateFrame(sceneNode,SFViewer.getLightStepController());
		
	}

	private SFObjectModel createMushroom(String textureName) {
		SFObjectModel model=new SFObjectModel();
		
		SFGeometry geometry=getAlreadyAvailableDatasetResource("TexturedMushroom");
		model.getModel().setRootGeometry(geometry);
		
		model.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
		model.getModel().getMaterialComponent().setProgram("TexturedMat");
		SFRenderedTexturesSet defaultTexture=getAlreadyAvailableDatasetResource(textureName);
		model.getModel().getMaterialComponent().getTextures().add(new SFTexture(defaultTexture, 0));
		return model;
	}

	public void buildTestData() {
		copyAssets("test0006", library);
		copyAssets("default", library);
		store(library);
	}

}
