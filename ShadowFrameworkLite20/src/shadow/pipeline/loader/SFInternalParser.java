//package shadow.pipeline.loader;
//
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//import shadow.pipeline.builder.SFIPipelineBuilder;
//import shadow.system.SFException;
//
//public class SFInternalParser implements SFLineParser{
//
//	@Override
//	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
//			int codeLine) throws SFException {
//		
//		ArrayList<String> internals=new ArrayList<String>();
//			
//			while(lineToken.hasMoreTokens()){
//				internals.add(lineToken.nextToken());
//			}
//		
//		builder.buildGridInternals(internals);
//		
//	}
//}
