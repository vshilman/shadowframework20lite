
function SFRendering_Test006v00(){
}

SFRendering_Test006v00.prototype = {

	main:function(args){
		 SFRendering_Test006v00  universe = new  SFRendering_Test006v00();
		universe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		universe.setVisible(true);
	},

	init:function(){
	//init program		SFPipeline.getSfProgramBuilder().prepareProgram(program);//Warning: Not well Identified 
	},

	render:function(){
	//load program		SFPipeline.getSfProgramBuilder().loadProgram(program);//Warning: Not well Identified 
	//load material data		SFPipeline.getSfPipelineGraphics().loadStructureData(materialData, materialReference.getMaterialIndex());//Warning: Not well Identified 
	//load light data		SFPipeline.getSfPipelineGraphics().loadStructureData(lightData, lightReference.getMaterialIndex());//Warning: Not well Identified 
		f + = 0.01f;
		SFPipeline.getSfPipelineGraphics().rotateModel(0.25f+f,0,0);
	//load primitives		//SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		geometry.drawGeometry(1);
	}

};