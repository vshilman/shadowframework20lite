

function SFSimpleObjPlaneTexCoordGeometryData(){
	
	this.au=new SFBinaryObject(new SFFixedFloat16());
	this.bu=new SFBinaryObject(new SFFixedFloat16());
	this.cu=new SFBinaryObject(new SFFixedFloat16());
	this.du=new SFBinaryObject(new SFFixedFloat16());
	this.av=new SFBinaryObject(new SFFixedFloat16());
	this.bv=new SFBinaryObject(new SFFixedFloat16());
	this.cv=new SFBinaryObject(new SFFixedFloat16());
	this.dv=new SFBinaryObject(new SFFixedFloat16());
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.au);
	parameters.addObject(this.bu);
	parameters.addObject(this.cu);
	parameters.addObject(this.du);
	parameters.addObject(this.av);
	parameters.addObject(this.bv);
	parameters.addObject(this.cv);
	parameters.addObject(this.dv);
	this.setData(parameters);
}

inherit(SFSimpleObjPlaneTexCoordGeometryData,SFDataAsset);

SFSimpleObjPlaneTexCoordGeometryData.prototype["buildResource"]=function(){
	return new SFSimpleObjPlaneTexCoordGeometry(this.au.getFloatValue(), this.bu.getFloatValue(), 
		this.cu.getFloatValue(), this.du.getFloatValue(),
		this.av.getFloatValue(), this.bv.getFloatValue(), 
		this.cv.getFloatValue(), this.dv.getFloatValue());
};

SFSimpleObjPlaneTexCoordGeometryData.prototype["generateNewDatasetInstance"]=function(){
	return new SFSimpleObjPlaneTexCoordGeometryData();
};	
	









