package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFParameterParser implements SFLineParser{

	public short parameter;
	
	public SFParameterParser(Short parameter) {
		super();
		this.parameter=parameter;
	}

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
		
		if(builder.getComponent()!=null){
			if(lineToken.hasMoreTokens()){
				String use=lineToken.nextToken();
				if(!lineToken.hasMoreElements()){					
					builder.buildParamRule(parameter, use);
				}
			}
		}
	}
}