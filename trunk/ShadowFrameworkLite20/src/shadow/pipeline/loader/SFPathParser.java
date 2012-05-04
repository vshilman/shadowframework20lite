package shadow.pipeline.loader;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFPathParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
	
		ArrayList<String> paths=new ArrayList<String>();
			
			while(lineToken.hasMoreTokens()){
				paths.add(lineToken.nextToken());
			}

		builder.buildPath(paths);
		
	}
}
