

function SFSimpleTexCoordGeometryuvData(){
	
	this.du=new SFFloat(1);
	this.dv=new SFFloat(1);
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.du);
	parameters.addObject(this.dv);
	this.setData(parameters);
}

inherit(SFSimpleTexCoordGeometryuvData,SFDataAsset);

SFSimpleTexCoordGeometryuvData.prototype["buildResource"]=function(){
	return new SFSimpleTexCoordGeometryuv(this.du.getFloatValue(), this.dv.getFloatValue());
};

SFSimpleTexCoordGeometryuvData.prototype["generateNewDatasetInstance"]=function(){
	return new SFSimpleTexCoordGeometryuvData();
};	
	
