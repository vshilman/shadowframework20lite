

function SFSplineCurvedTubeFunctionData(){
	
	this.curvesData = new SFLibraryReferenceList(new SFLibraryReference());
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.curvesData);
	this.setData(parameters);
}

inherit(SFSplineCurvedTubeFunctionData,SFDataAsset);

SFSplineCurvedTubeFunctionData.prototype["buildResource"]=function(){

	var func = new SFSplineCurvedTubeFunction();
	
	for (var i = 0; i < this.curvesData.size(); i++) {
		var dataset=this.curvesData.get(i).retrieveDataset();
		func.getCurves().push(dataset.getResource());
	}

	return func;
};

SFSplineCurvedTubeFunctionData.prototype["generateNewDatasetInstance"]=function(){
	return new SFSplineCurvedTubeFunctionData();
};	
	
