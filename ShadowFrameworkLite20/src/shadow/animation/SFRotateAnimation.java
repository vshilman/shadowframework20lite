package shadow.animation;

import shadow.math.SFQuaternion;
import shadow.math.SFVertex3f;
import shadow.renderer.SFTransformNode;

public class SFRotateAnimation extends SFTransformNodeAnimation implements SFAnimation{

	private SFVertex3f direction;
	private float firstAngle;
	private float lastAngle;
	
	public SFRotateAnimation(SFVertex3f direction, float firstAngle, float lastAngle, long duration,
			long startingTime, SFTransformNode transformNode) {
		super(transformNode,duration,startingTime);
		this.direction = direction;
		this.firstAngle = firstAngle;
		this.lastAngle = lastAngle;
	}

	public SFRotateAnimation(SFVertex3f direction, float firstAngle, float lastAngle, long duration,
			long startingTime, SFTransformNode transformNode, SFTweener tweener) {
		super(transformNode,duration,startingTime,tweener);
		this.direction = direction;
		this.firstAngle = firstAngle;
		this.lastAngle = lastAngle;
	}
	
	@Override
	public SFRotateAnimation clone() {
		return new SFRotateAnimation(direction,firstAngle,lastAngle,getDuration(),getStartingTime(),
				getTransformNode(),getTweener());
	}

	@Override
	protected void applyTransform(double T) {
		double angle=firstAngle*(1-T)+lastAngle*T;

		SFQuaternion quaternion = new SFQuaternion(this.direction,angle);
		
		getTransformNode().getTransform().setOrientation(quaternion.getRotationMatrix());
	}
	
}