
package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.system.SFException;

public class SFBeginParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
		
		if(builder.getComponent()!=null){
			throw new SFException("("+codeLine+") Begin called, but last Components is " +
					"not finished. use end command please :"+lineToken.nextToken());
		}

		String type,name;
		if(lineToken.hasMoreElements()){
			type=lineToken.nextToken();
			if(lineToken.hasMoreElements()){
				name=lineToken.nextToken();
				if(lineToken.hasMoreTokens()){
					throw new SFException("("+codeLine+"): Begin Command has too much parameters");
				}
				
				try {
					builder.generateElement(type, name);
				} catch (Exception e) {
					throw new SFException("("+codeLine+"):Begin Called with an undefined type ("+type+") only primitive," +
						"tesselator,transforms,material and lightstep are allowed");
				} 
				
				
			}else{
				throw new SFException("("+codeLine+"): Begin Command not well formed, a correct type and a name are required");
			}
		}else{
			throw new SFException("("+codeLine+"): Begin Command not well formed, a correct type and a name are required");
		}
	}

	
}
