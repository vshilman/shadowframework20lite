package shadow.pipeline.loader.parser;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.loader.SFParsableGrid;
import shadow.system.SFException;

public class SFEdgeParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
	
		SFParsableGrid grid=(SFParsableGrid)component;
		
		ArrayList<String> edges=new ArrayList<String>();
			
			while(lineToken.hasMoreTokens()){
				edges.add(lineToken.nextToken());
			}
		
		grid.loadEdge(edges);
		
		return component;
	}
}
