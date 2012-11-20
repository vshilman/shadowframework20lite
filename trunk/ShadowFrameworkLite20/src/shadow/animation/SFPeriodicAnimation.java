package shadow.animation;

/**
 * A Decorator which takes any animation and makes it periodic.
 * 
 * @author Alessandro Martinelli
 */
public class SFPeriodicAnimation implements SFAnimation{

	private SFAnimation animation;
	private long period;
	private long startingTime;
	
	
	public SFPeriodicAnimation(SFAnimation animation, long period, long startingTime) {
		super();
		this.animation = animation;
		this.period = period;
		this.startingTime = startingTime;
	}
	
	@Override
	public SFAnimation clone() {
		return new SFPeriodicAnimation(animation, period, startingTime);
	}


	public SFAnimation getAnimation() {
		return animation;
	}


	public long getPeriod() {
		return period;
	}


	public long getStartingTime() {
		return startingTime;
	}
	
	
	@Override
	public void animate(long time) {
		time=(time-startingTime)%period;
		animation.animate(time);
	}

	@Override
	public void destroy() {
		//nothing to do
	}
	
	@Override
	public void init() {
		//nothing to do
	}


}
