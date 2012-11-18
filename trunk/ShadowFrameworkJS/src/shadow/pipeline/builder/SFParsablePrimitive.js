


var SFParsablePrimitive_allCommands=[
	"domain",
	"block",
	"begin",
	"end"
];

function SFParsablePrimitive(){
	this.components=new Array();
	this.blocks=new Array();
}

inherit(SFParsablePrimitive,SFPrimitive);

SFParsablePrimitive.prototype["finalize"]=function(){
	
		var primitive=new SFPrimitive("",this.gridModel);
		
		var modules=this.components;
		var registers=this.blocks;
		
		primitive.setPrimitiveElements(registers, modules);
		
		primitive.setName(this.getName());
		SFPipeline_loadPrimitive(this.getName(), primitive);
	};

SFParsablePrimitive.prototype["getAllCommands"]=function(){
	return SFParsableProgramComponent_allCommands;
};

SFParsablePrimitive.prototype["setGridModel"]=function(gridModel){
	this.gridModel=gridModel;
};

SFParsablePrimitive.prototype["addComponent"]=function(block,component){
	this.components.push(component);
	this.blocks.push(block);
};	

SFParsablePrimitive.prototype["newInstance"]=function(){
	return new SFParsablePrimitive();
};