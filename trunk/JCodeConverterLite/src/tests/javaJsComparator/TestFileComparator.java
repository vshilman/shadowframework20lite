package tests.javaJsComparator;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockDataInterpreter;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.JavaJsCodePatternComparators;
import codeconverter.js.JsCodePatternInterpreter;
import codeconverter.utility.FileStringUtility;

public class TestFileComparator {

	private static final String JAVA_DIRECTORY = "../OpenGL OfA/src/test/jogl/";
	private static final String JS_DIRECTORY = "../OpenGL OfA/src/test/WebGL/";
	private static StringWriter logWriter = new StringWriter();

	public static void main(String[] args) {
		ArrayList<String> javaTests = new ArrayList<String>();
		ArrayList<String> jsTests = new ArrayList<String>();

		for (int i = 1; i <= 9; i++) {
			javaTests.add(JAVA_DIRECTORY + "Test_sv" + i + "Drawer.java");
			jsTests.add(JS_DIRECTORY + "test_sv" + i + "Drawer.js");
		}

		for (int i = 1; i <= 2; i++) {
			javaTests.add(JAVA_DIRECTORY + "Test_va" + i + "Drawer.java");
			jsTests.add(JS_DIRECTORY + "test_va" + i + "Drawer.js");
		}

		List<CodePatternComparator> comparators = JavaJsCodePatternComparators.getComparators();

		for (int i = 0; i < javaTests.size(); i++) {

			String name = "\n" + javaTests.get(i).substring(JAVA_DIRECTORY.length()) + " VS "
					+ jsTests.get(i).substring(JS_DIRECTORY.length());
			System.out.println(name);
			logWriter.write(name + "\n");

			ArrayList<CodePattern> javaPatterns = new ArrayList<CodePattern>(getCodePatterns(
					javaTests.get(i), new JavaCodePatternInterpreter()));

			ArrayList<CodePattern> jsPatterns = new ArrayList<CodePattern>(getCodePatterns(jsTests.get(i),
					new JsCodePatternInterpreter()));

			boolean[] javaConfirmations = new boolean[javaPatterns.size()];
			Arrays.fill(javaConfirmations, false);
			boolean[] jsConfirmations = new boolean[jsPatterns.size()];
			Arrays.fill(jsConfirmations, false);

			boolean found = false;

			for (int j = 0; j < javaPatterns.size(); j++) {
				if (javaPatterns.get(j).getPatternType().get(0) == PatternType.LIBRARY_DECLARATION) {
					javaConfirmations[j] = true;
				}
				if (!javaConfirmations[j]) {
					found = false;
					for (int k = 0; k < jsPatterns.size(); k++) {
						if (found)
							break;
						if (!jsConfirmations[k]) {
							for (CodePatternComparator codePatternComparator : comparators) {
								int[][] result = codePatternComparator
										.compare(javaPatterns, j, jsPatterns, k);
								if (result != null) {
									for (int l = 0; l < result[0].length; l++) {
										logWriter.write("\t" + javaPatterns.get(result[0][l]).toString());
										javaConfirmations[result[0][l]] = true;
										if (l < result[0].length - 1) {
											logWriter.write(";\t");
										}
									}
									logWriter.write("  --->  ");
									for (int l = 0; l < result[1].length; l++) {
										logWriter.write("\t\t" + jsPatterns.get(result[1][l]).toString());
										jsConfirmations[result[1][l]] = true;
										if (l < result[1].length - 1) {
											logWriter.write(";\t");
										}
									}
									logWriter.write("\n");
									found = true;
									break;
								}
							}
						}
					}
				}
			}

			String sw = "\tunmatched javaPatterns:";
			System.out.println(sw);
			logWriter.write(sw + "\n");
			for (int j = 0; j < javaConfirmations.length; j++) {
				if (!javaConfirmations[j]) {
					sw = "\t\t" + javaPatterns.get(j).toString();
					System.out.println(sw);
					logWriter.write(sw + "\n");
				}
			}

			sw = "\tunmatched jsPatterns:";
			System.out.println(sw);
			logWriter.write(sw + "\n");
			for (int j = 0; j < jsConfirmations.length; j++) {
				if (jsPatterns.get(j).getPatternType().get(0) == PatternType.CONSTRUCTOR_DECLARATION
						&& !jsConfirmations[j]) {
					jsConfirmations[j] = isDefaultConstructor(jsPatterns.get(j));
				}
				if (!jsConfirmations[j]) {
					sw = "\t\t" + jsPatterns.get(j).toString();
					System.out.println(sw);
					logWriter.write(sw + "\n");
				}
			}
		}

		FileStringUtility.writeTextFile("./src/tests/javaJsComparator/ComparatorLog.txt",
				logWriter.toString());

	}

	private static boolean isDefaultConstructor(CodePattern codePattern) {
		return codePattern.getPieceByType(PieceType.METHOD_VARIABLES).getPieces().size() == 0;
	}

	private static Collection<CodePattern> getCodePatterns(String file, BlockDataInterpreter blockInterpreter) {

		List<String> list = FileStringUtility.loadTextFile(file);

		StringWriter writer = new StringWriter();
		for (String string : list) {
			writer.write(string);
		}

		String totalString = writer.toString();
		char[] totalStringChars = totalString.toCharArray();

		Block fileBlock = BlockUtilities.generateBlocks(totalStringChars);

		// System.out.println(fileBlock.print());

		BlockInterpreter interpreter = new BlockInterpreter(blockInterpreter);
		HashMap<CodeModule, CodePattern> interpretation = interpreter.getInterpretation(fileBlock);

		Collection<CodePattern> javaPatterns = interpretation.values();
		return javaPatterns;
	}
}
