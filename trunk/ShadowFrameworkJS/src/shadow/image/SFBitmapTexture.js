

function SFBitmapTexture(bitmap){
	this.bitmap=bitmap;
	this.texture=null;
}


SFBitmapTexture.prototype["getBitmap"]=function(){
	return this.bitmap;
};

SFBitmapTexture.prototype["setBitmap"]=function(bitmap){
	this.bitmap=bitmap;
};

SFBitmapTexture.prototype["getTexture"]=function(index){
	return this.texture;
};

SFBitmapTexture.prototype["init"]=function(){
	if(this.texture==null)
		this.texture=SFPipeline_getSfTexturePipeline().getRenderedTextureFactory().generateBitmapTexture(this.bitmap,
			 	Filter_LINEAR_MIPMAP,WrapMode_REPEAT, WrapMode_REPEAT);
};

SFBitmapTexture.prototype["update"]=function(){
};

SFBitmapTexture.prototype["destroy"]=function(){
	if(texture!=null){
		SFPipeline_getSfTexturePipeline().destroyBufferData(texture);		
	}
};

SFBitmap.prototype["getTextureSize"]=function(){
	return 1;
};

