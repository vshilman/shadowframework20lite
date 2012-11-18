

var SFBitmapPerlinNoiseData_interpolants=[
	new SFNearestInterpolation(),new SFLinearInterpolation(),new SFCubicInterpolation()
];


function SFBitmapPerlinNoiseData(){
			
	this.bitmap=new SFLibraryReference();
	this.interpolant=new SFEnumObject(SFBitmapPerlinNoiseData_interpolants);
	this.prepare();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.width);
	parameters.addObject(this.height);
	parameters.addObject(this.weights);
	parameters.addObject(this.interpolant);
	parameters.addObject(this.bitmap);
	this.setData(parameters);
}

inherit(SFBitmapPerlinNoiseData,SFPerlineNoiseData);

SFBitmapPerlinNoiseData.prototype["buildResource"]=function(){
	
		var weights=this.weights.getValues();
		var perlinNoise=new SFBitmapPerlinNoise(this.width.getShortValue(),
				this.height.getShortValue(),weights,
				false);
		perlinNoise.setInterpolant(this.interpolant.getElement());
		
		var dataset=this.bitmap.retrieveDataset();
		if(dataset!=null && dataset!=undefined){
			var bitmap=dataset.getResource();
			if(bitmap!=null)
				perlinNoise.setBitmap(bitmap);
		}
		
		return perlinNoise;
};


SFBitmapPerlinNoiseData.prototype["generateNewDatasetInstance"]=function(){
	return new SFBitmapPerlinNoiseData();
};

