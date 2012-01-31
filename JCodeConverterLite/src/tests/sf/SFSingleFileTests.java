package tests.sf;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;

import tests.TestingUtilities;
import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.javatojs.JSCodeTranslator;

public class SFSingleFileTests {

	public static void main(String[] args) {
		//List<String> list=FileStringUtility
		//		.loadTextFile("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java");
		
		//String filename="../ShadowFrameworkLite20/src/shadow/pipeline/SFFunction.java";
		String filename="../ShadowFrameworkLite20/src/shadow/pipeline/SFPipelineGrid.java";
		File file=new File(filename);
		System.out.println("Does file exists? "+file.exists());
		
		PrintStream out=System.out;
		PrintStream err=System.err;
		
		String totalString=TestingUtilities.generateFileString(filename);
		//TestingUtilities.writeString(out,totalString);
		
		Block fileBlock=BlockUtilities.generateBlocks(totalString.toCharArray());
		//TestingUtilities.printBlock(out,fileBlock);
			
		BlockInterpreter interpreter=new BlockInterpreter(new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation=interpreter.getInterpretation(fileBlock);
		//TestingUtilities.writeInterpretations(out,interpretation);
		TestingUtilities.reportWrongInterpretation(err,interpretation);
		
		JSCodeTranslator translator=new JSCodeTranslator();
		String translation=translator.translateCode(fileBlock, interpretation);
		out.println(translation);
	}
}
