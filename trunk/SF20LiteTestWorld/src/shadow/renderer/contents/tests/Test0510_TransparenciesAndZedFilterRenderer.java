package shadow.renderer.contents.tests;

import shadow.animation.SFAnimation;
import shadow.animation.SFAnimator;
import shadow.image.SFDrawnRenderedTexture;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.viewer.SFAnimationTimer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0510_TransparenciesAndZedFilterRenderer extends SFAbstractTest{

	private static final String FILENAME="test0510";
	
	private SFDrawnRenderedTexture set;
	
	/***/
	public static void main(String[] args) {
		execute(new Test0510_TransparenciesAndZedFilterRenderer());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewTextureSet("Scene0001RenderedTexture", 0);
		
		set=getAlreadyAvailableDatasetResource("Scene0001RenderedTexture");
	
		SFDataCenter.getDataCenter().makeDatasetAvailable("Scene0001Rotation", new SFDataCenterListener<SFDataAsset<SFAnimation>>() {
			public void onDatasetAvailable(String name, SFDataAsset<SFAnimation> dataset) {
				SFAnimation composite=dataset.getResource();
				SFAnimator.addAnimation(composite);
			}
		});
		
		SFAnimator.addAnimation(new SFAnimation() {
			@Override
			public void init() {
				
			}
			@Override
			public SFAnimation clone() {
				return null;
			}
			
			@Override
			public void destroy() {
				
			}
			
			@Override
			public void animate(long time) {
				//if(set!=null)
				//	SFUpdater.addUpdatable(set);
			}
		});
		//SFUpdater.addUpdatable(set);
		
		SFAnimationTimer.startTimer();
		
//		SFTexture texture=new SFTexture(set, 0);
//		SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, set.getTextureSize()));
		
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0510Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
}
