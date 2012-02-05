
function SFProgramComponent(){
}

SFProgramComponent.prototype = {

	addRegister:function(global){
	if(set!=null)			set.add(global);//Warning: Not well Identified 
		registers.add(global);
	},

	addCodeString:function(function){
		code.add(function);
	},

	setGridInstance:function(grid){
		this.grid  = grid;
	},

	addStructureInstance:function(structure){
		structures.add(structure);
	},

	addParameter:function(parameter){
	if(set!=null)			set.add(parameter);//Warning: Not well Identified 
		temps.add(parameter);
	},

	getStructures:function(){
		return ,structures;
	},

	getGrid:function(){
		return ,grid;
	},

	loadShaderParameters:function(set){
		set.addAll(registers);
	},

	getRegisters:function(){
		return ,registers;
	},

	getShaderCodeLines:function(){
		return ,code;
	},

	getName:function(){
		return ,name;
	},

	setName:function(name){
		this.name    = name;
	},

	getParameterSet:function(){
		 if ( set==null ){
		  set=new  LinkedList<SFParameteri> (  );
		set.addAll(registers);
		set.addAll(temps);
	//for (Iterator<SFPipelineStructureInstance> iterator = structures.iterator();iterator.hasNext(););//Warning: Not well Identified 
	set.addAll(((SFPipelineStructureInstance) iterator.next()).getParameters());//Warning: Not well Identified 
	//}
	if(grid!=null)				set.addAll(((SFPipelineGridInstance) grid).getParameters());//Warning: Not well Identified 
	}
		return ,set;
	}

};