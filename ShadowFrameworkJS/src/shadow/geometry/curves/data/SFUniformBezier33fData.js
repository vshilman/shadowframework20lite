
function SFUniformBezier33fData(){
	this.data=new SFDataObjectsList(new SFBinaryDataList(new SFFixedFloat16()));
	var namesParameters=new SFNamedParametersObject();
	namesParameters.addObject(this.data);
	this.setData(namesParameters);
}

inherit(SFUniformBezier33fData,SFDataAsset);


SFUniformBezier33fData.prototype["buildResource"]=function(){
	var  spline=new SFUniformCurvesSpline();
		
	for (var i = 0; i < this.data.size(); i++) {
		var curveData=this.data.get(i);
		var data=curveData.getDataObject();
		if(data.length==6){
			var curve=SFCurves_generateBezier33f(data[0].getFloat(), data[1].getFloat(), data[2].getFloat(), 
					data[3].getFloat(), data[4].getFloat(), data[5].getFloat());
			spline.addCurve(curve);
		}
		if(data.length==9){
			var curve=SFCurves_generateBezier33f2(data[0].getFloat(), data[1].getFloat(), data[2].getFloat(), 
					data[3].getFloat(), data[4].getFloat(), data[5].getFloat(), 
					data[6].getFloat(), data[7].getFloat(), data[8].getFloat());
			spline.addCurve(curve);
		} 
	}

	return spline;
};

SFUniformBezier33fData.prototype["generateNewDatasetInstance"]=function(){
	return new SFUniformBezier33fData();
};	
	
