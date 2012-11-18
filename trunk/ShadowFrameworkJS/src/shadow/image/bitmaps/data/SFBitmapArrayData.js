

function SFBitmapArrayData(){
	this.width=new SFShort(0);
	this.height=new SFShort(0);
	this.bitmap=	new SFBinaryArrayObject(1);
	var parameters=new SFNamedParametersObject();
	parameters.addObject( this.width);
	parameters.addObject( this.height);
	parameters.addObject( this.bitmap);
	this.setData(parameters);
}

inherit(SFBitmapArrayData,SFBitmapData);

SFBitmapArrayData.prototype["buildResource"]=function(){
	
		var bitmap=new SFBitmap();
		var width=this.width.getShortValue();
		var height=this.height.getShortValue();
		var values=this.bitmap.getValues();

		bitmap.generateBitmap(width, height, values, false);
		
		return bitmap;
};


SFBitmapArrayData.prototype["generateNewDatasetInstance"]=function(){
	return new SFBitmapArrayData();
};

