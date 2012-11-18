

function SFTransformNodeAnimation(transformNode, duration, startingTime, tweener) {
		
	this.transformNode=transformNode;
	if(duration===undefined)
		duration=0;
	if(startingTime===undefined)
		startingTime=0;
	if(tweener===undefined)
		tweener=0;
	this.tweener = tweener;
	this.duration = duration;
	this.startingTime = startingTime;
}


SFTransformNodeAnimation.prototype["getTransformNode"]=function(){
	return this.transformNode;	
}

SFTransformNodeAnimation.prototype["setTransformNode"]=function(transformNode){
	this.transformNode = transformNode;	
}


SFTransformNodeAnimation.prototype["destroy"]=function(){
		//nothing to do
}

SFTransformNodeAnimation.prototype["init"]=function(){
		//nothing to do
}


SFTransformNodeAnimation.prototype["getTweener"]=function(){
	return this.tweener;	
}

SFTransformNodeAnimation.prototype["setTweener"]=function(tweener){
	this.tweener = tweener;	
}

SFTransformNodeAnimation.prototype["getDuration"]=function(){
	return this.duration;	
}

SFTransformNodeAnimation.prototype["setDuration"]=function(duration){
	this.duration = duration;	
}

	
SFTransformNodeAnimation.prototype["getStartingTime"]=function(){
	return this.startingTime;	
}

SFTransformNodeAnimation.prototype["setStartingTime"]=function(startingTime){
	this.startingTime = startingTime;	
}

SFTransformNodeAnimation.prototype["animate"]=function(time){
	
		if(this.getTransformNode()!=null){
			if(time>=this.startingTime && time<=this.startingTime+this.duration){

				var T=(this.time-this.startingTime)/(1.0*this.duration);

				if(tweener!=null)
					T=this.tweener.tweenValue(T);
				this.applyTransform(T);
			}
		}
}
