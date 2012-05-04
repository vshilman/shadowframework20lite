package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFEndParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
		builder.closeElement();
	}
}
