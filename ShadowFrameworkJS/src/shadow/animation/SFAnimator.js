//Java to JS on 06/02/2012

function SFAnimator(){
	this.animations=new Array();
}

var sfAnimatorSingletonInstance=new SFAnimator();

function SFAnimator_addAnimation(animation){
	if (!(animation in sfAnimatorSingletonInstance.animations))			
		sfAnimatorSingletonInstance.animations.push(animation);
}


function SFAnimator_animate(time){
	
	for(animationIndex in sfAnimatorSingletonInstance.animations){
		sfAnimatorSingletonInstance.animations[animationIndex].animate(time);
	}

}
