
function SFGL20Pipeline(){
}

SFGL20Pipeline.prototype = {

	setup:function(){
		SFPipeline.setSfProgramBuilder(new SFGL20ProgramBuilder());
		SFPipeline.setSfPipelineGraphics(new SFGL20PipelineGraphics());
		SFPipeline.setSfPipelineMemory(new SFGL20PipelineMemory());
		SFPipeline.setSfTexturePipeline(new SFGL20TexturePipeline());
		SFExpressionParser.getParser().setGenerator(new SFGL20ExpressionGenerator());
	}

};