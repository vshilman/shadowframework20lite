package tests.javaJsComparator;

import java.util.ArrayList;
import java.util.HashMap;

import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockDataInterpreter;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.js.JsCodePatternInterpreter;

public class Test001 {

	public static void main(String[] args) {

		NameComparator comparator = new NameComparator();
		String javaLine = "ciao";
		String jsLine = "ciao";

		HashMap<CodeModule, CodePattern> javaInterpretation = getInterpretation(
				javaLine, new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> jsInterpretation = getInterpretation(
				jsLine, new JsCodePatternInterpreter());
		
		ArrayList<CodePattern> javaPatterns=new ArrayList<CodePattern>(javaInterpretation.values());
		ArrayList<CodePattern> jsPatterns=new ArrayList<CodePattern>(jsInterpretation.values());

		
	}

	private static HashMap<CodeModule, CodePattern> getInterpretation(
			String javaLine, BlockDataInterpreter blockInterpreter) {
		char[] totalStringChars = javaLine.toCharArray();

		Block fileBlock = BlockUtilities.generateBlocks(totalStringChars);

		// System.out.println(fileBlock.print());

		BlockInterpreter interpreter = new BlockInterpreter(blockInterpreter);
		return interpreter.getInterpretation(fileBlock);
	}

}
