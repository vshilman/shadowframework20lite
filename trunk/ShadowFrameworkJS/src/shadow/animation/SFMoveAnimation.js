



function SFMoveAnimation(startingPosition,endingPosition,duration,startingTime,
	transformNode,tweener){
	this.transformNode=transformNode;
	this.duration=duration;
	this.startingTime=startingTime;
	this.startingPosition=startingPosition;
	this.endingPosition=endingPosition;
	this.tweener=tweener; 
}

inherit(SFMoveAnimation,SFTransformNodeAnimation);

SFMoveAnimation.prototype["clone"]=function(time){
	return new SFMoveAnimation(this.startingPosition, this.endingPosition, this.getDuration(),
				this.getStartingTime(), this.getTransformNode(),this.getTweener());
};

SFMoveAnimation.prototype["applyTransform"]=function(T){
	var position=this.startingPosition.cloneV();
		position.mult(T);
		position.addMult((1-T),this.endingPosition);
		this.getTransformNode().getTransform().setPosition(position);
};
