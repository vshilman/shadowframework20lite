


function SFTextureDataToken(width, height, format, filters,  wrapS, wrapT){
	this.filters = filters;
	this.WrapS = wrapS;
	this.WrapT = wrapT;
	this.width=width;
	this.height=height;
	this.format=format;
}

inherit(SFTextureDataToken,SFPipelineTexture);

SFTextureDataToken.prototype["apply"]=new function(textureLevel){
	//Do nothing
};