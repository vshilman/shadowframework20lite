
function SFNormalBasedObjPlaneTexCoordGeometryData(){
	
	this.a=new SFFloat(1);
	this.du=new SFFloat(0);
	this.av=new SFFloat(0);
	this.bv=new SFFloat(1);
	this.cv=new SFFloat(0);
	this.dv=new SFFloat(0);
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.a);
	parameters.addObject(this.du);
	parameters.addObject(this.av);
	parameters.addObject(this.bv);
	parameters.addObject(this.cv);
	parameters.addObject(this.dv);
	this.setData(parameters);
}

inherit(SFNormalBasedObjPlaneTexCoordGeometryData,SFDataAsset);

SFNormalBasedObjPlaneTexCoordGeometryData.prototype["buildResource"]=function(){
	return new SFNormalBasedObjPlaneTexCoordGeometry(this.a.getFloatValue(), this.du.getFloatValue(),
			this.av.getFloatValue(), this.bv.getFloatValue(), this.cv.getFloatValue(), this.dv.getFloatValue());
};

SFNormalBasedObjPlaneTexCoordGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFNormalBasedObjPlaneTexCoordGeometryData();
};	
