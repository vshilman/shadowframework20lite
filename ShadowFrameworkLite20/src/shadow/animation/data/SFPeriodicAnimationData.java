package shadow.animation.data;

import shadow.animation.SFAnimation;
import shadow.animation.SFPeriodicAnimation;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetObject;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFInt;

public class SFPeriodicAnimationData extends SFDataAsset<SFAnimation>{

	private SFDataAssetObject<SFAnimation> animation=
			new SFDataAssetObject<SFAnimation>(null);
	private SFInt startTime=new SFInt(0);
	private SFInt period=new SFInt(0);
	
	public SFPeriodicAnimationData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("startingPosition", startTime);
		parameters.addObject("endingPosition", period);
		parameters.addObject("animation", animation);
		setData(parameters);
	}
	
	public void setup(SFDataAsset<SFAnimation> animation,int startTime,int period){
		this.startTime.setIntValue(startTime);
		this.period.setIntValue(period);
		this.animation.setDataset(animation);
	}
	
	@Override
	protected SFAnimation buildResource() {
		SFPeriodicAnimation periodicAnimation=new SFPeriodicAnimation(animation.getDataset().getResource(),
				period.getIntValue(),startTime.getIntValue());
		
		return periodicAnimation;
	}
}


