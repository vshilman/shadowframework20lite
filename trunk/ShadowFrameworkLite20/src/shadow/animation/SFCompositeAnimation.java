package shadow.animation;

import java.util.ArrayList;

public class SFCompositeAnimation extends ArrayList<SFAnimation> implements SFAnimation{

	private static final long serialVersionUID = (new SFCompositeAnimation()).getClass().hashCode();
	
	@Override
	public void animate(long time) {
		for (int i = 0; i < size(); i++) {
			get(i).animate(time);
		}
	}
	
	@Override
	public SFCompositeAnimation clone() {
		SFCompositeAnimation clone=new SFCompositeAnimation();
		for (SFAnimation sfAnimation : this) {
			SFAnimation animation=sfAnimation.clone();
			clone.add(animation);
		}
		// TODO yea, but in this way you are not going anywhere..bn
		return clone;
	}
	
	@Override
	public void destroy() {
		//nothing to do
	}
	
	
	@Override
	public void init() {
		//nothing to do
	}

	protected SFTweener getTweener() {
		return null;
	}

	protected void setTweener(SFTweener tweener) {
	}

	protected void applyTransform(double T) {
	}
}
