

function SFBezier3Data(){
	this.vertices = new SFDataAssetObject(null);
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.vertices);
	this.setData(parameters);
}

inherit(SFBezier3Data,SFDataAsset);

SFBezier3Data.prototype["buildResource"]=function(){
	var values=vertices.getResource();
	var A=new SFVertex3f();
	var B=new SFVertex3f();
	var C=new SFVertex3f();
	var D=new SFVertex3f();
	
	this.values.getValue(0, A);
	this.getValue(1, B);
	this.getValue(2, C);
	this.getValue(3, D);
	
	var bezier3=new SFBezier3(A,B,C,D);
	
	return bezier3;
};

SFBezier3Data.prototype["generateNewDatasetInstance"]=function(){
	return new SFBezier3Data();
};	
		


