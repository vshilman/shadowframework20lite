
var SFDrawnRenderedTextureData_USE_DEFAULT_DEPTH=1;
var SFDrawnRenderedTextureData_USE_DEFAULT_STENCIL=3;

function SFDrawnRenderedTextureData(){
	this.textures=new SFDataObjectsList(new SFTextureDataObject());
	this.node=new SFLibraryReference();
	this.renderer=new SFDataAssetObject(null);
	this.depthUse=new SFShort(0);
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.textures);
	parameters.addObject(this.node);
	parameters.addObject(this.renderer);
	parameters.addObject(this.depthUse);
	this.setData(parameters);
}

inherit(SFDrawnRenderedTextureData,SFDataAsset);

SFDrawnRenderedTextureData.prototype["generateNewDatasetInstance"]=function(){
	return new SFDrawnRenderedTextureData();
};

SFDrawnRenderedTextureData.prototype["buildResource"]=function(){
	var drawn=new SFDrawnRenderedTexture();
	var textures=new Array();
	for (var i = 0; i < this.textures.size(); i++) {
		textures[i]=this.textures.get(i).getTexture();
	}
	drawn.setTextures(textures);
		
	var dataset=this.node.retrieveDataset();
	drawn.setNode(dataset.getResource());

	drawn.setRenderer(this.renderer.getDataset().getResource());
	drawn.setUseDefaultDepthBuffer((this.depthUse.getShortValue() & SFDrawnRenderedTextureData_USE_DEFAULT_DEPTH)>0);
	drawn.setUseDefaultStencilBuffer((this.depthUse.getShortValue() & SFDrawnRenderedTextureData_USE_DEFAULT_STENCIL)>0);
	return drawn;
};
