package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFVertexParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder,
			StringTokenizer lineToken, int codeLine) throws SFException {
	
		if(lineToken.hasMoreTokens()){
			builder.addGridVertex(lineToken.nextToken());
		}
		
	}
}
