

function SFFunctionRandomizerData(){
	
	this.seed=new SFInt(9000);

	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.seed);
	this.setData(parameters);
}

inherit(SFFunctionRandomizerData,SFDataAsset);


SFFunctionRandomizerData.prototype["buildResource"]=function(){
	return new SFFunctionRandomizer(this.seed.getIntValue());
};


SFFunctionRandomizerData.prototype["generateNewDatasetInstance"]=function(){
	return new SFFunctionRandomizerData();
};
