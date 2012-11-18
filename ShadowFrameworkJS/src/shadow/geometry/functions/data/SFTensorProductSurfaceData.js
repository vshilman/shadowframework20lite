

function SFTensorProductSurfaceData(){

	this.curvesData = new SFLibraryReferenceList(new SFLibraryReference());
	this.productCurve=new SFLibraryReference();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.curvesData);
	parameters.addObject(this.productCurve);
	this.setData(parameters);
}

inherit(SFTensorProductSurfaceData,SFDataAsset);

SFTensorProductSurfaceData.prototype["buildResource"]=function(){

	var surface=new SFTensorProductSurface();
		
	var guideLines=new Array();
	for (var i = 0; i < thistory.guideLines.length; i++) {
		var dataset=this.guideLines.get(i).retrieveDataset();
		guideLines[i]=dataset.getResource();
	}
	surface.setGuideLines(guideLines);
	
	var dataset=productCurve.retrieveDataset();
	surface.setProductCurve(dataset.getResource());
	
	return surface;
};

SFTensorProductSurfaceData.prototype["generateNewDatasetInstance"]=function(){
	return new SFTensorProductSurfaceData();
};	
	

