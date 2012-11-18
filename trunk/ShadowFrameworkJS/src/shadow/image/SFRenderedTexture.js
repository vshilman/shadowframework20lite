//Java to JS on 06/02/2012

function SFRenderedTexture(depthBuffer,stencilBuffer,colorsData){
	this.depthBuffer=depthBuffer;
	this.stencilBuffer=stencilBuffer;
	if(colorsData===undefined){
		colorsData=new Array();
	}
	this.colorsData=colorsData;
}

SFRenderedTexture.prototype["setDepthBuffer"]=function(depthBuffer){
	this.depthBuffer=depthBuffer;
};


SFRenderedTexture.prototype["setStencilBuffer"]=function(stencilBuffer){
	this.stencilBuffer=stencilBuffer;
};

SFRenderedTexture.prototype["addColorData"]=function(colorData){
	this.colorsData.push(colorData);
};

SFRenderedTexture.prototype["getDepthBuffer"]=function(){
	return this.depthBuffer;
};

SFRenderedTexture.prototype["getStencilBuffer"]=function(){
	return this.stencilBuffer;
};

SFRenderedTexture.prototype["getColorsData"]=function(){
	return this.colorsData;
};
	