
var SFVoronoiBitmapFunctionData_models=SFBasicVoronoiModels_values();
		
var SFVoronoiBitmapFunctionData_distances=SFBasicVoronoiDistances_values();

function SFVoronoiBitmapFunctionData(){
	
	this.vertices =new SFLibraryReference();
	this.model=new SFEnumObject(SFVoronoiBitmapFunctionData_models);
	this.distance=new SFEnumObject(SFVoronoiBitmapFunctionData_distances);


	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.vertices);
	parameters.addObject(this.mdoel);
	parameters.addObject(this.distance);
	this.setData(parameters);
}

inherit(SFVoronoiBitmapFunctionData,SFDataAsset);


SFVoronoiBitmapFunctionData.prototype["buildResource"]=function(){
	var func=new SFVoronoiBitmapFunction();
	func.setDistance(this.distance.getElement());
	func.setModel(this.model.getElement());
	var vertices=this.vertices.getDataset().getResource();
	var centers=new Array();
	for (var i = 0; i < vertices.getSize(); i++) {
		var vertex=new SFVertex2f(0,0);
		vertices.getValue(i, vertex);
		centers.push(vertex);
	}
	func.setCenters(centers);
	return func;
};


SFVoronoiBitmapFunctionData.prototype["generateNewDatasetInstance"]=function(){
	return new SFVoronoiBitmapFunctionData();
};


	
	
