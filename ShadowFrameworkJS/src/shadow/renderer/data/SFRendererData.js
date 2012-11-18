

function SFRendererData(){

	this.camera = new SFDataAssetObject(null);
	this.light=new SFDataAssetObject(new SFProgramAssetData());
	
	this.prepare();			
}

inherit(SFRendererData,SFDataAsset);


SFRendererData.prototype["prepare"]=function(){
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.camera);
	parameters.addObject(this.light);
	this.setData(parameters);
};


SFRendererData.prototype["buildResource"]=function(){
	var renderer = new SFRenderer();
	renderer.setLight(this.light.getResource());
	renderer.setCamera(this.camera.getDataset().getResource());
	return renderer;
};
	

SFRendererData.prototype["generateNewDatasetInstance"]=function(){
	return new SFRendererData();
};
