

function SFFilteredRenderedTextureData(){
		
	this.textures =  new SFDataObjectsList(new SFTextureDataObject());

	this.lightComponent=new SFDataAssetObject(new SFProgramAssetData());
	this.materialComponent=new SFDataAssetObject(new SFProgramAssetData());

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.textures);
	parameters.addObject(this.lightComponent);
	parameters.addObject(this.materialComponent);
	this.setData(parameters);
}

inherit(SFFilteredRenderedTextureData,SFDataAsset);

SFFilteredRenderedTextureData.prototype["generateNewDatasetInstance"]=function(){
	return new SFFilteredRenderedTextureData();
};

SFFilteredRenderedTextureData.prototype["buildResource"]=function(){
	var renderedTexture=new SFFilteredRenderedTexture();
	
	renderedTexture.setMaterialComponent(this.materialComponent.getResource());
		
	var textures=new Array();
	for (var i = 0; i < this.textures.size(); i++) {
		textures[i]=this.textures.get(i).getTexture();
	}
	renderedTexture.setTextures(textures);

	renderedTexture.setLightComponent(this.lightComponent.getResource());

	//throw "OMG";
		
	return renderedTexture;
};

