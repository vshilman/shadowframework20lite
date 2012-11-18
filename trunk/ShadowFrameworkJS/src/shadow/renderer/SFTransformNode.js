
function SFTransformNode(){
	this.initialize();
}


SFTransformNode.prototype["initialize"]=function(){
	this.transform=SFPipelineTransforms_generateTrasform();
};



SFTransformNode.prototype["getTransform"]=function(){
	return this.transform;
};

