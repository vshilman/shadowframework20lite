package shadow.animation.data;

import shadow.animation.SFAnimation;
import shadow.animation.SFCompositeAnimation;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetList;
import shadow.system.data.SFNamedParametersObject;

public class SFCompositeAnimationData extends SFDataAsset<SFAnimation>{

	private SFDataAssetList<SFAnimation> animations;
	
	public SFCompositeAnimationData() {
		animations=new SFDataAssetList<SFAnimation>();
		SFNamedParametersObject namedParameters=new SFNamedParametersObject();
		namedParameters.addObject("animations", animations);
		setData(namedParameters);
	}
	
	public void addAnimation(SFDataAsset<SFAnimation> animation){
		animations.add(animation);
	}
	
	@Override
	protected SFCompositeAnimation buildResource() {
		SFCompositeAnimation animation=new SFCompositeAnimation();
		
		for (int i = 0; i < animations.size(); i++) {
			animation.add(animations.get(i).getResource());
		}
		
		return animation;
	}
}
