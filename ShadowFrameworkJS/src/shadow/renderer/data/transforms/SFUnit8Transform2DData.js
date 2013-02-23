

function SFUnit8Transform2DData(){
	this.x=new SFBinaryObject(new SFFixedFloatUnit8(0));
	this.y=new SFBinaryObject(new SFFixedFloatUnit8(0));
	this.rot=new SFBinaryObject(new SFFixedFloatUnit8(0));
	this.scale=new SFBinaryObject(new SFFixedFloatUnit8(0));
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.x);
	parameters.addObject(this.y);
	parameters.addObject(this.rot);
	parameters.addObject(this.scale);
	this.setData(parameters);
}

inherit(SFUnit8Transform2DData,SFDataAsset);


SFUnit8Transform2DData.prototype["buildResource"]=function(){
			var matrix=SFMatrix3f_getRotationY(this.rot.getBinaryValue().getFloat()*(float)(2*Math.PI));
			matrix.mult(this.scale.getBinaryValue().getFloat());
			
			var transform=new SFTransform3f();
			transform.setMatrix(matrix);
			transform.setPosition(new SFVertex3f(this.x.getBinaryValue().getFloat(),
					this.y.getBinaryValue().getFloat(),0));
			
			return transform;
		};
		

SFUnit8Transform2DData.prototype["generateNewDatasetInstance"]=function(){
			return new SFUnit8Transform2DData();
		};