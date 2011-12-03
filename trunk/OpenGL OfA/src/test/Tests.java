package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;

import OfA.Converter;
import OfA.TestCoverter;

public class Tests extends TestCase {

	private static final String WEB_GL_PATH = "src/test/WebGL/";
	private static final String JOGL_PATH = "src/test/jogl/";
	private Converter converter;

	@Before
	public void setUp() throws Exception {
		converter = new TestCoverter();
	}

	private String loadFile(String src) {
		String text = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(src)));

			String line = reader.readLine();
			while (line != null) {
				text += line + "\n";
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return format(text);
	}

	public void test_sv1() {
		String joglFile = loadFile(JOGL_PATH + "Test_sv1.java");
		String webglFile = loadFile(WEB_GL_PATH + "test_sv1.js");
		assertEquals(joglFile, converter.convert(webglFile));
	}

	private String format(String str) {
		str = str.replaceAll("@\\w*\n", "");
		str = str.replaceAll("\t", "");
		str = str.replaceAll("\n\n", "\n");
		return str;
	}

}
