

var SFParsableProgramModule_allCommands=[
	"domain",
	"component",
	"end"
];

function SFParsableProgramModule(){
	this.components=new Array();
}

inherit(SFParsableProgramModule,SFProgramModule);

SFParsableProgramModule.prototype["finalize"]=function(){
		var module=this.clone();
		
		var modules=this.components;
		module.setComponents(modules);
		
		SFPipeline_loadShaderModule(this.getName(), module);
	};

SFParsableProgramModule.prototype["getAllCommands"]=function(){
		return SFParsableProgramComponent_allCommands;
	};

SFParsableProgramModule.prototype["addComponent"]=function(component){
		this.components.push(component);
	};	
	
SFParsableProgramModule.prototype["newInstance"]=function(){
	return new SFParsableProgramModule();
};