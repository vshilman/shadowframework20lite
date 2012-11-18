

function SFRigidTransformData(){
	this.position=new SFVertex3fData();
	this.orientation=new SFVertex3fData();
	this.scale=new SFFloat(1);
	var parameters=new SFNamedParametersObject();
	parameters.addObject(this.position);
	parameters.addObject(this.orientation);
	parameters.addObject(this.scale);
	this.setData(parameters);
}

inherit(SFRigidTransformData,SFDataAsset);


SFRigidTransformData.prototype["buildResource"]=function(){
	var matrix=new SFMatrix3f();

	var angles=new SFEulerAngles3f();
	angles.set(this.orientation.getVertex3f());
	angles.getMatrix(matrix);
	matrix.mult(this.scale.getFloatValue());
	
	var transform=new SFTransform3f();
	transform.setMatrix(matrix);
	transform.setPosition(this.position.getVertex3f());
	
	return transform;
};

SFRigidTransformData.prototype["generateNewDatasetInstance"]=function(){
	return new SFRigidTransformData();
};