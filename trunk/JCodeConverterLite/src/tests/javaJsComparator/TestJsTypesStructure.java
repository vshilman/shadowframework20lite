package tests.javaJsComparator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import tests.TestingUtilities;
import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.js.JsCodePatternInterpreter;
import codeconverter.utility.FileStringUtility;

public class TestJsTypesStructure {

	private static final String DIRECTORY = "../OpenGL OfA/src/test/WebGL/";

	public static void main(String[] args) {

		ArrayList<String> tests = new ArrayList<String>();

		for (int i = 1; i <= 9; i++) {
			tests.add(DIRECTORY + "test_sv" + i + "Drawer.js");
		}

		for (int i = 1; i <= 2; i++) {
			tests.add(DIRECTORY + "test_va" + i + "Drawer.js");
		}

		StringWriter logWriter = new StringWriter();
		for (String file : tests) {
			String name = "\n" + file.substring(DIRECTORY.length());
			System.out.println(name);
			logWriter.write(name + "\n");

//			String totalString = TestingUtilities.generateFileString(FileStringUtility.getStream(file)).toString();
//			char[] totalStringChars = totalString.toCharArray();
//
//			Block fileBlock = BlockUtilities.generateBlocks(totalStringChars);
			Block fileBlock=BlockUtilities.generateBlocksFromStream(FileStringUtility.getStream(file));

			// System.out.println(fileBlock.print());

			BlockInterpreter interpreter = new BlockInterpreter(new JsCodePatternInterpreter());
			HashMap<CodeModule, CodePattern> interpretation = interpreter.getInterpretation(fileBlock);

			Set<CodeModule> keys = interpretation.keySet();
			for (CodeModule codeModule : keys) {
				CodePattern pattern = interpretation.get(codeModule);
				String result;
				if (pattern == null) {
					result = "[unidentified] -------> " + codeModule;
					System.out.println(result);
					logWriter.write(result+"\n");
				} else {
					result = "[" + patternType(pattern) + "] -------> " + pattern;
					//System.out.println(result);
					//System.out.println(pattern.printTypes() + "\n");
					logWriter.write(result+"\n");
					logWriter.write(pattern.printTypes()+"\n\n");
				}
			}
		}

		FileStringUtility.writeTextFile("./src/tests/javaJsComparator/JsLog.txt", logWriter.toString());

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
