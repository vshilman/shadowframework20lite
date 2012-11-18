
function SFQuadsSurfaceGeometryData(){
	this.NuNv=new SFShortByteField(0);
	this.surfaceFunction=new SFLibraryReference();
	this.texCoordFunction=new SFLibraryReference();
	this.primitive=new SFString();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.NuNv);
	parameters.addObject(this.surfaceFunction);
	parameters.addObject(this.texCoordFunction);
	parameters.addObject(this.primitive);
	this.setData(parameters);
}

inherit(SFQuadsSurfaceGeometryData,SFDataAsset);


SFQuadsSurfaceGeometryData.prototype["buildResource"]=function(){

	var gridGeometry=new SFQuadsGridGeometry();
	
	gridGeometry.getQuadsGrid().setNu( this.NuNv.getByte(0));

	gridGeometry.getQuadsGrid().setNv( this.NuNv.getByte(1));
	
	var surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
	var primitive=SFPipeline_getPrimitive(this.primitive.getString());
	surfaceGeometry.setPrimitive(primitive);
	gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
	
	surfaceGeometry.setMainGeometryFunction(this.surfaceFunction.retrieveDataset().getResource());
	var texCoordDataset=this.texCoordFunction.retrieveDataset();
	if(texCoordDataset!=null)
		surfaceGeometry.setTexCoordGeometry(texCoordDataset.getResource());
	
	return surfaceGeometry;
};


SFQuadsSurfaceGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFQuadsSurfaceGeometryData();
};