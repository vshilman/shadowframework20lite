


function SFRotateAnimation(direction, firstAngle, lastAngle,duration,startingTime,
	transformNode,tweener){
	this.transformNode=transformNode;
	this.duration=duration;
	this.startingTime=startingTime;
	this.direction=direction;
	this.firstAngle=firstAngle;
	this.lastAngle=lastAngle;
	this.tweener=tweener; 
}

inherit(SFRotateAnimation,SFTransformNodeAnimation);

SFRotateAnimation.prototype["clone"]=function(time){
	return new SFRotateAnimation(this.direction, this.firstAngle, this.lastAngle, this.getDuration(),
				this.getStartingTime(), this.getTransformNode(),this.getTweener());
};

SFRotateAnimation.prototype["applyTransform"]=function(T){
	var angle=this.firstAngle*(1-T)+this.lastAngle*T;
	var quaternion = new SFQuaternion(this.direction,angle);
		
	this.getTransformNode().getTransform().setOrientation(quaternion.getRotationMatrix());
};
