package tests.blocks;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import codeconverter.CodePattern;
import codeconverter.FileStringUtility;
import codeconverter.java.JavaConstructorDeclaration;

public class BlocksTest006 {

	public static void main(String[] args) {
		//List<String> list=FileStringUtility
		//		.loadTextFile("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java");
		
		String filename="../ShadowFramework2.0_OpenGL20/src/shadow/pipeline/openGL20/expressions/SFGL20Divide.java";
		File file=new File(filename);
		System.err.println(file.exists());
		List<String> list=FileStringUtility.loadTextFile(filename);
		//List<String> list=FileStringUtility.loadTextFile("src/testPackage/Expressions.java");
		//List<String> list=FileStringUtility.loadTextFile("src/testPackage/House.java");

		StringWriter writer=new StringWriter();
		for (String string : list) {
			writer.write(string);
		}

		String totalString=writer.toString();
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
		
		System.out.println(translation);
	}
}
