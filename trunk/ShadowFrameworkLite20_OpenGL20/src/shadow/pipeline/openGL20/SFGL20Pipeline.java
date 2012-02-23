package shadow.pipeline.openGL20;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;

public class SFGL20Pipeline {
	public static void setup(){
		SFPipeline.setSfProgramBuilder(new SFGL20ProgramBuilder());
		SFPipeline.setSfPipelineGraphics(new SFGL20PipelineGraphics());
		SFPipeline.setSfPipelineMemory(new SFGL20PipelineMemory());
		SFPipeline.setSfTexturePipeline(new SFGL20TexturePipeline());
		SFExpressionGeneratorKeeper.getKeeper().setGenerator(new SFGL20ExpressionGenerator());
	}
}
