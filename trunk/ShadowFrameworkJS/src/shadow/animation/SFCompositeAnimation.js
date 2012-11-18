


function SFCompositeAnimation(){
	this.array=new Array();
}

SFCompositeAnimation.prototype["animate"]=function(time){
	for (var i = 0; i < this.size(); i++) {
		get(i).animate(time);
	} 
};


SFCompositeAnimation.prototype["size"]=function(){
	return this.array.length;
};

SFCompositeAnimation.prototype["get"]=function(i){
	return this.array[i];
};

SFCompositeAnimation.prototype["add"]=function(animation){
	return this.array.push(animation);
};
	
SFCompositeAnimation.prototype["clone"]=function(){
	var clone=new SFCompositeAnimation();
	for (var i=0;i<this.array.length;i++) {
		var animation=sfAnimation.clone();
		clone.add(animation);
	}
	return clone;
};
	
SFCompositeAnimation.prototype["destroy"]=function(){
	
};	

SFCompositeAnimation.prototype["init"]=function(){
	
};	
	
SFCompositeAnimation.prototype["getTweener"]=function(){
	return null;
};	

//	protected void setTweener(SFTweener tweener) {
//	}

//	protected void applyTransform(double T) {
//	}
