

var SFParsableProgramComponent_allCommands=[
	"begin",
	"use",
	"write",
	"rewrite",
	"param",
	"grid",
	"define",
	"end"
];

function SFParsableProgramComponent(){
	this.code=new Array();
	this.registers=new Array();
	this.temps=new Array();
	this.structures=new Array();
	this.grid=new Array();
}

inherit(SFParsableProgramComponent,SFProgramComponent);

SFParsableProgramComponent.prototype["clone"]=function(){
	return new SFParsableProgramComponent();
	};

SFParsableProgramComponent.prototype["finalize"]=function(){
		SFPipeline_loadShaderComponent(this.getName(), this);
	};

SFParsableProgramComponent.prototype["getAllCommands"]=function(){
		return SFParsableProgramComponent_allCommands;
	};

SFParsableProgramComponent.prototype["newInstance"]=function(){
		return new SFParsableProgramComponent();
	};
	