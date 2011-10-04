package shadow.pipeline.loader.parser;

import java.util.StringTokenizer;

import shadow.pipeline.SFFunction;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFDefineParser implements SFLineParser{

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer tokenizer, int codeLine)
			throws SFException {
		
		if(component!=null){
			if(tokenizer.hasMoreElements()){
				String wrote=tokenizer.nextToken();
				
				StringTokenizer tWrote=new StringTokenizer(wrote,":");
				String pWrote=tWrote.nextToken();
				int number=new Integer(tWrote.nextToken());
				short type=(short)(SFParameteri.GLOBAL_FLOAT+number-1);
				
				SFParameter param=new SFParameter(pWrote,type);
				SFProgramComponent cmp=(SFProgramComponent)component;
				cmp.addParameter(param);
				
				if(tokenizer.hasMoreElements()){
					String function=tokenizer.nextToken();
					SFFunction functionCode=new SFFunction(param,function,cmp.getParameterSet());
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
