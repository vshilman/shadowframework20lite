

function SFBitmapPerlinNoise(width,height,weights,rgb){

	this.interpolant = new SFLinearInterpolation();
	this.bitmap = new SFFunction2DBitmap(10,10,false,SFBasicBitmapFunctions_CIRCLE);	
	
	if(width!=undefined && height!=undefined && weights!=undefined && rgb!=undefined){
		
		this.setWidth(width);
		this.setHeight(height);
		
		this.weights=weights;
		this.rgb=rgb;
	}
}

inherit(SFBitmapPerlinNoise,SFPerlinNoise);

SFBitmapPerlinNoise.prototype["getBitmap"]=function(){
		return this.bitmap;
};

SFBitmapPerlinNoise.prototype["getInterpolant"]=function(){
		return this.interpolant;
};

SFBitmapPerlinNoise.prototype["setInterpolant"]=function(interpolant){
	this.interpolant = interpolant;
};

SFBitmapPerlinNoise.prototype["setBitmap"]=function(bitmap){
	this.bitmap = bitmap;
};

SFBitmapPerlinNoise.prototype["getOctaveValue"]=function(u,v,octave){
	return this.interpolant.getOctaveValue(u, v, octave,this.bitmap);
};



