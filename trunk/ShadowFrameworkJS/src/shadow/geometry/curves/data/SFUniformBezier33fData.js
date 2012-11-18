
function SFUniformBezier33fData(){
	var data=new SFDataObjectsList(new SFBinaryDataList(new SFFixedFloat16()));
	var namesParameters=new SFNamedParametersObject();
	namesParameters.addObject( data);
	this.setData(namesParameters);
}

inherit(SFUniformBezier33fData,SFDataAsset);


SFUniformBezier33fData.prototype["buildResource"]=function(){
	var  spline=new SFUniformCurvesSpline();
		
	for (var i = 0; i < this.data.size(); i++) {
		var curveData=data.get(i);
		var data=curveData.getDataObject();
		if(data.size()==6){
			var curve=SFCurves.generateBezier33f(data.get(0).getFloat(), data.get(1).getFloat(), data.get(2).getFloat(), 
					data.get(3).getFloat(), data.get(4).getFloat(), data.get(5).getFloat());
			spline.addCurve(curve);
		}
		if(data.size()==9){
			var curve=SFCurves.generateBezier33f2(data.get(0).getFloat(), data.get(1).getFloat(), data.get(2).getFloat(), 
					data.get(3).getFloat(), data.get(4).getFloat(), data.get(5).getFloat(), 
					data.get(6).getFloat(), data.get(7).getFloat(), data.get(8).getFloat());
			spline.addCurve(curve);
		} 
	}

	return spline;
};

SFUniformBezier33fData.prototype["generateNewDatasetInstance"]=function(){
	return new SFUniformBezier33fData();
};	
	
