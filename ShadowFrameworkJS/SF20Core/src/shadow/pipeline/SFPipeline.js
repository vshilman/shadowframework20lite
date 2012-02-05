
function SFPipeline(){
}

SFPipeline.prototype = {

	getPipeline:function(){
		return ,pipeline;
	},

	setSfTexturePipeline:function(sfTexturePipeline){
		pipeline.sfTexturePipeline  = sfTexturePipeline;
	},

	getSfProgramBuilder:function(){
		return ,pipeline.sfProgramBuilder;
	},

	setSfProgramBuilder:function(sfProgramBuilder){
		pipeline.sfProgramBuilder  = sfProgramBuilder;
	},

	getSfTexturePipeline:function(){
		return ,pipeline.sfTexturePipeline;
	},

	getSfPipelineMemory:function(){
		return ,pipeline.sfPipelineMemory;
	},

	setSfPipelineMemory:function(sfPipelineMemory){
		pipeline.sfPipelineMemory    = sfPipelineMemory;
	},

	getSfPipelineGraphics:function(){
		return ,pipeline.sfPipelineGraphics;
	},

	setSfPipelineGraphics:function(sfPipelineGraphics){
		pipeline.sfPipelineGraphics  = sfPipelineGraphics;
	},

	getModule:function(structureCode){
		 SFPipelineStructure  structure = pipeline.structures.get(structureCode);
		 if ( structure != null ){
		return ,structure;
	}
		 SFProgramComponent  component = pipeline.components.get(structureCode);
		 if ( component != null ){
		return ,component;
	}
		return ,pipeline.grids.get(structureCode);
	},

	getStructure:function(structureCode){
		return ,pipeline.structures.get(structureCode);
	},

	getStaticProgram:function(primitive, material[], light){
		 SFProgram  program = pipeline.sfProgramBuilder.generateNewProgram();
		program.setPrimitive(primitive);
		for ( int  i=0 ; i   < material.length ; i++ ){
		program.setMaterial(i,pipeline.components.get(material[i]));
	}
		program.setLightStep(pipeline.components.get(light));
		pipeline.sfProgramBuilder.prepareProgram(program);
	// Now program is returned, and it is ready to be used		return program;//Warning: Not well Identified 
	},

	getStaticImageProgram:function(material[], light){
		 SFProgram  program = pipeline.sfProgramBuilder.generateImageProgram();
		for ( int  i=0 ; i   < material.length ; i++ ){
		program.setMaterial(i,pipeline.components.get(material[i]));
	}
		program.setLightStep(pipeline.components.get(light));
		pipeline.sfProgramBuilder.prepareProgram(program);
		return ,program;
	}

};