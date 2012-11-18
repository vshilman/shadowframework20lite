
function SFGL20Pipeline_setup(){
	SFPipeline_setSfProgramBuilder(new SFGL20ProgramBuilder());
	SFPipeline_setSfPipelineGraphics(new SFGL20PipelineGraphics());
	SFPipeline_setSfPipelineMemory(new SFGL20PipelineMemory());
	SFPipeline_setSfTexturePipeline(new SFGL20TexturePipeline());
	SFExpressionGeneratorKeeper_getKeeper().setGenerator(new SFBasicExpressionGenerator());
}
	