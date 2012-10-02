package shadow.animation.data;

import shadow.animation.SFAnimation;
import shadow.animation.SFRotateAnimation;
import shadow.animation.SFStandardTweeners;
import shadow.animation.SFTransformNodeAnimation;
import shadow.math.SFVertex3f;
import shadow.renderer.SFTransformNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFVertex3fData;

public class SFRotateAnimationData extends SFDataAsset<SFAnimation>{

	private SFVertex3fData direction=new SFVertex3fData();
	private SFFloat startAngle=new SFFloat(0);
	private SFFloat lastAngle=new SFFloat(0);
	private SFInt duration=new SFInt(0);
	private SFInt startingTime=new SFInt(0);
	private SFLibraryReference<SFTransformNode> node=new SFLibraryReference<SFTransformNode>();
	
	public SFRotateAnimationData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("direction", direction);
		parameters.addObject("startAngle", startAngle);
		parameters.addObject("lastAngle", lastAngle);
		parameters.addObject("duration", duration);
		parameters.addObject("startingTime", startingTime);
		parameters.addObject("node", node);
		setData(parameters);
		setReferences(node);
	}
	
	@Override
	protected SFAnimation buildResource() {
		
		final SFTransformNodeAnimation moveAnimation=new SFRotateAnimation(direction.getVertex3f(),
				startAngle.getFloatValue(),lastAngle.getFloatValue(),duration.getIntValue(),startingTime.getIntValue(),
				null,SFStandardTweeners.CUBIC);
		
		node.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFTransformNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFTransformNode> dataset) {
				moveAnimation.setTransformNode(dataset.getResource());
			}
		});
		
		return moveAnimation;
	}
	
	public void setup(SFDataAsset<SFTransformNode> nodeAsset,SFVertex3f direction, 
			float startAngle, float lastAngle, int duration,
			int startingTime) {
		this.node.setDataset(nodeAsset);
		this.direction.getVertex3f().set(direction);
		this.startAngle.setFloatValue(startAngle);
		this.lastAngle.setFloatValue(lastAngle);
		this.duration.setIntValue((int)duration);
		this.startingTime.setIntValue((int)startingTime);
		
	}
	
	public void setup(String name,SFVertex3f direction, 
			float startAngle, float lastAngle, int duration,
			int startingTime) {
		this.node.setReference(name);
		this.direction.getVertex3f().set(direction);
		this.startAngle.setFloatValue(startAngle);
		this.lastAngle.setFloatValue(lastAngle);
		this.duration.setIntValue((int)duration);
		this.startingTime.setIntValue((int)startingTime);
		
	}
	
}
