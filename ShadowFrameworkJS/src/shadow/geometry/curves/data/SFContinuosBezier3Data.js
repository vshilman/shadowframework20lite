



function SFContinuosBezier3Data(){
	this.vertices = new SFDataAssetObject(null);
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.vertices);
	this.setData(parameters);
}

inherit(SFContinuosBezier3Data,SFCurvesVerticesData);

SFContinuosBezier3Data.prototype["buildResource"]=function(){
	
	var  spline=new SFUniformCurvesSpline();
	
	var vertices=this.vertices.getDataset().getResource();
	
	for (var i = 0; i < vertices.getSize(); i++) {
		var point=new SFVertex3f();
		vertices.getValue(i, point);
		spline.addControlPoint(point);
	}
	
	for (var i = 0; i < vertices.getSize()-3; i+=3) {

		var A=spline.getControlPoint(i);
		var B=spline.getControlPoint(i+1);
		var C=spline.getControlPoint(i+2);
		var D=spline.getControlPoint(i+3);

		var values=new SFBezier3(A, B, C, D);
		spline.addCurve(values);
	}
	
	return spline;
};

SFContinuosBezier3Data.prototype["generateNewDatasetInstance"]=function(){
	return new SFContinuosBezier3Data();
};	



