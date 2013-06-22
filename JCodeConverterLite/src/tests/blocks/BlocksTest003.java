package tests.blocks;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.javatojs.JSCodeTranslator;
import codeconverter.utility.FileStringUtility;

public class BlocksTest003 {

	public static void main(String[] args) {
		//List<String> list=FileStringUtility
		//		.loadTextFile("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java");

		//TODO : here we can use TestingUtilities.generateFileString, doing the same thing
		List<String> list=FileStringUtility.loadTextfromStream(FileStringUtility.getStream("src/testPackage/Expressions.java"));
		//List<String> list=FileStringUtility.loadTextFile("src/testPackage/House.java");

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
		}

		System.out.println(totalString);
		char[] totalStringChars=totalString.toCharArray();

		Block fileBlock=BlockUtilities.generateBlocks(totalStringChars);

		//System.out.println(fileBlock);

		BlockInterpreter interpreter=new BlockInterpreter(new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation=interpreter.getInterpretation(fileBlock);

		JSCodeTranslator translator=new JSCodeTranslator();

//		Set<CodeModule> keys=interpretation.keySet();
//		for (CodeModule codeModule : keys) {
//			CodePattern pattern=interpretation.get(codeModule);
//			if(pattern!=null && (pattern instanceof JavaConstructorDeclaration))
//				System.err.println("["+codeModule+"]:"+pattern);
//		}

		String translation=translator.translateCode(fileBlock, interpretation);

		System.err.println("Something to write?");
		System.err.println(translation);
	}
}
