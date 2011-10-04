package shadow.pipeline.loader.parser;

import java.util.StringTokenizer;

import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.loader.SFParsableGrid;
import shadow.system.SFException;

public class SFVertexParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
	
		SFParsableGrid grid=(SFParsableGrid)component;
		
		if(lineToken.hasMoreTokens()){
			grid.loadVertex(lineToken.nextToken());
		}
		
		return component;
	}
}
