
function SFRectangle2DFunctionData(){
	this.x=new SFBinaryObject(new SFFixedFloat16());
	this.y=new SFBinaryObject(new SFFixedFloat16());
	this.w=new SFBinaryObject(new SFFixedFloat16());
	this.h=new SFBinaryObject(new SFFixedFloat16());
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.x);
	parameters.addObject(this.y);
	parameters.addObject(this.w);
	parameters.addObject(this.h);
	this.setData(parameters);
}

inherit(SFRectangle2DFunctionData,SFDataAsset);

SFRectangle2DFunctionData.prototype["buildResource"]=function(){
	var func=new SFRectangle2DFunction(
			this.x.getBinaryValue().getFloat(),
			this.y.getBinaryValue().getFloat(),
			this.w.getBinaryValue().getFloat(),
			this.h.getBinaryValue().getFloat()
	);
	return func;
};

SFRectangle2DFunctionData.prototype["generateNewDatasetInstance"]=function(){
	return new SFRectangle2DFunctionData();
};	
	




