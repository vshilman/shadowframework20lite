package tests.javaJsComparator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

import tests.tmp.GeneralTests;
import codeconverter.CodeModule;
import codeconverter.DifferentiationResult;
import codeconverter.utility.FileStringUtility;

public class TestFileComparatorUsingUtils {

	private static final String JAVA_DIRECTORY = "../OpenGL OfA/src/test/jogl/";
	private static final String JS_DIRECTORY = "../OpenGL OfA/src/test/WebGL/";
	private static StringWriter logWriter = new StringWriter();

	public static void main(String[] args) {
		ArrayList<String> javaTests = new ArrayList<String>();
		ArrayList<String> jsTests = new ArrayList<String>();

		for (int i = 2; i <= 2; i++) {
			javaTests.add(JAVA_DIRECTORY + "Test_sv" + i + "Drawer.java");
			jsTests.add(JS_DIRECTORY + "test_sv" + i + "Drawer.js");
		}

	/*	for (int i = 1; i <= 1; i++) {
			javaTests.add(JAVA_DIRECTORY + "Test_va" + i + "Drawer.java");
			jsTests.add(JS_DIRECTORY + "test_va" + i + "Drawer.js");
		}*/

		for (int i = 0; i < javaTests.size(); i++) {

			String name = "\n" + javaTests.get(i).substring(JAVA_DIRECTORY.length()) + " VS "
					+ jsTests.get(i).substring(JS_DIRECTORY.length());

			System.out.println(name);

			String javaFileName = javaTests.get(i);
			String jsFileName = jsTests.get(i);


			logWriter.write(name + "\n");

			DifferentiationResult res=GeneralTests.compareFiles(jsFileName, javaFileName,FileStringUtility.getStream(jsFileName),FileStringUtility.getStream(javaFileName) , logWriter,true);

			String infos="Non interpretate sinistra:\n";

			for (int j = 0; i < res.getUninterpretatesLeft().size(); j++) {
				infos+=res.getUninterpretatesLeft().get(j).getCode()+"\n";
			}

			infos+="Non interpretate destra:\n";

			for (int j = 0; i < res.getUninterpretatesRight().size(); j++) {
				infos+=res.getUninterpretatesRight().get(i).getCode()+"\n";
			}


			infos+="Differenti sinistra:\n";

			if(res.getDifferentLeft()!=null){
				for (Iterator<CodeModule> iterator = res.getDifferentLeft().iterator(); iterator.hasNext();) {
					String s=iterator.next().getCode();
					//String s=iterator.next().print();
					infos+=s+"\n";
				}
			}
			infos+="Differenti destra:\n";

			if(res.getDifferentRight()!=null){
				for (Iterator<CodeModule> iterator = res.getDifferentRight().iterator(); iterator.hasNext();) {
					String s=iterator.next().getCode();
					infos+=s+"\n";

				}
			}
			System.out.println(infos);


		}

		FileStringUtility.writeTextFile("./src/tests/javaJsComparator/ComparatorLogUsingUtils.txt",
				logWriter.toString());

	}



}
