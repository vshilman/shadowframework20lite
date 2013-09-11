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
import codeconverter.comparator.CodePatternComparator;
import codeconverter.java.JavaCodePatternInterpreter;
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

		JavaJsCodePatternComparators jjc=new JavaJsCodePatternComparators();
		List<CodePatternComparator> comparators = jjc.getComparators();

		for (int i = 0; i < javaTests.size(); i++) {

			String name = "\n" + javaTests.get(i).substring(JAVA_DIRECTORY.length()) + " VS "
					+ jsTests.get(i).substring(JS_DIRECTORY.length());
			System.out.print(name);
			logWriter.write(name + "\n");

			ArrayList<CodePattern> javaPatterns = new ArrayList<CodePattern>(getCodePatterns(javaTests.get(i)
					, new JavaCodePatternInterpreter()));

			ArrayList<CodePattern> jsPatterns = new ArrayList<CodePattern>(getCodePatterns(jsTests.get(i),
					new JsCodePatternInterpreter()));

			boolean[] javaConfirmations = new boolean[javaPatterns.size()];
			Arrays.fill(javaConfirmations, false);
			boolean[] jsConfirmations = new boolean[jsPatterns.size()];
			Arrays.fill(jsConfirmations, false);

			boolean found = false;

			for (int j = 0; j < javaPatterns.size(); j++) {
				if (javaPatterns.get(j) == null) {
					javaConfirmations[j] = false;
				} else {

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
									boolean result = codePatternComparator.compare(javaPatterns.get(j),
											jsPatterns.get(k));
									if (result ) {
										if (!alreadyConfirmed(result, j,k, javaConfirmations, jsConfirmations)) {
											//for (int l = 0; l < result[0].length; l++) {
												logWriter.write("\t"
														+ javaPatterns.get(j).toString() + "\n");
												javaConfirmations[j] = true;
											//}
											//for (int l = 0; l < result[1].length; l++) {
												logWriter.write("\t\t"
														+ jsPatterns.get(k).toString() + "\n");
												jsConfirmations[k] = true;
											//}
											logWriter.write("\n");
											found = true;
											break;
										}
									}
								}
							}
						}
					}
				}
			}

			StringWriter javaUnmatchWriter = new StringWriter();
			StringWriter jsUnmatchWriter = new StringWriter();

			for (int j = 0; j < javaConfirmations.length; j++) {
				if (!javaConfirmations[j] && javaPatterns.get(j) != null) {
					javaUnmatchWriter.write("\t\t" + javaPatterns.get(j).toString() + "\n");
				}
			}

			for (int j = 0; j < jsConfirmations.length; j++) {
				if (jsPatterns.get(j).getPatternType().get(0) == PatternType.CONSTRUCTOR_DECLARATION
						&& !jsConfirmations[j]) {
					jsConfirmations[j] = isDefaultConstructor(jsPatterns.get(j));
				}
				if (!jsConfirmations[j]) {
					jsUnmatchWriter.write("\t\t" + jsPatterns.get(j).toString() + "\n");
				}
			}

			String sw = "\tunmatched javaPatterns:\n";
			if (javaUnmatchWriter.toString().length() != 0 || jsUnmatchWriter.toString().length() != 0) {
				System.out.println("\n" + sw + javaUnmatchWriter);
			} else {
				System.out.println(": test is OK!!");
			}
			logWriter.write(sw + javaUnmatchWriter + "\n");

			sw = "\tunmatched jsPatterns:\n";
			if (javaUnmatchWriter.toString().length() != 0 || jsUnmatchWriter.toString().length() != 0) {
				System.out.println(sw + jsUnmatchWriter);
			}
			logWriter.write(sw + jsUnmatchWriter + "\n");
		}

		FileStringUtility.writeTextFile("./src/tests/javaJsComparator/ComparatorLog.txt",
				logWriter.toString());

	}


	private static boolean alreadyConfirmed(boolean result, int j, int k,boolean[] javaConfirmations,
			boolean[] jsConfirmations) {
		if (javaConfirmations[j])
				return true;
		if (jsConfirmations[k])
				return true;
		return false;
	}

//	private static boolean alreadyConfirmed(int[][] result, boolean[] javaConfirmations,
//			boolean[] jsConfirmations) {
//		for (int l = 0; l < result[0].length; l++) {
//			if (javaConfirmations[result[0][l]])
//				return true;
//		}
//		for (int l = 0; l < result[1].length; l++) {
//			if (jsConfirmations[result[1][l]])
//				return true;
//		}
//		return false;
//	}

	private static boolean isDefaultConstructor(CodePattern codePattern) {
		return codePattern.getPieceByType(PieceType.METHOD_VARIABLES).getPieces().size() == 0;
	}

	private static Collection<CodePattern> getCodePatterns(String filename, BlockDataInterpreter blockInterpreter) {

//		char[] totalStringChars = TestingUtilities.generateFileString(stream).toCharArray();
//
//		Block fileBlock = BlockUtilities.generateBlocks(totalStringChars);
		Block fileBlock=BlockUtilities.generateBlocksFromStream(FileStringUtility.getStream(filename));

		// System.out.println(fileBlock.print());

		BlockInterpreter interpreter = new BlockInterpreter(blockInterpreter);
		HashMap<CodeModule, CodePattern> interpretation = interpreter.getInterpretation(fileBlock);

		Collection<CodePattern> javaPatterns = interpretation.values();
		return javaPatterns;
	}

}
