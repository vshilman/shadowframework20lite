
function SFGL20TexturePipeline(){
}

SFGL20TexturePipeline.prototype = {

	beginNewRenderedTexture:function(textureData){
	texture.initShadowTexture(textureData);//Warning: Not well Identified 
	//what should i do?;//Warning: Not well Identified 
	},

	destroyRenderedTexture:function(textureData){
	// TODO Auto-generated method stub					//Rendered texture are still not destroyed...;//Warning: Not well Identified 
	},

	endRenderedTexture:function(textureData){
	// TODO Auto-generated method stub		SFGL20TexturePipeline.texture.closeShadowTexture();//Warning: Not well Identified 
	},

	destroyBufferData:function(bufferData){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	},

	getRenderedTextureFactory:function(){
		return this.factory;
	}

};