package shadow.pipeline.loader.parser;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.loader.SFParsableGrid;
import shadow.system.SFException;

public class SFInternalParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
		
		SFParsableGrid grid=(SFParsableGrid)component;
		
		ArrayList<String> internals=new ArrayList<String>();
			
			while(lineToken.hasMoreTokens()){
				internals.add(lineToken.nextToken());
			}
		
		grid.loadInternal(internals);
		
		return component;
	}
}
