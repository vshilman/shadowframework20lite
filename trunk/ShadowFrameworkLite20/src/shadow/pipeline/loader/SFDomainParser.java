package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFDomainParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder,
			StringTokenizer lineToken, int codeLine) throws SFException {

		if(builder.getComponent()!=null){
			if(lineToken.hasMoreElements()){
				String name=lineToken.nextToken();
				
				builder.buildDomain( name);
			}else{
				throw new SFException(codeLine+":  cannot use domain command without parameters");
			}
		}
	}
}
