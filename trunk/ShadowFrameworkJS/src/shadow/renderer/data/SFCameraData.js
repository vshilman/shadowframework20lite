


function SFCameraData(){

	this.focus=new SFVertex3fData();
	this.left=new SFVertex3fData();
	this.up=new SFVertex3fData();
	this.dir=new SFVertex3fData();
	this.distance=new SFFloat(0);
	
	this.leftL=new SFFloat(0);
	this.upL=new SFFloat(0);

	this.prepare();			
}

inherit(SFCameraData,SFDataAsset);

SFCameraData.prototype["prepare"]=function(){
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.focud);
	parameters.addObject(this.left);
	parameters.addObject(this.leftL);
	parameters.addObject(this.up);
	parameters.addObject(this.dir);
	parameters.addObject(this.distance);
	parameters.addObject(this.leftL);
	parameters.addObject(this.upL);
	this.setData(parameters);
};


SFCameraData.prototype["buildResource"]=function(){
	return new SFCamera(this.focus.getVertex3f(), this.dir.getVertex3f(), this.left.getVertex3f(),
				this.up.getVertex3f(), this.leftL.getFloatValue(), this.upL.getFloatValue(), this.distance.getFloatValue());
};
	
	
SFCameraData.prototype["generateNewDatasetInstance"]=function(){
	return new SFCameraData();
};
	
