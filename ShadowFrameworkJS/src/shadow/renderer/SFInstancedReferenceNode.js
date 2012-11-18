

function SFInstancedReferenceNode(){
	this.nodes=new Array();	
	this.initialize();
}

inherit(SFInstancedReferenceNode,SFAbstractReferenceNode);

SFAbstractReferenceNode.prototype["copyNode"]=function(){
	return this;
}