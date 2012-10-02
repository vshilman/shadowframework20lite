package shadow.renderer.contents.tests;

import shadow.animation.SFAnimation;
import shadow.animation.SFAnimator;
import shadow.animation.SFCompositeAnimation;
import shadow.animation.SFMoveAnimation;
import shadow.animation.SFPeriodicAnimation;
import shadow.animation.SFStandardTweeners;
import shadow.math.SFVertex3f;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFAnimationTimer;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0130_AnimationSimpleUpAndDown{

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		CommonPipeline.prepare();
		
		SFAnimationTimer.startTimer();

		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0004.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				
				SFAnimation animationDown=new SFMoveAnimation(new SFVertex3f(0,0,0),
						new SFVertex3f(0,-0.4f,0),2000,0,model,SFStandardTweeners.CUBIC);
				SFAnimation animationUp=new SFMoveAnimation(new SFVertex3f(0,-0.4f,0),
						new SFVertex3f(0,0,0),2000,2000,model,SFStandardTweeners.CUBIC);
				SFCompositeAnimation compositeAnimation=new SFCompositeAnimation();
				compositeAnimation.add(animationDown);
				compositeAnimation.add(animationUp);
				SFPeriodicAnimation upAndDownAnimation=new SFPeriodicAnimation(compositeAnimation, 4000, 0);
				
				SFAnimator.addAnimation(upAndDownAnimation);
				
				SFViewer.generateFrame(model,SFViewer.getLightStepController());
			}
		});
		
	}

}

