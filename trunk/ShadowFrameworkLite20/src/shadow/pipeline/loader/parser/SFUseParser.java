package shadow.pipeline.loader.parser;

import java.util.StringTokenizer;

import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFUseParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
		
		if(component!=null){
			if(lineToken.hasMoreTokens()){
				String use=lineToken.nextToken();
				if(!lineToken.hasMoreElements()){
					SFPipelineRegister global=SFPipelineRegister.getFromName(use);
					SFProgramComponent cmp=(SFProgramComponent)component;
					cmp.addRegister(global);
				}
			}
		}
		return component;
	}
}
