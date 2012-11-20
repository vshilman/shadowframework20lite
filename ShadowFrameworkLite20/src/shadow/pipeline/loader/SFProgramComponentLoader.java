package shadow.pipeline.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.builder.SFBuilderElement;
import shadow.pipeline.builder.SFIPipelineBuilder;
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
		parsers.put(COMMAND_STRING+"rewrite",new SFRewriteParser());
		parsers.put(COMMAND_STRING+"end",new SFEndParser());
		parsers.put(COMMAND_STRING+"include",new SFIncludeParser());
		parsers.put(COMMAND_STRING+"component",new SFComponentParser());
		parsers.put(COMMAND_STRING+"block",new SFBlockParser());
		parsers.put(COMMAND_STRING+"domain",new SFDomainParser());
		parsers.put(COMMAND_STRING+"texture",new SFTextureParser());
		//parsers.put(COMMAND_STRING+"vertex",new SFVertexParser());
		//parsers.put(COMMAND_STRING+"edge",new SFEdgeParser());
		//parsers.put(COMMAND_STRING+"path",new SFPathParser());
		//parsers.put(COMMAND_STRING+"internal",new SFInternalParser());
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
		public void parseLine(SFIPipelineBuilder builder,
				StringTokenizer lineToken, int codeLine) throws SFException {
			
			if(lineToken.hasMoreTokens()){
				String filename=lineToken.nextToken();
				if(!lineToken.hasMoreElements()){
					String parsingRooString_=parsingRoot;
						String file=parsingRoot+"/"+filename;
						try {
							loadComponents(new File(file),builder);
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
		}
	}
	
	public static void loadComponents(InputStream stream,SFIPipelineBuilder builder) throws IOException,
			SFPipelineModuleWrongException{
		parsingRoot="";
		BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
		loadComponents(reader,builder);
	}
	
	public static void loadComponents(File f,SFIPipelineBuilder builder) throws IOException,
			SFPipelineModuleWrongException{
		parsingRoot=f.getParent();
		BufferedReader reader=new BufferedReader(new FileReader(f));
		loadComponents(reader,builder);
	}
	

	private static void loadComponents(BufferedReader stream,SFIPipelineBuilder builder) throws IOException,
						SFPipelineModuleWrongException{
		
		ArrayList<SFBuilderElement> components=new ArrayList<SFBuilderElement>();
		SFBuilderElement temp=null;
		
		String line=stream.readLine();
		int lineCounter=0;
		
		while(line!=null){

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
					builder.setComponent((SFPipelineElement)temp);
					parser.parseLine(builder, tokenizer, lineCounter);
					if(builder.getComponent()==null && temp!=null)
						components.add(temp);
					temp=(SFBuilderElement)builder.getComponent();
				}else if(!command.startsWith(COMMENT_STRING)){
					throw new SFException("Unknown Command Line (" +lineCounter+"):"+line);
				}
			}//else line is blank, and that is ok.
			
			line=stream.readLine();
			lineCounter++;
		}
	}
}
