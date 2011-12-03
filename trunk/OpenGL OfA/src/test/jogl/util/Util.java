package test.jogl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.media.opengl.GL2;

public class Util {

	public static String loadFile(String src) {
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
		return text;
	}

	public static int getShader(GL2 gl, String src, int type) {

		String[] text = new String[1];
		text[0] = loadFile(src);

		int[] length = new int[1];
		length[0] = text[0].length();

		int shader = gl.glCreateShader(type);
		gl.glShaderSource(shader, 1, text, length, 0);

		gl.glCompileShader(shader);

		int status[] = new int[1];

		gl.glGetProgramiv(shader, GL2.GL_COMPILE_STATUS, status, 0);

		if (status[0] != 0) {
			System.out.println("Shader has a problem");
			int len[] = new int[1];
			gl.glGetObjectParameterivARB(shader, GL2.GL_COMPILE_STATUS, len, 0);

			byte[] b = new byte[20000];
			gl.glGetInfoLogARB(shader, b.length, status, 0, b, 0);

			System.out.println(new String(b));
		}

		return shader;
	}
}
