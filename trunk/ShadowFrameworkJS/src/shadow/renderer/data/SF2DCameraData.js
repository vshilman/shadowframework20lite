
function SF2DCameraData(){

	this.leftL=new SFFloat(0);
	this.upL=new SFFloat(0);

this.prepare();			
}

inherit(SF2DCameraData,SFDataAsset);


SF2DCameraData.prototype["prepare"]=function(){
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.leftL);
	parameters.addObject(this.upL);
	this.setData(parameters);
};


SF2DCameraData.prototype["buildResource"]=function(){
	var camera=new SFCamera(new SFVertex3f(0,0,0),
				new SFVertex3f(1,0,0),new SFVertex3f(0,1,0),new SFVertex3f(0,0,1),0,this.leftL.getFloatValue(),
				this.upL.getFloatValue());
		return camera;
};
	

SF2DCameraData.prototype["generateNewDatasetInstance"]=function(){
	return new SF2DCameraData();
};