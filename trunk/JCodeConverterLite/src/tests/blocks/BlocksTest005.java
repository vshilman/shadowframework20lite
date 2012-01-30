package tests.blocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.java.JavaConstructorDeclaration;
import codeconverter.javatojs.JSCodeTranslator;
import codeconverter.utility.FileStringUtility;

public class BlocksTest005 {

	public static void main(String[] args) {
		translateLibrary("../ShadowFrameworkLite20/src","../ShadowFrameworkJS/SF20Core");
		translateLibrary("../ShadowFrameworkLite20_OpenGL20/src","../ShadowFrameworkJS/SF20OpenGL20Graphics");
	}
	
	public static void translateLibrary(String filename,String pattern){
		System.out.println(filename);
		File f=new File(filename);
		if(f.isDirectory()){
			if(!(f.getName().startsWith("."))){
				File[] files=f.listFiles();
				for (int i=0; i < files.length; i++) {
					String directory=pattern+"/"+f.getName();
					
					File directoryFile=new File(directory);
					
					if(!(directoryFile.exists())){
							directoryFile.mkdir();
					}
					translateLibrary(files[i].getAbsolutePath(),directory);
				}
			}
		}else{
			if(filename.endsWith(".java")){
				String name=f.getName();
				String file=name.substring(0,name.length()-5)+".js";
				file=pattern+"/"+file;
				
				String translation="";
				
				try {
					translation=translateFile(filename);
				} catch (Exception e1) {
					e1.printStackTrace();
					System.err.println(filename+" was not translated!");
				}
				
				try {
					BufferedWriter writer=new BufferedWriter(new FileWriter(new File(file)));
					writer.write(translation);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}else{
				System.out.println("Not a directory "+f.exists());
			}
		}
	}

	public static String translateFile(String inputFilename) {
		List<String> list=FileStringUtility.loadTextFile(inputFilename);

		StringWriter writer=new StringWriter();
		for (String string : list) {
			writer.write(string);
		}

		String totalString=writer.toString();
		int beginof=totalString.indexOf("/*");
		int endof=totalString.indexOf("*/");
		while(beginof!=-1 && endof!=-1){
			String tmp=totalString.substring(0,beginof);
			tmp+=totalString.substring(endof+2);
			totalString=tmp;
			beginof=totalString.indexOf("/*");
			endof=totalString.indexOf("*/");
			System.out.println();
		}
		char[] totalStringChars=totalString.toCharArray();

		Block fileBlock=BlockUtilities.generateBlocks(totalStringChars);

		System.out.println(fileBlock);	
		
		BlockInterpreter interpreter=new BlockInterpreter(new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation=interpreter.getInterpretation(fileBlock);
		
		JSCodeTranslator translator=new JSCodeTranslator();

		Set<CodeModule> keys=interpretation.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern=interpretation.get(codeModule);
			if(pattern!=null && (pattern instanceof JavaConstructorDeclaration))
				System.err.println("["+codeModule+"]:"+pattern);
		}
		
		String translation=translator.translateCode(fileBlock, interpretation);
		return translation;
	}
}
