

function SFReferenceNodeData(){
	this.generate();
}

inherit(SFReferenceNodeData,SFAbstractReferenceNodeData);

SFReferenceNodeData.prototype["generateReferenceNode"]=function(){
	return new SFReferenceNode();
};


SFReferenceNodeData.prototype["generateNewDatasetInstance"]=function(){
	return new SFReferenceNodeData();
};