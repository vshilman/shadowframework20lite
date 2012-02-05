
function SFRendering_Test005(){
}

SFRendering_Test005.prototype = {

	main:function(args){
		 SFRendering_Test005  universe = new  SFRendering_Test005();
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
	//		SFPipeline.getSfPipelineGraphics().translateModel(0,0,0);//Warning: Not well Identified 
	//		//		//draw primitives//		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
	//SFPipeline.getSfPipelineGraphics().translateModel(-0.1f,0,0);//Warning: Not well Identified 
	//SFPipeline.getSfPipelineGraphics().translateModel(0,0.1f,0);//Warning: Not well Identified 
	f+=.03f;//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*0.0f+f,0,0);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*0.5f+f,0,0);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*1.0f+f,0,0);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*1.5f+f,0,0);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*0.0f+f,0,3.14f);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*0.5f+f,0,3.14f);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*1.0f+f,0,3.14f);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
		SFPipeline.getSfPipelineGraphics().rotateModel(3.14f*1.5f+f,0,3.14f);
	//draw primitives		SFPipeline.getSfPipelineGraphics().drawPrimitives(primitiveData, 0, 1);//Warning: Not well Identified 
	}

};