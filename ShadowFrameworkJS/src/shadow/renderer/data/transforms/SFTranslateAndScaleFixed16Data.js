


function SFTranslateAndScaleFixed16Data(){
	this.x=new SFBinaryObject(new SFFixedFloat16(0));
	this.y=new SFBinaryObject(new SFFixedFloat16(0));
	this.z=new SFBinaryObject(new SFFixedFloat16(0));
	this.scale=new SFBinaryObject(new SFFixedFloat16(2));
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.x);
	parameters.addObject(this.y);
	parameters.addObject(this.z);
	parameters.addObject(this.scale);
	this.setData(parameters);
}

inherit(SFTranslateAndScaleFixed16Data,SFDataAsset);


SFTranslateAndScaleFixed16Data.prototype["buildResource"]=function(){
	var matrix=new SFMatrix3f();
	
	
	matrix.mult(this.scale.getBinaryValue().getFloat());
	
	var transform=new SFTransform3f();

	
	transform.setMatrix(matrix);
		
	var position=new SFVertex3f(this.x.getBinaryValue().getFloat(),
			this.y.getBinaryValue().getFloat(),
			this.z.getBinaryValue().getFloat());
	
	transform.setPosition(position);

	return transform;
};

SFTranslateAndScaleFixed16Data.prototype["generateNewDatasetInstance"]=function(){
	return new SFTranslateAndScaleFixed16Data();
};