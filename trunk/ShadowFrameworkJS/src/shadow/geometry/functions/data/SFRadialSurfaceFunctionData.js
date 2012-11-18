

function SFRadialSurfaceFunctionData(){
	this.setup();
}

inherit(SFRadialSurfaceFunctionData,SFTwoCurvesFunctionData);

SFRadialSurfaceFunctionData.prototype["buildResource"]=function(){			
	
	var radialSurface=new SFRadialSurfaceFunction();
	
	var dataset=this.getFirstCurve().retrieveDataset();
	radialSurface.setBorderCurve(dataset.getResource());
	dataset=this.getSecondCurve().retrieveDataset();
	radialSurface.setRayCurve(dataset.getResource());
	return radialSurface;

};

SFRadialSurfaceFunctionData.prototype["generateNewDatasetInstance"]=function(){	
	return new SFRadialSurfaceFunctionData();
};


