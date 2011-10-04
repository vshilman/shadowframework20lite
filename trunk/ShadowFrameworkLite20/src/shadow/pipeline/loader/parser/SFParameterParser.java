package shadow.pipeline.loader.parser;

import java.util.StringTokenizer;

import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.parameters.SFParameter;
import shadow.system.SFException;

public class SFParameterParser implements SFLineParser{

	private short parameter;
	
	public SFParameterParser(Short parameter) {
		super();
		this.parameter=parameter;
	}

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine)
			throws SFException {
	
		if(component!=null){
			if(lineToken.hasMoreTokens()){
				String use=lineToken.nextToken();
				if(!lineToken.hasMoreElements()){					
					SFParameter param=new SFParameter(use, parameter);
					SFPipelineStructure cmp=(SFPipelineStructure)component;
					cmp.addParameter(param);
				}
			}
		}
		return component;
	}
}