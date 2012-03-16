package tests.jogl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.utility.FileStringUtility;

public class TestFileInterpretation {

	public static void main(String[] args) {
		List<String> list = FileStringUtility.loadTextFile("../OpenGL OfA/src/test/jogl/Test_sv1Drawer.java");
//		List<String> list=new LinkedList<String>();
//		list.add("ciao{colors2[c + 1] = 0.75f;}");

		StringWriter writer = new StringWriter();
		for (String string : list) {
			writer.write(string);
		}

		String totalString = writer.toString();
		char[] totalStringChars = totalString.toCharArray();

		Block fileBlock = BlockUtilities.generateBlocks(totalStringChars);

		System.out.println(fileBlock.print());

		BlockInterpreter interpreter = new BlockInterpreter(new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation = interpreter.getInterpretation(fileBlock);

		Set<CodeModule> keys = interpretation.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern = interpretation.get(codeModule);
			if (pattern != null)
				System.err.println("[" + patternType(pattern) + "] -------> " + pattern);
			else
				System.err.println("[unidentified] -------> "+codeModule);
		}
	}

	private static String patternType(CodePattern pattern) {
		List<PatternType> types = pattern.getPatternType();
		String s = "";
		for (PatternType patternType : types) {
			s += patternType + ", ";
		}
		int d = s.lastIndexOf(", ");
		if (d != -1) {
			s = s.substring(0, d);
		}
		return !s.equals("") ? s : "noType";
	}
}
