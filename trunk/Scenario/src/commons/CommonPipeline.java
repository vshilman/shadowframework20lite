package commons;

import java.io.File;
import java.io.IOException;

import org.java_websocket.util.Base64.InputStream;

import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;

public class CommonPipeline {
	
	public static String commonPipeline="/pipeline";


	public static void prepare() {
		SFGL20Pipeline.setup();
		try {
		
			SFProgramComponentLoader.loadComponents(CommonPipeline.class.getResourceAsStream(commonPipeline),new SFPipelineBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
	}

	
}
