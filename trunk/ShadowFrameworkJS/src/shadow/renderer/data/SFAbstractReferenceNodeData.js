
function SFAbstractReferenceNodeData(){
	
}

inherit(SFAbstractReferenceNodeData,SFDataAsset);

SFAbstractReferenceNodeData.prototype["generate"]=function(){
	
	this.nodes = new SFLibraryReferenceList(new SFLibraryReference());
	this.transform=new SFLibraryReference(new SFNoTransformData());
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject( this.transform);
	parameters.addObject( this.nodes);
	this.setData(parameters);
};


SFAbstractReferenceNodeData.prototype["buildResource"]=function(){
	
	var reference=this.generateReferenceNode();
		
	var transform=this.transform.getDataset().getResource();
	var vertex=new SFVertex3f();
	transform.getPosition(vertex);
	reference.getTransform().setPosition(vertex);
	var matrix=new SFMatrix3f();
	transform.getMatrix(matrix);
	reference.getTransform().setOrientation(matrix);
	
	for (var i = 0; i < this.nodes.size(); i++) {
		var dataset=this.nodes.get(i).retrieveDataset();//new SFDataCenterListener<SFDataAsset<SFNode>>() {
		reference.addNode(dataset.getResource().copyNode());
	}
	return reference;
};

