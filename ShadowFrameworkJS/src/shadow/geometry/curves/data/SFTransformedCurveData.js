

function SFTransformedCurveData(){
	
	this.transform=new SFLibraryReference(new SFNoTransformData());
	this.curve =new SFLibraryReference();
	
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.transform);
	parameters.addObject(this.curve);
	this.setData(parameters);
}

inherit(SFTransformedCurveData,SFDataAsset);

SFTransformedCurveData.prototype["buildResource"]=function(){
	

	var trCurve=new SFTransformedCurve();
	
	var transformDataset=this.transform.retrieveDataset();
	var curveDataset=this.curve.retrieveDataset();
	
	trCurve.setTransform(transformDataset.getResource());
	trCurve.setCurve(curveDataset.getResource());
	
	return trCurve;
};

SFTransformedCurveData.prototype["generateNewDatasetInstance"]=function(){
	return new SFTransformedCurveData();
};	
	



	