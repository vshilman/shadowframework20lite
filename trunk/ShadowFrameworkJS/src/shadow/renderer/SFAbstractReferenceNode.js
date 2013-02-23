

function SFAbstractReferenceNode(){
	this.nodes=new Array();	
	this.initialize();
}

inherit(SFAbstractReferenceNode,SFTransformNode);

SFAbstractReferenceNode.prototype["addNode"]=function(node){
	this.nodes.push(node);
	node.getTransform().attachOn(this.getTransform());
};


SFAbstractReferenceNode.prototype["removeNode"]=function(node){
	this.nodes.remove(node);
};

SFAbstractReferenceNode.prototype["getNodes"]=function(node){
	return this.nodes;
};


SFAbstractReferenceNode.prototype["getModel"]=function(){
	return null;
};
	

SFAbstractReferenceNode.prototype["init"]=function(){
	
};

//Should use the one of SFTransformNodeNode...
//SFAbstractReferenceNode.prototype["destroy"]=function(){
	//
//}

SFAbstractReferenceNode.prototype["isDrawable"]=function(){
	return false;
};	

