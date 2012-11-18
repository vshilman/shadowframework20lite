

function SFTranslationData(){
	this.position=new SFVertex3fData();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.position);
	this.setData(parameters);
}

inherit(SFTranslationData,SFDataAsset);


SFTranslationData.prototype["buildResource"]=function(){
	var matrix=new SFMatrix3f();
	
	var transform=new SFTransform3f();
	transform.setMatrix(matrix);
	transform.setPosition(this.position.getVertex3f());
	
	return transform;
};

SFTranslationData.prototype["generateNewDatasetInstance"]=function(){
	return new SFTranslationData();
};
