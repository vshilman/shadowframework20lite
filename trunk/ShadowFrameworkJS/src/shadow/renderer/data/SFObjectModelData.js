
function SFObjectModelData(){
	this.transform=new SFLibraryReference(new SFNoTransformData());
	this.rootGeometryReference=new SFLibraryReference();
	this.transformComponent=new SFDataAssetObject(new SFProgramAssetData());
	this.materialComponent=new SFDataAssetObject(new SFProgramAssetData());
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.transform);
	parameters.addObject(this.rootGeometryReference);
	parameters.addObject(this.transformComponent);
	parameters.addObject(this.materialComponent);
	this.setData(parameters);
}

inherit(SFObjectModelData,SFDataAsset);

SFObjectModelData.prototype["buildResource"]=function(){
	var node=new SFObjectModel();
	
	var transform=this.transform.retrieveDataset().getResource();
	var vertex=new SFVertex3f();
	transform.getPosition(vertex);
	node.getTransform().setPosition(vertex);
	var matrix=new SFMatrix3f();
	transform.getMatrix(matrix);
	node.getTransform().setOrientation(matrix);
	
	node.getModel().setRootGeometry(this.rootGeometryReference.retrieveDataset().getResource());
	
	node.getModel().setMaterialComponent(this.materialComponent.getResource());
	node.getModel().setTransformComponent(this.transformComponent.getResource());
	
	return node;
};

SFObjectModelData.prototype["generateNewDatasetInstance"]=function(){
	return new SFObjectModelData();
};
