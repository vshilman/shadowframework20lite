

function SFObjectModel_SFNodeIterator(nodes){
	this.index=0;
	this.nodes=nodes;
}

SFObjectModel_SFNodeIterator.prototype["hasNext"]=function(){
	return this.index<nodes.length;
};

SFObjectModel_SFNodeIterator.prototype["next"]=function(){
	if(!this.hasNext())
		throw new NoSuchElementException();
	var node=this.nodes[index];
	this.index++;
	return node;
};

function SFObjectModel(){
	this.initialize();
	this.nodes=new Array();
	this.model=new SFModel();
}

inherit(SFObjectModel,SFTransformNode);


SFObjectModel.prototype["addNode"]=function(node){
	this.nodes.add(node);
};

SFObjectModel.prototype["setModel"]=function(model){
	this.model = model;
};


SFObjectModel.prototype["copyNode"]=function(){
	var model=new SFObjectModel();
	model.setModel(this.model);
	for(var i=0;i<this.nodes.length;i++){
		model.nodes.push(this.nodes[i].copyNode());
	}
	var tmpV=new SFVertex3f();
	var tmpM=new SFMatrix3f();
	this.transform.getPosition(tmpV);
	model.transform.setPosition(tmpV);
	this.transform.getOrientation(tmpM);
	model.transform.setOrientation(tmpM);
	return model;
};

SFObjectModel.prototype["getModel"]=function(){
	return this.model;
};
	

SFObjectModel.prototype["isDrawable"]=function(){
	return this.getModel().getRootGeometry()!=null;
};
	

SFObjectModel.prototype["init"]=function(){
	//Do nothing
};

SFObjectModel.prototype["destroy"]=function(){
	//Do nothing
};
	

SFObjectModel.prototype["getNodes"]=function(){
	return this.nodes;
};
	

SFObjectModel.prototype["iterator"]=function(){
	return new SFNodeIterator(this.nodes);
};
	

