

function SFCurvedTubeFunctionData(){
	this.setup();
}

inherit(SFCurvedTubeFunctionData,SFTwoCurvesFunctionData);

SFCurvedTubeFunctionData.prototype["buildResource"]=function(){
							   
			var curvedTube=new SFCurvedTubeFunction();
			var dataset=this.getFirstCurve().retrieveDataset();
			curvedTube.setCentralCurve(dataset.getResource());
			dataset=this.getSecondCurve().retrieveDataset();
			curvedTube.setRayCurve(dataset.getResource());
			return curvedTube;
		};
	

SFCurvedTubeFunctionData.prototype["generateNewDatasetInstance"]=function(){
			
			return new SFCurvedTubeFunctionData();
		};