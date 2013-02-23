
function SFPipelineTransform3f(array,index){
	this.array = array;
	this.index = index;
}

SFPipelineTransform3f.prototype["attachOn"]=function(transform){
	transform.array.attach(this.array, this.index, transform.index);
};

SFPipelineTransform3f.prototype["setPosition"]=function(vertex){
	this.array.setElementPosition(this.index, vertex);
};

SFPipelineTransform3f.prototype["setOrientation"]=function(matrix){
	this.array.setElementOrientation(this.index, matrix);
};

SFPipelineTransform3f.prototype["getPosition"]=function(vertex){
	this.array.getElementPosition(this.index, vertex);
};

SFPipelineTransform3f.prototype["getOrientation"]=function(matrix){
	this.array.getElementOrientation(this.index, matrix);
};

SFPipelineTransform3f.prototype["apply"]=function(){
	this.array.apply(this.index);
};	