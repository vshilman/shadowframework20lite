package shadow.renderer.contents.tests;

import shadow.animation.SFAnimator;
import shadow.animation.data.SFCompositeAnimationData;
import shadow.animation.data.SFMoveAnimationData;
import shadow.animation.data.SFPeriodicAnimationData;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFInstancedReferenceNodeData;
import shadow.renderer.viewer.SFAnimationTimer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0131_AnimationSimpleUpAndDownStore extends SFAbstractTest{

	private static final String FILENAME="test0131";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0131_AnimationSimpleUpAndDownStore());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();

		SFAnimationTimer.startTimer();
		
		viewNode("InstancedNode");

		SFDataCenter.getDataCenter().makeDatasetAvailable("PerlinMushroomUpAndDown", new SFDataCenterListener<SFPeriodicAnimationData>() {
			public void onDatasetAvailable(String name, SFPeriodicAnimationData upAndDownAnimation) {
				SFAnimator.addAnimation(upAndDownAnimation.getResource());
			}
		});
	}
	
	@Override
	public void buildTestData() {
		
		copyAssets("test0004", library);	
			
		SFMoveAnimationData animationDown = new SFMoveAnimationData();
		animationDown.setup("InstancedNode", new SFVertex3f(0,0,0),
						new SFVertex3f(0,-0.4f,0),2000,0);
		//Missing control on tweener, default is SFStandardTweeners.CUBIC
		SFMoveAnimationData animationUp = new SFMoveAnimationData();
		animationUp.setup("InstancedNode", new SFVertex3f(0,-0.4f,0),
				new SFVertex3f(0,0,0),2000,2000);
		
		SFCompositeAnimationData compositeAnimation=new SFCompositeAnimationData();
		compositeAnimation.addAnimation(animationDown);
		compositeAnimation.addAnimation(animationUp);
		
		SFPeriodicAnimationData upAndDownAnimation=new SFPeriodicAnimationData();
			upAndDownAnimation.setup(compositeAnimation, 0, 4000);
			library.put("PerlinMushroomUpAndDown", upAndDownAnimation);

		SFInstancedReferenceNodeData referenceNode=new SFInstancedReferenceNodeData();
			referenceNode.addNode("PerlinMushroom");
			library.put("InstancedNode", referenceNode);

		store(library);
	}
}

