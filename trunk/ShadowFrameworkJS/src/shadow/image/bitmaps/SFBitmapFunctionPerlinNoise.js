

function SFBitmapFunctionPerlinNoise(width,height,weights,rgb){
	this.bitmap=null;
	if(width!=undefined && height!=undefined && weights!=undefined && rgb!=undefined){
		
		this.setWidth(width);
		this.setHeight(height);
		
		this.weights=weights;
		this.rgb=rgb;
	}
	
}

inherit(SFBitmapFunctionPerlinNoise,SFPerlinNoise);


SFBitmapFunctionPerlinNoise.prototype["getBitmap"]=function(){
		return this.bitmap;
};

SFBitmapFunctionPerlinNoise.prototype["setBitmap"]=function(bitmap){
		this.bitmap = bitmap;
};
	
SFBitmapFunctionPerlinNoise.prototype["getOctaveValue"]=function(u,v,octave){
		var U=u*octave;
		U-=(Math.floor(U));
		var V=v*octave;
		V-=(Math.floor(V));
		
		var value=bitmap.getValue(U, V);
		return value*256;
};
	

