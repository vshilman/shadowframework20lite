package shadow.renderer.contents.tests.common;

import java.io.File;
import java.io.IOException;

import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;

public class CommonPipeline {
	
	public static String commonPipeline="data/pipeline";

	private CommonPipeline() {
		prepare();
	}

	public static void prepare() {
		SFGL20Pipeline.setup();
		try {
			SFProgramComponentLoader.loadComponents(new File(commonPipeline),new SFPipelineBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
	}

	
}
