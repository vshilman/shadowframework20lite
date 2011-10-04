package shadow.pipeline.loader.parser;

import java.util.StringTokenizer;

import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.system.SFException;

public class SFEndParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
		
		if(component!=null)
			component.finalize();
		
		return null;
	}
}
