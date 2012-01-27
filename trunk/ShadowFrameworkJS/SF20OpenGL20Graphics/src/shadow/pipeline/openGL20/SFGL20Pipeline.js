

SFGL20Pipeline.prototype = {

	setup:function(){
	SFPipeline.setSfProgramBuilder(new SFGL20ProgramBuilder());//Warning: Not well Identified 
	SFPipeline.setSfPipelineGraphics(new SFGL20PipelineGraphics());//Warning: Not well Identified 
	SFPipeline.setSfPipelineMemory(new SFGL20PipelineMemory());//Warning: Not well Identified 
	SFPipeline.setSfTexturePipeline(new SFGL20TexturePipeline());//Warning: Not well Identified 
	SFExpressionParser.getParser().setGenerator(new SFGL20ExpressionGenerator());//Warning: Not well Identified 
	}

};