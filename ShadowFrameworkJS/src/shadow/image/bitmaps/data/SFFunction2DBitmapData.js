

function SFFunction2DBitmapData(){
	
	this.func=new SFLibraryReference(); 
	
	this.width=new SFShort();
	this.height=new SFShort();
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.width);
	parameters.addObject(this.height);
	parameters.addObject(this.func);
	this.setData(parameters);
}

inherit(SFFunction2DBitmapData,SFBitmapData);


SFFunction2DBitmapData.prototype["buildResource"]=function(){
	var bitmap=new SFFunction2DBitmap(this.width.getShortValue(), this.height.getShortValue(), false, null);
	var dataset=this.func.retrieveDataset();//new SFDataCenterListener<SFDataAsset<SFBitmapFunction>>() {
	bitmap.setFunction(dataset.getResource());
	return bitmap;
};


SFFunction2DBitmapData.prototype["generateNewDatasetInstance"]=function(){
	return new SFFunction2DBitmapData();
};
