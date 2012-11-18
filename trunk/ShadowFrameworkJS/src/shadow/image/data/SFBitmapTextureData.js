

function SFBitmapTextureData(){
	this.bitmap=new SFDataAssetObject();	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.bitmap);
	this.setData(parameters);
}

inherit(SFBitmapTextureData,SFDataAsset);

SFBitmapTextureData.prototype["generateNewDatasetInstance"]=function(){
	return new SFBitmapTextureData();
};

SFBitmapTextureData.prototype["buildResource"]=function(){
	var bitmapTexture=new SFBitmapTexture(this.bitmap.getResource());
	return bitmapTexture;
};
