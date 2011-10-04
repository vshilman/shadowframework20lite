package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.SFPipelineElement;
import shadow.system.SFException;

public interface SFLineParser {
	public SFParsableElement parseLine(SFParsableElement component,StringTokenizer lineToken,
			int codeLine) throws SFException;
}