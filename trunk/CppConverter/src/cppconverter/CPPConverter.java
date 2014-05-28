package cppconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import sf.utils.files.BufferedReaderIterator;
import sf.utils.files.FileIterator;
import sf.utils.files.FileUtils;

public class CPPConverter {

	private static ArrayList<DataMessage> cppConversions=new ArrayList<DataMessage>();
	private static ArrayList<DataMessage> hConversions=new ArrayList<DataMessage>();
	
	static{
		try {
			loadConversionRules(cppConversions, "config/cppconversion");
			loadConversionRules(hConversions, "config/hconversion");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void loadConversionRules(ArrayList<DataMessage> conversion,
			String filename) throws FileNotFoundException {
		BufferedReaderIterator reader=new BufferedReaderIterator(new FileReader(filename));
		String lastSource="";
		while(reader.hasNext()){
			String line=reader.next();
			if(line.startsWith("-----")){
				line=line.substring(5);
				conversion.add(new DataMessage(lastSource, line));
			}else{
				lastSource=line;
			}
		}
	}
	
	
	static String sourceFolder="D:/Development/Workspaces/ShadowFramework2.0/ShadowFramework2.0RenderingData/src";
	static String destFolder="D:/Development/Workspaces/ProjectInspector/CppConverter/out/RenderingDataSrc";

//	static String sourceFolder="D:/Development/Workspaces/ShadowFramework2.0/ShadowFramework2.0RenderingEngine/src";
//	static String destFolder="D:/Development/Workspaces/ProjectInspector/CppConverter/out/RenderingSrc";
	
	public static void main(String[] args) {
		
		FileIterator filteIterator=new FileIterator(sourceFolder);
		
		for (File in : filteIterator) {
				//System.err.println(f.getName());
			
			File out=FileUtils.replacedFile(in, sourceFolder, destFolder);
			out=FileUtils.changeExtension(out, "cpp");
			FileUtils.touch(out);
			writeFile(out,in,cppConversions);
			
			out=FileUtils.changeExtension(out, "h");
			FileUtils.touch(out);
			writeFile(out,in,hConversions);
			//FileUtils.fileCopy(f, out);

		}
		
	}
	
	public static void writeFile(File out,File in,ArrayList<DataMessage> conversions){
		
		System.err.println(out.getName());
		try {
			
			ArrayList<String> lines=new ArrayList<String>();
			
			BufferedReaderIterator reader=new BufferedReaderIterator(new FileReader(in));

			HashMap<String, String> propValues=new HashMap<String, String>();
			
			for (String string : reader) {
				convertLine(conversions, lines, propValues, string);
			}
			
			convertLine(conversions, lines, propValues, "EOF ____");
			
			FileUtils.writeFile(out.getAbsolutePath(), lines);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	private static String convertLine(ArrayList<DataMessage> conversions,
			ArrayList<String> lines, HashMap<String, String> propValues,
			String string) {
		String conversion=null;
		String sourceString=string;
		string=string.trim();
		
		String shift=sourceString.substring(0,sourceString.indexOf(string));
		
		String foundSource=null;
		
		for (int i = 0; i < conversions.size(); i++) {
			if(foundSource==null || foundSource.equalsIgnoreCase(conversions.get(i).getOriginalMessage())){
				
				String converted=conversions.get(i).checkString(string);
				if(converted!=null){
					converted=checkString(converted);
					conversion=converted;
					lines.add(shift+converted);
					foundSource=conversions.get(i).getOriginalMessage();
					//propValues.putAll(conversions.get(i).getPropValues());
					//System.err.println("Recognized "+string+" AS "+foundSource+" TANSLATED AS "+converted);
				}
			}
		}
		if(conversion==null && string.length()>0){
			lines.add(""+checkString(sourceString));
		}
		if(conversion==null && string.length()==0){
			lines.add("");
		}
		return string;
	}
	

	public static String checkString(String toCheck){
		toCheck=toCheck.replace("this.", "this->");
		toCheck=toCheck.replace("public ", "");
		toCheck=toCheck.replace("private ", "");
		toCheck=toCheck.replace("protected ", "");
		toCheck=toCheck.replace("boolean ", "bool");
		toCheck=toCheck.replace("SFNamedParametersObject namedParameters", "SFNamedParametersObject* namedParameters");
		toCheck=toCheck.replace("namedParameters.addObject", "namedParameters->addObject");
		return toCheck;
	}
}
