
function SFStandardTweeners(tweenFunc){
	this.tweenFunc=tweenFunc;
}

SFStandardTweeners.prototype["tweenValue"]=function(){
	return this.tweenFunc();
}


var SFStandardTweeners_LINEAR_tweenValue=function(value){
	return value;
};

var SFStandardTweeners_LINEAR = new SFStandardTweeners(SFStandardTweeners_LINEAR_tweenValue);

var SFStandardTweeners_CUBIC_tweenValue=function(value){
	return (-2*value+3)*value*value;
};

var SFStandardTweeners_CUBIC = new SFStandardTweeners(SFStandardTweeners_CUBIC_tweenValue);

