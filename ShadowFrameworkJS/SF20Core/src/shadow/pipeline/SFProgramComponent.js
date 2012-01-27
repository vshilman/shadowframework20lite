

SFProgramComponent.prototype = {

	addRegister:function(global){
	if(set!=null)			set.add(global);//Warning: Not well Identified 
	registers.add(global);//Warning: Not well Identified 
	},

	addCodeString:function(function){
	code.add(function);//Warning: Not well Identified 
	},

	setGridInstance:function(grid){
		this.grid=grid;
	},

	addStructureInstance:function(structure){
	structures.add(structure);//Warning: Not well Identified 
	},

	addParameter:function(parameter){
	if(set!=null)			set.add(parameter);//Warning: Not well Identified 
	temps.add(parameter);//Warning: Not well Identified 
	},

	getStructures:function(){
		return this.structures;
	},

	getGrid:function(){
		return this.grid;
	},

	loadShaderParameters:function(set){
	set.addAll(registers);//Warning: Not well Identified 
	},

	getRegisters:function(){
		return this.registers;
	},

	getShaderCodeLines:function(){
		return this.code;
	},

	getName:function(){
		return this.name;
	},

	setName:function(name){
		this.name=name;
	},

	getParameterSet:function(){
		return this.set;
	}

};