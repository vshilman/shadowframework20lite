



function SFPolygonalSplineData(){
	this.vertices=new SFLibraryReference();
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.vertices);
	this.setData(parameters);
}

inherit(SFPolygonalSplineData,SFDataAsset);

SFPolygonalSplineData.prototype["buildResource"]=function(){
	
	var spline1 = new SFUniformCurvesSpline();

	var vertices=this.vertices.getDataset().getResource();
		
		for (var i = 1; i < vertices.getSize(); i++) {
			var vertex0=new SFVertex3f();
			var vertex1=new SFVertex3f();
			vertices.getValue(i-1, vertex0);
			vertices.getValue(i, vertex1);
			spline1.addCurve(new SFLine(vertex0,vertex1));
		}
			
		return spline1;
};

SFPolygonalSplineData.prototype["generateNewDatasetInstance"]=function(){
	return new SFPolygonalSplineData();
};	
	
