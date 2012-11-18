


function SFPeriodicAnimation(period,startingTime,
	animation){
	this.startingTime=startingTime;
	this.period=period;
	this.animation=animation; 
}

SFPeriodicAnimation.prototype["clone"]=function(){
		return new SFPeriodicAnimation(this.animation, this.period, this.startingTime);
};


SFPeriodicAnimation.prototype["getAnimation"]=function(){
		return this.animation;
};

SFPeriodicAnimation.prototype["getPeriod"]=function(){
		return this.period;
};

SFPeriodicAnimation.prototype["getStartingTime"]=function(){
		return this.startingTime;
};

SFPeriodicAnimation.prototype["animate"]=function(time){
		time=(time-this.startingTime)%this.period;
		this.animation.animate(time);
};

SFPeriodicAnimation.prototype["destroy"]=function(){

};

SFPeriodicAnimation.prototype["init"]=function(){

};

