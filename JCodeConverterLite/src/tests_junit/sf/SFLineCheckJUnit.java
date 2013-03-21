package tests_junit.sf;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import tests.TestingUtilities;
import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;

public class SFLineCheckJUnit {

	private static int totalLineCount=0;
	private static int errorLine=0;
	private static boolean report=true;
	
	private static FileWriter fileOutputStream;
	
	@Test
	public void test() {
		try {
			fileOutputStream=new FileWriter(new File("log.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		String s=translateLibrary("../ShadowFrameworkLite20/src","../ShadowFrameworkJS/SF20Core")+"\n";
		s+=translateLibrary("../ShadowFrameworkLite20_OpenGL20/src","../ShadowFrameworkJS/SF20OpenGL20Graphics")+"\n";
	
		
		s+="Total Line Count : "+totalLineCount+"\n";
		s+="Total Error Line : "+errorLine+"\n";
		s+="Rap : "+(errorLine/(1.0f*totalLineCount))+"\n";
		
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(s, "Not a directory false\nNot a directory false\nTotal Line Count : 0\nTotal Error Line : 0\nRap : NaN\n");
		
	}
	
	public static String translateLibrary(String filename,String pattern){
		//System.out.println(filename);
		File f=new File(filename);
		String s="";
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
					s+=filename;
				} catch (Exception e1) {
					e1.printStackTrace();
					s+=filename+" was not translated!";
				}
				
				try {
					BufferedWriter writer=new BufferedWriter(new FileWriter(new File(file)));
					writer.write(translation);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}else{
				s+="Not a directory "+f.exists();
			}
		}
		return s;
	}
	
	public static String checkFile(String inputFilename) {

		String s="Checking file "+inputFilename;
		
		String totalString=TestingUtilities.generateFileString(inputFilename);
		//TestingUtilities.writeString(out,totalString);
		
		Block fileBlock=BlockUtilities.generateBlocks(totalString.toCharArray());
		//TestingUtilities.printBlock(out,fileBlock);
			
		BlockInterpreter interpreter=new BlockInterpreter(new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation=interpreter.getInterpretation(fileBlock);
		//TestingUtilities.writeInterpretations(out,interpretation);
		if(report){
			int count = TestingUtilities.reportWrongInterpretation(fileOutputStream,interpretation);
			errorLine+=count;
			totalLineCount+=interpretation.size();	
		}else{
			int count = TestingUtilities.countWrongInterpretation(interpretation);
			errorLine+=count;
			totalLineCount+=interpretation.size();
		}
		return s;
	}

}
