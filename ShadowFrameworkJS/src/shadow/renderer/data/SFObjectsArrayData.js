

function SFObjectsArrayData(){

	this.startTransform=new SFDataAssetObject(new SFNoTransformData());
	this.incrementalTransform=new SFDataAssetObject(new SFNoTransformData());
	this.arraySize=new SFShort(0);
	this.objectNode=new SFLibraryReference(null);
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.startTransform);
	parameters.addObject(this.incrementalTransform);
	parameters.addObject(this.arraySize);
	parameters.addObject(this.objectNode);
	this.setData(parameters);
}

inherit(SFObjectsArrayData,SFDataAsset);

SFObjectsArrayData.prototype["buildResource"]=function(){
	var node=new SFReferenceNode();
	
	var dataset=this.objectNode.retrieveDataset();
	
	var modelNode=dataset.getResource();
					
	var incremental=this.incrementalTransform.getDataset().buildResource();
	var startTransform=this.startTransform.getDataset().buildResource();		
			
	for (var i = 0; i < this.arraySize.getShortValue(); i++) {
		var modelNodeClone=modelNode.copyNode();
		
		var matrix=new SFMatrix3f();
		var position=new SFVertex3f();
		
		startTransform.getMatrix(matrix);
		startTransform.getPosition(position);
		
		modelNodeClone.getTransform().setOrientation(matrix);
		modelNodeClone.getTransform().setPosition(position);
		
		node.addNode(modelNodeClone);
		
		startTransform.mult(incremental);
	}
	
	return node;
};

SFObjectsArrayData.prototype["generateNewDatasetInstance"]=function(){
	return new SFObjectsArrayData();
};
