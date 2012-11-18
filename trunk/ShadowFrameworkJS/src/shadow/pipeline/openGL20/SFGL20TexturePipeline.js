

function SFGL20TexturePipeline(){
	this.texture=new SFGL20RenderedTexture();
	this.factory=new SFGL20RenderedTextureFactory();
	this.frameBuffers=new Array();
	this.frameBuffers=new Array();
}

SFGL20TexturePipeline.prototype["beginNewRenderedTexture"]=function(textureData){
		this.texture.initShadowTexture(textureData);
	};

SFGL20TexturePipeline.prototype["destroyRenderedTexture"]=function(textureData){
		//TODO
	};


SFGL20TexturePipeline.prototype["endRenderedTexture"]=function(textureData){
		this.texture.closeShadowTexture();
	};


SFGL20TexturePipeline.prototype["destroyBufferData"]=function(bufferData){
		//TODO
	};

SFGL20TexturePipeline.prototype["getRenderedTextureFactory"]=function(){
		return this.factory;
	};
