package shadow.pipeline.loader.parser;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.loader.SFParsableGrid;
import shadow.system.SFException;

public class SFPathParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
	
		SFParsableGrid grid=(SFParsableGrid)component;
		
		ArrayList<String> paths=new ArrayList<String>();
			
			while(lineToken.hasMoreTokens()){
				paths.add(lineToken.nextToken());
			}
		
		grid.loadPath(paths);
		
		return component;
	}
}
