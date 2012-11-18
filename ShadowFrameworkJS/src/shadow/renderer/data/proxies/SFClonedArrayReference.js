

function SFClonedArrayReference(){
	this.properties=new SFDataObjectsList(new SFString());
	this.constants=new SFDataObjectsList(new SFString());
	this.elements=new SFDataAssetList();
	this.node=new SFLibraryReference(null);
	this.proxyDataCenter=new SFIndexedProxyDataCenter();

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.node);
	parameters.addObject(this.properties);
	parameters.addObject(this.constants);
	parameters.addObject(this.elements);
	//parameters.addObject("mappedProxy", mappedProxy);
	this.setData(parameters);
}

inherit(SFClonedArrayReference,SFDataAsset);
	
SFClonedArrayReference.prototype["buildResource"]=function(){
	var node=new SFReferenceNode();
		
	var index=0;
	for (var i = 0; i < this.elements.size(); ) {
		for (var j = 0; j < this.properties.size(); j++,i++) {
			this.proxyDataCenter.setData(this.properties.get(j).getString(), index, this.elements.get(i));
		}
		index++;
	}

	for (var i = 0; i < this.constants.size(); i++) {
		this.proxyDataCenter.addConstant(this.constants.get(i).getString());
	}
	
	this.proxyDataCenter.setup();

	SFDataAsset_setUpdateMode(true);
	
	for (var i = 0; i < this.proxyDataCenter.size; i++) {
		
		this.proxyDataCenter.setIndex(i);
		
		var model=this.node.getDataset().getResource();
		
		node.addNode(model);
	}
	
	SFDataAsset_setUpdateMode(false);

	this.proxyDataCenter.unset();
	
	return node;
};
	

SFClonedArrayReference.prototype["generateNewDatasetInstance"]=function(){
	return new SFClonedArrayReference();
};

