
function SFBasisSplineData(){
	this.setup();
}

inherit(SFBasisSplineData,SFCurvesVerticesData);//TODO: verify constructors

SFBasisSplineData.prototype["buildResource"]=function(){
			var spline=new SFBasisSpline2(this.getClosed());
			var dataset=this.vertices.retrieveDataset();
			
				var iterator=dataset.getResource().getIterator();
				while (iterator.hasNext()) {
					var vertex=new SFVertex3f();
					iterator.getNext(vertex);
					spline.getVertices().push(vertex);
				}
			return spline;
		};

SFBasisSplineData.prototype["generateNewDatasetInstance"]=function(){
			return new SFBasisSplineData();
		};	