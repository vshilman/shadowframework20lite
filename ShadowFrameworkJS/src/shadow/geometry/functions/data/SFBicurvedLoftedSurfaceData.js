


function SFBicurvedLoftedSurfaceData(){
	
	this.centralCurve=new SFLibraryReference();
	this.rayCurve=new SFLibraryReference();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.centralCurve);
	parameters.addObject(this.rayCurve);
	this.setData(parameters);
}

inherit(SFBicurvedLoftedSurfaceData,SFDataAsset);

SFBicurvedLoftedSurfaceData.prototype["buildResource"]=function(){
	var loftCurve=new SFBicurvedLoftedSurface();
	var firstDataset=this.centralCurve.retrieveDataset();
	var secondDataset=this.rayCurve.retrieveDataset();
		
	loftCurve.setA(firstDataset.getResource());
	loftCurve.setB(secondDataset.getResource());
		
	return loftCurve;
};

SFBicurvedLoftedSurfaceData.prototype["generateNewDatasetInstance"]=function(){
	return new SFBicurvedLoftedSurfaceData();
};	
		





