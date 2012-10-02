package shadow.animation.data;

import shadow.animation.SFAnimation;
import shadow.animation.SFMoveAnimation;
import shadow.animation.SFStandardTweeners;
import shadow.animation.SFTransformNodeAnimation;
import shadow.math.SFVertex3f;
import shadow.renderer.SFTransformNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFVertex3fData;

public class SFMoveAnimationData extends SFDataAsset<SFAnimation>{

	private SFVertex3fData startingPosition=new SFVertex3fData();
	private SFVertex3fData endingPosition=new SFVertex3fData();
	private SFInt duration=new SFInt(0);
	private SFInt startingTime=new SFInt(0);
	private SFLibraryReference<SFTransformNode> node=new SFLibraryReference<SFTransformNode>();
	
	public SFMoveAnimationData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("startingPosition", startingPosition);
		parameters.addObject("endingPosition", endingPosition);
		parameters.addObject("duration", duration);
		parameters.addObject("startingTime", startingTime);
		parameters.addObject("node", node);
		setData(parameters);
		setReferences(node);
	}
	
	@Override
	protected SFAnimation buildResource() {
		
		final SFTransformNodeAnimation moveAnimation=new SFMoveAnimation(startingPosition.getVertex3f(),
				endingPosition.getVertex3f(),duration.getIntValue(),startingTime.getIntValue(),
				null,SFStandardTweeners.CUBIC);
		
		node.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFTransformNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFTransformNode> dataset) {
				moveAnimation.setTransformNode(dataset.getResource());
			}
		});
		
		return moveAnimation;
	}
	
	public void setup(SFDataAsset<SFTransformNode> nodeAsset,SFVertex3f startingPosition, SFVertex3f endingPosition, int duration,
			int startingTime) {
		this.node.setDataset(nodeAsset);
		this.startingPosition.getVertex3f().set(startingPosition);
		this.endingPosition.getVertex3f().set(endingPosition);
		this.duration.setIntValue((int)duration);
		this.startingTime.setIntValue((int)startingTime);
		
	}
	
	public void setup(String name,SFVertex3f startingPosition, SFVertex3f endingPosition, int duration,
			int startingTime) {
		this.node.setReference(name);
		this.startingPosition.getVertex3f().set(startingPosition);
		this.endingPosition.getVertex3f().set(endingPosition);
		this.duration.setIntValue((int)duration);
		this.startingTime.setIntValue((int)startingTime);
		
	}
	
}
