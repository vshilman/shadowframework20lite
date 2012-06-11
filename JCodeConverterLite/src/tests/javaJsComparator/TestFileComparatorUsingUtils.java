package tests.javaJsComparator;

import java.io.StringWriter;
import java.util.ArrayList;

import tests.tmp.GeneralTests;
import codeconverter.utility.FileStringUtility;

public class TestFileComparatorUsingUtils {

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

		for (int i = 0; i < javaTests.size(); i++) {

			String name = "\n" + javaTests.get(i).substring(JAVA_DIRECTORY.length()) + " VS "
					+ jsTests.get(i).substring(JS_DIRECTORY.length());
			
			System.out.println(name);
			
			String javaFileName = javaTests.get(i);
			String jsFileName = jsTests.get(i);
			
			GeneralTests.compareFiles(jsFileName, javaFileName, logWriter);
			
			logWriter.write(name + "\n");

		}

		FileStringUtility.writeTextFile("./src/tests/javaJsComparator/ComparatorLogUsingUtils.txt",
				logWriter.toString());

	}
}
