

function SFModel(){
	this.transform=new SFProgramModuleStructures();
	this.material=new SFProgramModuleStructures();
	this.programs=new Array();
	this.rootGeometry=null;
}

SFModel.prototype["getRootGeometry"]=function(){
	return this.rootGeometry;
};

SFModel.prototype["getMaterialComponent"]=function(){
	return this.material;
};

SFModel.prototype["setMaterialComponent"]=function(material){
	this.material = material;
};


SFModel.prototype["setRootGeometry"]=function(rootGeometry){
	this.rootGeometry = rootGeometry;
};


SFModel.prototype["setTransformComponent"]=function(transformComponent){
	this.transform = transformComponent;
};

SFModel.prototype["getTransformComponent"]=function(){
	return this.transform;
};	

SFModel.prototype["evaluateProgram"]=function(light){
	var program=SFPipeline_getStaticProgram(this.getRootGeometry().getPrimitive(),
			this.transform.getProgram(),this.material.getProgram(),light.getProgram());
	this.programs[light.getProgram()]=program;
	
	return program;
};	

SFModel.prototype["cleanPrograms"]=function(){
	this.programs.length=0;
};	

SFModel.prototype["getProgram"]=function(light){
	var program=this.programs[light.getProgram()];
	if(!(program===undefined)){
		return program;
	}
	return this.evaluateProgram(light);
};	

SFModel.prototype["clone"]=function(){
	var model=new SFModel();
	model.rootGeometry=this.rootGeometry;
	model.transform=this.transform.clone();
	model.setMaterialComponent(this.getMaterialComponent().clone());
	return model;
};	

	