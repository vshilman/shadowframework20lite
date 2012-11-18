

function SFMeshGeometryData(){
	this.linesData=new SFLibraryReference(null);
	this.firstElement=new SFShort(0);
	this.lastElement=new SFShort(0);
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.linesData);
	parameters.addObject(this.firstElement);
	parameters.addObject(this.lastElement);
	this.setData(parameters);
}

inherit(SFMeshGeometryData,SFDataAsset);


SFMeshGeometryData.prototype["buildResource"]=function(){

	var geometry=new SFMeshGeometry();
	
	var dataset=this.linesData.retrieveDataset();
	var array=dataset.getResource();
	geometry.setArray(array);
	geometry.setPrimitive(array.getPrimitive());

	geometry.getMesh().evaluateRanges();//is this necessary?
	geometry.setFirstElement(this.firstElement.getShortValue());
	geometry.setLastElement(this.lastElement.getShortValue());
	
	return geometry;
};


SFMeshGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFMeshGeometryData();
};

