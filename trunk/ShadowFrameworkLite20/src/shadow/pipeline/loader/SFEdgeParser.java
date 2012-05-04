package shadow.pipeline.loader;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFEdgeParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
		
		ArrayList<String> edges=new ArrayList<String>();
			
			while(lineToken.hasMoreTokens()){
				edges.add(lineToken.nextToken());
			}

		builder.buildEdge(edges);
		
	}
}
