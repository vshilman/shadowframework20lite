

function SFReferenceNode(){
	this.nodes=new Array();
	this.initialize();
}

inherit(SFReferenceNode,SFAbstractReferenceNode);


SFReferenceNode.prototype["copyNode"]=function(){
	var reference=new SFReferenceNode();
	this.cloneOn(reference);
	return reference;
};


SFReferenceNode.prototype["cloneOn"]=function(reference){
	for(var i=0;i<this.nodes.length;i++){
		reference.addNode(this.nodes[i].copyNode());
	}
	var tmpV=new SFVertex3f();
	var tmpM=new SFMatrix3f();
	this.transform.getPosition(tmpV);
	reference.transform.setPosition(tmpV);
	this.transform.getOrientation(tmpM);
	reference.transform.setOrientation(tmpM);
};

