
function SFGroupMeshGeometryData(){

	this.geometries = new SFDataAssetList();
	this.primitive = new SFString();

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.geometries);
	parameters.addObject(this.primitive);
	
	this.setData(parameters);
}

inherit(SFGroupMeshGeometryData,SFDataAsset);

SFGroupMeshGeometryData.prototype["buildResource"]=function(){
	var geometry=new SFGroupMeshGeometry();
	var primitive=SFPipeline_getPrimitive(this.primitive.getString());
	geometry.setArray(SFPipeline_getSfPipelineMemory().generatePrimitiveArray(primitive));
	for (var i = 0; i < this.geometries.size(); i++) {
		geometry.addGeometry(this.geometries.get(i).getResource());
	}
	return geometry;
};

SFGroupMeshGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFGroupMeshGeometryData();
};	
