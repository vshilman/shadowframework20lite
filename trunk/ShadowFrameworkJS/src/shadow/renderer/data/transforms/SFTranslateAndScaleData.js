



function SFTranslateAndScaleData(){
	this.position=new SFVertex3fData();
	this.scale=new SFFloat(1);
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.position);
	parameters.addObject(this.scale);
	this.setData(parameters);
}

inherit(SFTranslateAndScaleData,SFDataAsset);


SFTranslateAndScaleData.prototype["buildResource"]=function(){
	var matrix=new SFMatrix3f();

	matrix.mult(this.scale.getFloatValue());
	
	var transform=new SFTransform3f();
	transform.setMatrix(matrix);
	transform.setPosition(this.position.getVertex3f());
	
	return transform;
};

SFTranslateAndScaleData.prototype["generateNewDatasetInstance"]=function(){
	return new SFTranslateAndScaleData();
};