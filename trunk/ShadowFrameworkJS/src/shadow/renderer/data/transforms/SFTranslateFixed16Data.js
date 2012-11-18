

function SFTranslateFixed16Data(){
	this.x=new SFBinaryObject(new SFFixedFloat16(0));
	this.y=new SFBinaryObject(new SFFixedFloat16(0));
	this.z=new SFBinaryObject(new SFFixedFloat16(0));
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.x);
	parameters.addObject(this.y);
	parameters.addObject(this.z);
	this.setData(parameters);
}

inherit(SFTranslateFixed16Data,SFDataAsset);


SFTranslateFixed16Data.prototype["buildResource"]=function(){
			var matrix=new SFMatrix3f();
			
			var transform=new SFTransform3f();
			transform.setMatrix(matrix);
			transform.setPosition(new SFVertex3f(this.x.getBinaryValue().getFloat(),
					this.y.getBinaryValue().getFloat(),
					this.z.getBinaryValue().getFloat()));
			
			return transform;
		};
		

SFTranslateFixed16Data.prototype["generateNewDatasetInstance"]=function(){
			return new SFTranslateFixed16Data();
		};
