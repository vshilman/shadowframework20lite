

function SFBitmapFunctionPerlinNoiseData(){
	
	this.bitmap=new SFLibraryReference();
	this.prepare();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.width);
	parameters.addObject(this.height);
	parameters.addObject(this.weights);
	parameters.addObject(this.bitmap);
	this.setData(parameters);
}

inherit(SFBitmapFunctionPerlinNoiseData,SFPerlineNoiseData);


SFBitmapFunctionPerlinNoiseData.prototype["buildResource"]=function(){
	
		var weights=this.weights.getValues();
		var perlinNoise=new SFBitmapFunctionPerlinNoise(this.width.getShortValue(),
				this.height.getShortValue(),weights,
				false);
		
		var dataset=this.bitmap.retrieveDataset();
		perlinNoise.setBitmap(dataset.getResource());
		
		return perlinNoise;
};


SFBitmapFunctionPerlinNoiseData.prototype["generateNewDatasetInstance"]=function(){
	return new SFBitmapFunctionPerlinNoiseData();
};


	
