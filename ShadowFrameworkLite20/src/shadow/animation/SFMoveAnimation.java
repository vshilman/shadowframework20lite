package shadow.animation;

import shadow.math.SFVertex3f;
import shadow.renderer.SFTransformKeeper;

/**
 * Generate a move animation.
 * 
 * @author Alessandro
 */
public class SFMoveAnimation extends SFTransformNodeAnimation implements SFAnimation{

	private SFVertex3f startingPosition;
	private SFVertex3f endingPosition;
	
	public SFMoveAnimation(SFVertex3f startingPosition, SFVertex3f endingPosition, long duration,
			long startingTime, SFTransformKeeper transformNode) {
		super(transformNode,duration,startingTime);
		this.startingPosition = startingPosition;
		this.endingPosition = endingPosition;
	}

	public SFMoveAnimation(SFVertex3f startingPosition, SFVertex3f endingPosition, long duration,
			long startingTime, SFTransformKeeper transformNode, SFTweener tweener) {
		super(transformNode,duration,startingTime,tweener);
		this.startingPosition = startingPosition;
		this.endingPosition = endingPosition;
	}
	
	@Override
	public SFTransformNodeAnimation clone() {
		return new SFMoveAnimation(startingPosition, endingPosition, getDuration(),
				getStartingTime(), getTransformNode(),getTweener());
	}

	@Override
	protected void applyTransform(double T) {
		SFVertex3f position=startingPosition.cloneV();
		position.mult(((float)T));
		position.addMult((1-(float)T),endingPosition);
		getTransformNode().getTransform().setPosition(position);
	}
	
}
