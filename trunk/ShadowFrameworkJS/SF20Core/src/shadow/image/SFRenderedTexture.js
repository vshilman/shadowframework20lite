
function SFRenderedTexture(){
}

SFRenderedTexture.prototype = {

	setDepthBuffer:function(depthBuffer){
		this.depthBuffer    = depthBuffer;
	},

	setStencilBuffer:function(stencilBuffer){
		this.stencilBuffer    = stencilBuffer;
	},

	addColorData:function(colorData){
		this.colorsData.add(colorData);
	},

	getDepthBuffer:function(){
		return ,depthBuffer;
	},

	getStencilBuffer:function(){
		return ,stencilBuffer;
	},

	getColorsData:function(){
		return ,colorsData;
	}

};