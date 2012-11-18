


function SFParametrizedSurfaceGeometryData(){
	this.surfaceFunction=new SFLibraryReference();
	this.texCoordFunction=new SFLibraryReference();
	this.uvGeometry=new SFLibraryReference();
	this.primitive=new SFString();

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.surfaceFunction);
	parameters.addObject(this.texCoordFunction);
	parameters.addObject(this.uvGeometry);
	parameters.addObject(this.primitive);
	this.setData(parameters);
}

inherit(SFParametrizedSurfaceGeometryData,SFDataAsset);


SFParametrizedSurfaceGeometryData.prototype["buildResource"]=function(){

		var surfaceGeometry=new SFParametrizedGeometry();
		
		var dataset=this.uvGeometry.retrieveDataset();
		surfaceGeometry.addMeshGeometry(dataset.getResource());
		
		var primitive=SFPipeline_getPrimitive(this.primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		
		dataset=surfaceFunction.retrieveDataset();
		
		surfaceGeometry.setMainGeometryFunction(dataset.getResource());
		surfaceGeometry.setTexCoordGeometry(dataset.getResource());
		
		dataset=texCoordFunction.retrieveDataset();
		if(dataset!=null && dataset!=undefined)
				surfaceGeometry.setTexCoordGeometry(dataset.getResource());
				
		return surfaceGeometry;
};


SFParametrizedSurfaceGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFParametrizedSurfaceGeometryData();
};




