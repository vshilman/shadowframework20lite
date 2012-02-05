
function SFRendering_Test003_2(){
}

SFRendering_Test003_2.prototype = {

	main:function(args){
		 SFRendering_Test003_2  universe = new  SFRendering_Test003_2();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
	},

	render:function(){
	//load program		SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	//load material data		//SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load light data		SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());//Warning: Not well Identified 
	//load primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
	}

};