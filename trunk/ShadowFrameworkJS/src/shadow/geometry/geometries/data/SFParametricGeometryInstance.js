


function SFParametricGeometryInstance(){
	
	this.parametricGeometry=new SFLibraryReference(null);
	this.parameters=new SFDataParametersSet();
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.parameters);
	parameters.addObject(this.parametricGeometry);
	this.setData(parameters);
}

inherit(SFParametricGeometryInstance,SFDataAsset);


SFParametricGeometryInstance.prototype["buildResource"]=function(){

	var dataset=this.parametricGeometry.retrieveDataset();
	
	SFDataAsset_setUpdateMode(true);
	
	this.parameters.apply();
	
	var geometry = dataset.getResource();
	
	SFDataAsset_setUpdateMode(false);
	
	return geometry;
};


SFParametricGeometryInstance.prototype["generateNewDatasetInstance"]=function(){
	return new SFParametricGeometryInstance();
};
