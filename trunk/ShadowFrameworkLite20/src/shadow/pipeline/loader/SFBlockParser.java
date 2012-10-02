package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFBlockParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder,
			StringTokenizer lineToken, int codeLine) throws SFException {

		if(builder.getComponent()!=null){
			if(lineToken.hasMoreElements()){
				String block=lineToken.nextToken();
				
				if(lineToken.hasMoreElements()){
					String component=lineToken.nextToken();
					
					builder.buildBlock(block, component);
				}else{
					throw new SFException(codeLine+":  cannot use block with only one argument");
				}
			}else{
				throw new SFException(codeLine+":  cannot use block command without parameters");
			}
		}
	}
}
