

function SFProgramModule(name){
	if(!(name===undefined))
	this.setName();
}

inherit(SFProgramModule,SFPipelineElement);

SFProgramModule.prototype["setComponents"]=function(components){
	this.components=components;
};

SFProgramModule.prototype["getComponents"]=function(){
	return this.components;
};		
	

SFProgramModule.prototype["clone"]=function(){

	var module=new SFProgramModule(this.name);
	module.components=this.components;
	return module;
};		
	