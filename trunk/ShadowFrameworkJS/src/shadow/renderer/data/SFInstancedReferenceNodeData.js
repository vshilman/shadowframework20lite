
function SFInstancedReferenceNodeData(){
	this.generate();	
}

inherit(SFInstancedReferenceNodeData,SFAbstractReferenceNodeData);

SFInstancedReferenceNodeData.prototype["generateReferenceNode"]=function(){
	return new SFInstancedReferenceNode();
};

SFInstancedReferenceNodeData.prototype["generateNewDatasetInstance"]=function(){
	return new SFInstancedReferenceNodeData();
};

