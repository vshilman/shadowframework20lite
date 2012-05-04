package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFWriteParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
	
		if(builder.getComponent()!=null){
			if(lineToken.hasMoreElements()){
				String wrote=lineToken.nextToken();
				if(lineToken.hasMoreElements()){
					String function=lineToken.nextToken();
					builder.buildWriteRule(wrote, function);
				}else{
					throw new SFException(codeLine+":  param command miss parameter type definition");
				}
			}else{
				throw new SFException(codeLine+":  cannot use param command without parameters");
			}
		}
	}	
}