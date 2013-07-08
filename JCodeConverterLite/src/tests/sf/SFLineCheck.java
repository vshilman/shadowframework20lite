package tests.sf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import tests.TestingUtilities;
import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.utility.FileStringUtility;

public class SFLineCheck {

	private static int totalLineCount=0;
	private static int errorLine=0;
	private static boolean report=true;

	private static FileWriter fileOutputStream;

	public static void main(String[] args) {

		try {
			fileOutputStream=new FileWriter(new File("log.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		translateLibrary("../ShadowFrameworkLite20/src","../ShadowFrameworkJS/SF20Core");
		translateLibrary("../ShadowFrameworkLite20_OpenGL20/src","../ShadowFrameworkJS/SF20OpenGL20Graphics");

		System.err.println("Total Line Count : "+totalLineCount);
		System.err.println("Total Error Line : "+errorLine);
		System.err.println("Rap : "+(errorLine/(1.0f*totalLineCount)));

		try {
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void translateLibrary(String filename,String pattern){
		//System.out.println(filename);
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
					checkFile(filename);
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

	public static void checkFile(String inputFilename) {

		System.out.println("Checking file "+inputFilename);

//		String totalString=TestingUtilities.generateFileString(FileStringUtility.getStream(inputFilename));
//		//TestingUtilities.writeString(out,totalString);
//
//		Block fileBlock=BlockUtilities.generateBlocks(totalString.toCharArray());
		Block fileBlock=BlockUtilities.generateBlocksFromStream(FileStringUtility.getStream(inputFilename));
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
	}
}
