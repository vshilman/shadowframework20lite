

function SFCompositeMeshGeometryData(){

	this.geometries = new SFLibraryReferenceList(new SFLibraryReference());
	this.primitive = new SFString();

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.geometries);
	parameters.addObject(this.primitive);
	
	this.setData(parameters);
}

inherit(SFCompositeMeshGeometryData,SFDataAsset);

SFCompositeMeshGeometryData.prototype["buildResource"]=function(){
	var compositeMeshGeometry=new SFCompositeMeshGeometry();
	compositeMeshGeometry.setPrimitive(SFPipeline_getPrimitive(this.primitive.getString()));
	for (var i = 0; i < this.geometries.size(); i++) {
		var dataset=geometries.get(i).retrieveDataset();
		compositeMeshGeometry.addGeometry(dataset.getResource());
	}

	return compositeMeshGeometry;
};

SFCompositeMeshGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFCompositeMeshGeometryData();
};	
	



