package shadow.animation;

import shadow.renderer.SFTransformKeeper;

public abstract class SFTransformNodeAnimation implements SFAnimation{

	public SFTweener tweener;
	private SFTransformKeeper transformNode;
	private long duration;
	private long startingTime;
	
	public SFTransformNodeAnimation(SFTransformKeeper transformNode) {
		super();
		this.transformNode=transformNode;
	}

	public SFTransformNodeAnimation(SFTransformKeeper transformNode, long duration, long startingTime,SFTweener tweener) {
		super();
		this.tweener = tweener;
		this.transformNode = transformNode;
		this.duration = duration;
		this.startingTime = startingTime;
	}

	public SFTransformNodeAnimation(SFTransformKeeper transformNode,
			long duration, long startingTime) {
		super();
		this.transformNode = transformNode;
		this.duration = duration;
		this.startingTime = startingTime;
	}



	public SFTransformKeeper getTransformNode() {
		return transformNode;
	}

	public void setTransformNode(SFTransformKeeper transformNode) {
		this.transformNode = transformNode;
	}

	@Override
	public abstract SFTransformNodeAnimation clone();

	protected abstract void applyTransform(double T);
	


	@Override
	public void destroy() {
		//nothing to do
	}
	
	@Override
	public void init() {
		//nothing to do
	}

	protected SFTweener getTweener() {
		return tweener;
	}

	protected void setTweener(SFTweener tweener) {
		this.tweener=tweener;
	}

	protected void setDuration(long duration) {
		this.duration = duration;
	}

	protected long getDuration() {
		return duration;
	}

	protected void setStartingTime(long startingTime) {
		this.startingTime = startingTime;
	}

	protected long getStartingTime() {
		return startingTime;
	}

	public void animate(long time) {
		
		if(getTransformNode()!=null){
			if(time>=startingTime && time<=startingTime+duration){

				double T=(time-startingTime)/(1.0f*duration);

				if(tweener!=null)
					T=tweener.tweenValue(T);
				applyTransform(T);
			}
		}
	}
}