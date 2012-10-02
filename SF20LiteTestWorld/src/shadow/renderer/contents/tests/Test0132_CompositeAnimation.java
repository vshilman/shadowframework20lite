package shadow.renderer.contents.tests;

import shadow.animation.SFAnimation;
import shadow.animation.SFAnimator;
import shadow.animation.data.SFCompositeAnimationData;
import shadow.animation.data.SFMoveAnimationData;
import shadow.animation.data.SFPeriodicAnimationData;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFInstancedReferenceNodeData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.transforms.SFRigidTransformData;
import shadow.renderer.data.transforms.SFTranslateFixed16Data;
import shadow.renderer.viewer.SFAnimationTimer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0132_CompositeAnimation  extends SFAbstractTest{

	private static final String FILENAME="test0132";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0132_CompositeAnimation());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		viewNode("MushroomsScene01b");
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomsScene01Animations", new SFDataCenterListener<SFDataAsset<SFAnimation>>() {
			public void onDatasetAvailable(String name, SFDataAsset<SFAnimation> dataset) {
				SFAnimator.addAnimation(dataset.getResource());
			}
		});
		
		SFAnimationTimer.startTimer();
	}
	
	@Override
	public void buildTestData() {
		
		copyAssets("test0002", library,"Mushroom","BasicMatColours");
			
		SFReferenceNodeData scene=new SFReferenceNodeData();
		
		SFCompositeAnimationData compositeAnimation=new SFCompositeAnimationData();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int index=i*4+j;
				String name="ColouredMushroom"+index;
				float x=-0.7f+0.45f*j;
				float y=-0.85f+0.45f*i;
				
				SFObjectModelData objectModel1=new SFObjectModelData();
				objectModel1.setGeometry("Mushroom");
				objectModel1.setTransform(new SFRigidTransformData(0,0,0,0.6f,SFMatrix3f.getRotationY((float)(index*Math.PI/8))));
				objectModel1.getMaterialComponent().addStructure("BasicMatColours", index);
				objectModel1.getMaterialComponent().setProgram("BasicMat");
				objectModel1.getTransformComponent().setProgram("BasicPNTransform");
				library.put(name, objectModel1);
				
				String namePlace=name+"Place";
				SFInstancedReferenceNodeData placeReferenceNodeData=new SFInstancedReferenceNodeData();
				placeReferenceNodeData.setTransform(new SFTranslateFixed16Data(x, y, 0));
				placeReferenceNodeData.addNode(name);
				library.put(namePlace,placeReferenceNodeData);
				
				scene.addNode(namePlace);
				
				SFVertex3f position1=new SFVertex3f(x, y, 0);
				SFVertex3f secondPosition=new SFVertex3f(position1);
				secondPosition.add3f(new SFVertex3f(0,0.1f,0));
				
				int startTime=(int)(Math.random()*500);
				int firstDuration=(int)(Math.random()*2000)+800;
				int delay=(int)(Math.random()*500);
				int secondDuration=(int)(Math.random()*2000)+800;
				int waitTime=(int)(Math.random()*500);
				
					SFMoveAnimationData animationDown = new SFMoveAnimationData();
					animationDown.setup(namePlace, position1,
							secondPosition,firstDuration,startTime);
					SFMoveAnimationData animationUp = new SFMoveAnimationData();
					int secondStartTime=startTime+firstDuration+delay;
					animationUp.setup(namePlace, secondPosition,
							position1,secondDuration,secondStartTime);
					
					SFCompositeAnimationData ownCompositeAnimation=new SFCompositeAnimationData();
					ownCompositeAnimation.addAnimation(animationDown);
					ownCompositeAnimation.addAnimation(animationUp);
					
					SFPeriodicAnimationData upAndDownAnimation=new SFPeriodicAnimationData();
						upAndDownAnimation.setup(ownCompositeAnimation, 0, secondStartTime+secondDuration+waitTime);
						
						compositeAnimation.addAnimation(upAndDownAnimation);
			}
		}	
		
		library.put("MushroomsScene01b", scene);
		library.put("MushroomsScene01Animations",compositeAnimation);
		
		store(library);

	}

}

