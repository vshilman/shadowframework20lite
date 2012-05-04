package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFUseParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
	
		if(builder.getComponent()!=null){
			if(lineToken.hasMoreTokens()){
				String use=lineToken.nextToken();
				if(!lineToken.hasMoreElements()){
					builder.setUseRule(use);
				}
			}
		}
	}

	
}
