package shadow.pipeline.loader.parser;

import java.util.StringTokenizer;

import shadow.pipeline.SFFunction;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.expression.SFExpressionParser;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFWriteParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer tokenizer, int codeLine)
			throws SFException {
	
		if(component!=null){
			if(tokenizer.hasMoreElements()){
				String wrote=tokenizer.nextToken();
				
				SFPipelineRegister global=SFPipelineRegister.getFromName(wrote);
				SFProgramComponent cmp=(SFProgramComponent)component;
				cmp.addRegister(global);
				
				if(tokenizer.hasMoreElements()){
					String function=tokenizer.nextToken();
					SFFunction functionCode=new SFFunction(global,SFExpressionParser.getParser().parseString(function, cmp.getParameterSet()),cmp.getParameterSet());
					cmp.addCodeString(functionCode);
				}else{
					throw new SFException(codeLine+":  param command miss parameter type definition");
				}
			}else{
				throw new SFException(codeLine+":  cannot use param command without parameters");
			}
		}
		return component;
	}	
}