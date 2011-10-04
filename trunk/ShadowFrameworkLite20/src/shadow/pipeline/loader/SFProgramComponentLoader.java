package shadow.pipeline.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.loader.parser.SFBeginParser;
import shadow.pipeline.loader.parser.SFDefineParser;
import shadow.pipeline.loader.parser.SFEdgeParser;
import shadow.pipeline.loader.parser.SFEndParser;
import shadow.pipeline.loader.parser.SFFloat2Parser;
import shadow.pipeline.loader.parser.SFFloat3Parser;
import shadow.pipeline.loader.parser.SFFloat4Parser;
import shadow.pipeline.loader.parser.SFFloatParser;
import shadow.pipeline.loader.parser.SFGridParser;
import shadow.pipeline.loader.parser.SFInternalParser;
import shadow.pipeline.loader.parser.SFMatrix2Parser;
import shadow.pipeline.loader.parser.SFMatrix3Parser;
import shadow.pipeline.loader.parser.SFMatrix4Parser;
import shadow.pipeline.loader.parser.SFParamParser;
import shadow.pipeline.loader.parser.SFPathParser;
import shadow.pipeline.loader.parser.SFUseParser;
import shadow.pipeline.loader.parser.SFVertexParser;
import shadow.pipeline.loader.parser.SFWriteParser;
import shadow.system.SFException;

public class SFProgramComponentLoader {

	private static final String COMMAND_STRING="@";
	private static final String COMMENT_STRING="//";
	private static Hashtable<String, SFLineParser> parsers=new Hashtable<String, SFLineParser>(); 
	
	static{
		parsers.put(COMMAND_STRING+"begin",new SFBeginParser());
		parsers.put(COMMAND_STRING+"use",new SFUseParser());
		parsers.put(COMMAND_STRING+"param",new SFParamParser());
		parsers.put(COMMAND_STRING+"grid",new SFGridParser());	
		parsers.put(COMMAND_STRING+"define",new SFDefineParser());
		parsers.put(COMMAND_STRING+"write",new SFWriteParser());
		parsers.put(COMMAND_STRING+"end",new SFEndParser());
		parsers.put(COMMAND_STRING+"include",new SFIncludeParser());
		parsers.put(COMMAND_STRING+"vertex",new SFVertexParser());
		parsers.put(COMMAND_STRING+"edge",new SFEdgeParser());
		parsers.put(COMMAND_STRING+"path",new SFPathParser());
		parsers.put(COMMAND_STRING+"internal",new SFInternalParser());
		parsers.put(COMMAND_STRING+"float",new SFFloatParser());
		parsers.put(COMMAND_STRING+"float2",new SFFloat2Parser());
		parsers.put(COMMAND_STRING+"float3",new SFFloat3Parser());
		parsers.put(COMMAND_STRING+"float4",new SFFloat4Parser());
		parsers.put(COMMAND_STRING+"matrix2",new SFMatrix2Parser());
		parsers.put(COMMAND_STRING+"matrix3",new SFMatrix3Parser());
		parsers.put(COMMAND_STRING+"matrix4",new SFMatrix4Parser());
	}
	
	private static String parsingRoot="";
	
	private static class SFIncludeParser implements SFLineParser{
		
		@Override
		public SFParsableElement parseLine(SFParsableElement component,
				StringTokenizer lineToken, int codeLine)
				throws SFException {
			
			if(lineToken.hasMoreTokens()){
				String filename=lineToken.nextToken();
				if(!lineToken.hasMoreElements()){
					String parsingRooString_=parsingRoot;
						String file=parsingRoot+"\\"+filename;
						try {
							loadComponents(new File(file));
						} catch (IOException e) {
							throw new SFException("Cannot Open Inclueded File "+filename);
						} catch (SFPipelineModuleWrongException e) {
							String message="Errors in File Include "+file+":";
							ArrayList<String> errors=e.getList();
							for (int i = 0; i < errors.size(); i++) {
								message+=errors.get(i)+"\n";
							}
							throw new SFException(message);
						}
					parsingRoot=parsingRooString_;
				}
			}	
			return component;
		}
	}
	
	public static void loadComponents(File f) throws IOException,
			SFPipelineModuleWrongException{
		parsingRoot=f.getParent();
		BufferedReader reader=new BufferedReader(new FileReader(f));
		loadComponents(reader);
	}
	

	private static void loadComponents(BufferedReader stream) throws IOException,
						SFPipelineModuleWrongException{
		
		ArrayList<SFParsableElement> components=new ArrayList<SFParsableElement>();
		SFParsableElement temp=null;
		
		ArrayList<String> errors=new ArrayList<String>();
		
		String line=stream.readLine();
		int lineCounter=0;
		
		while(line!=null){
			
			try {
				StringTokenizer tokenizer=new StringTokenizer(line);
				if(tokenizer.hasMoreTokens()){
					String command=tokenizer.nextToken();
					if(command.startsWith(COMMAND_STRING)){
						SFLineParser parser=parsers.get(command);
						if(parser==null){
							throw new SFException("Unknown Command Line (" +lineCounter+"):"+line);
						}
						if(temp!=null){
							boolean found=false;
							for (String string : temp.getAllCommands()) {
								found=found || (COMMAND_STRING+string).equalsIgnoreCase(command);
							}
							if(!found){
								throw new SFException("Unknown or Invalid Command Line (" +lineCounter+"):"+line);
							}	
						}
						SFParsableElement sfsc=parser.parseLine(temp, tokenizer, lineCounter);
						if(sfsc==null && temp!=null)
							components.add(temp);
						temp=sfsc;
					}else if(!command.startsWith(COMMENT_STRING)){
						throw new SFException("Unknown Command Line (" +lineCounter+"):"+line);
					}
				}//else line is blank, and that is ok.
			}catch (SFException e) {
				String errorMessage=e.getMessage();
				errors.add(errorMessage);
			}
			
			line=stream.readLine();
			lineCounter++;
		}

		if(errors.size()!=0){
			SFPipelineModuleWrongException sfCwex=new SFPipelineModuleWrongException();
			sfCwex.setList(errors);
			throw sfCwex;
		}	
	}
}
