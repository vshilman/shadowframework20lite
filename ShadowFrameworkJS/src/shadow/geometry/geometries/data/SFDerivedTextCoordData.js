

function SFDerivedTextCoordData(){


	this.func=new SFLibraryReference();
	this.geometry=new SFLibraryReference();
	this.primitive = new SFString();

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.func);
	parameters.addObject(this.geometry);
	parameters.addObject(this.primitive);
	
	this.setData(parameters);
}

inherit(SFDerivedTextCoordData,SFDataAsset);

SFDerivedTextCoordData.prototype["buildResource"]=function(){
	var derivedTextCoord=new SFDerivedTexCoord();
		
	derivedTextCoord.setPrimitive(SFPipeline_getPrimitive(this.primitive.getString()));
		
	var dataset=this.func.retrieveDataset();
	derivedTextCoord.setDerivedTexCoordFunction(dataset.getResource());
		
	dataset=this.geometry.retrieveDataset();
	derivedTextCoord.addMeshGeometry(dataset.getResource());
	
	return derivedTextCoord;
};

SFDerivedTextCoordData.prototype["generateNewDatasetInstance"]=function(){
	return new SFDerivedTextCoordData();
};	
	
