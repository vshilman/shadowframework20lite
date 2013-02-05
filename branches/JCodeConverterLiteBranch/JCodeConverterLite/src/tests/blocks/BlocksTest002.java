package tests.blocks;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.utility.FileStringUtility;

public class BlocksTest002 {

	public static void main(String[] args) {
		//List<String> list=FileStringUtility
		//		.loadTextFile("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java");
		 List<String> list=FileStringUtility.loadTextFile("src/testPackage/House.java");

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
		
		Set<CodeModule> keys=interpretation.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern=interpretation.get(codeModule);
			if(pattern!=null)
				System.err.println("["+codeModule+"]:"+pattern);
			else
				System.err.println("["+codeModule+"]: unidentified");
		}
	}
}