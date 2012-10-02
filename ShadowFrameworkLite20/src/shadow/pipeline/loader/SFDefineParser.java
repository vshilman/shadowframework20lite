package shadow.pipeline.loader;

import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFDefineParser implements SFLineParser{

	@Override
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
		
		if(builder.getComponent()!=null){
			if(lineToken.hasMoreElements()){
				String wrote=lineToken.nextToken();
				
				StringTokenizer tWrote=new StringTokenizer(wrote,":");
				String pWrote=tWrote.nextToken();
				int number=new Integer(tWrote.nextToken());
				short type=(short)(SFParameteri.GLOBAL_FLOAT+number-1);
				
				String function="";
				if(lineToken.hasMoreElements()){
					function=lineToken.nextToken();
				}else{
					throw new SFException(codeLine+":  param command miss parameter type definition");
				}
				builder.buildDefineRule( pWrote, type, function);
			}else{
				throw new SFException(codeLine+":  cannot use param command without parameters");
			}
		}
	}
}
