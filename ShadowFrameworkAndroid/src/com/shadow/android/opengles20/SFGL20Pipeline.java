package com.shadow.android.opengles20;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.expression.SFBasicExpressionGenerator;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.pipeline.java.SFGL20PipelineMemory;

public class SFGL20Pipeline {
	public static void setup(){
		SFPipeline.setSfProgramBuilder(new SFGL20ProgramBuilder());
		SFPipeline.setSfPipelineGraphics(new SFGL20PipelineGraphics());
		SFPipeline.setSfPipelineMemory(new SFGL20PipelineMemory());
		SFPipeline.setSfTexturePipeline(new SFGL20TexturePipeline());
		SFExpressionGeneratorKeeper.getKeeper().setGenerator(new SFBasicExpressionGenerator());
	}
}
