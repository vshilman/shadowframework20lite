package test.jogl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.opengl.GL2;

public class Util {

	private static final int BUFFER = 1048576;

	public static String loadFile(String src) {
		String text = "";
		try {
			FileInputStream reader = new FileInputStream(new File(src));
			byte[] b = new byte[BUFFER];
			int c = reader.read(b);
			while (c != -1) {
				if (c < BUFFER) {
					byte[] d = b.clone();
					b = new byte[c];
					for (int j = 0; j < b.length; j++) {
						b[j] = d[j];
					}
				}
				text += new String(b);
				c = reader.read(b);
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

		int shader = gl.glCreateShaderObjectARB(type);

		if (shader == 0) {
			System.out.println("Failed Create " + (GL2.GL_FRAGMENT_SHADER == type ? "fragment" : "vertex")
					+ " shader");
			System.exit(0);
		}

		gl.glShaderSourceARB(shader, 1, text, length, 0);

		gl.glCompileShaderARB(shader);

		int status[] = new int[1];

		gl.glGetObjectParameterivARB(shader, GL2.GL_OBJECT_COMPILE_STATUS_ARB, status, 0);

		if (status[0] == 0) {
			System.out.println("Shader has a problem");
			int len[] = new int[1];
			gl.glGetObjectParameterivARB(shader, GL2.GL_OBJECT_COMPILE_STATUS_ARB, len, 0);

			byte[] b = new byte[3000];
			gl.glGetInfoLogARB(shader, b.length, status, 0, b, 0);

			System.out.println(new String(b));
		}

		return shader;
	}

	public static int getShaderProgram(GL2 gl, String fragmentShaderFile, String vertexShaderFile) {
		int fragmentShader = getShader(gl, fragmentShaderFile, GL2.GL_FRAGMENT_SHADER);
		int vertexShader = getShader(gl, vertexShaderFile, GL2.GL_VERTEX_SHADER);

		int shaderProgram = gl.glCreateProgramObjectARB();

		if (shaderProgram == 0) {
			System.out.println("Failed Create program");
			System.exit(0);
		}

		gl.glAttachObjectARB(shaderProgram, vertexShader);
		gl.glAttachObjectARB(shaderProgram, fragmentShader);
		gl.glLinkProgramARB(shaderProgram);

		int[] status = new int[1];
		gl.glGetObjectParameterivARB(shaderProgram, GL2.GL_OBJECT_LINK_STATUS_ARB, status, 0);

		if (status[0] == 0) {
			System.out.println("Something failed " + status[0]);
			int len[] = new int[1];
			gl.glGetObjectParameterivARB(vertexShader, GL2.GL_OBJECT_COMPILE_STATUS_ARB, len, 0);
			byte[] b = new byte[2000];
			gl.glGetInfoLogARB(vertexShader, b.length, status, 0, b, 0);
		}

		gl.glValidateProgramARB(shaderProgram);
		gl.glUseProgramObjectARB(shaderProgram);
		return shaderProgram;
	}

	public static ObjModel loadObj(String src) {

		String text = loadFile(src);

		ArrayList<Float> vertexArray = new ArrayList<Float>();
		ArrayList<Float> normalArray = new ArrayList<Float>();
		ArrayList<Float> textureArray = new ArrayList<Float>();
		ArrayList<Short> indexArray = new ArrayList<Short>();

		ArrayList<Float> vertex = new ArrayList<Float>();
		ArrayList<Float> normal = new ArrayList<Float>();
		ArrayList<Float> texture = new ArrayList<Float>();
		HashMap<String, Short> facemap = new HashMap<String, Short>();
		short index = 0;

		String[] lines = text.split("\n");
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
			String line = lines[lineIndex].replaceAll("/[ \t]+/g", " ").replaceAll("/\\s\\s*$/", "");

			if (line.equals(""))
				continue;

			if (line.charAt(0) == '#')
				continue;

			line = line.replaceAll("  ", " ");
			String[] array = line.split(" ");
			if (array[0].equals("v")) {
				vertex.add(Float.parseFloat(array[1]));
				vertex.add(Float.parseFloat(array[2]));
				vertex.add(Float.parseFloat(array[3]));
			} else if (array[0].equals("vt")) {
				texture.add(Float.parseFloat(array[1]));
				texture.add(Float.parseFloat(array[2]));
			} else if (array[0].equals("vn")) {
				normal.add(Float.parseFloat(array[1]));
				normal.add(Float.parseFloat(array[2]));
				normal.add(Float.parseFloat(array[3]));
			} else if (array[0].equals("f")) {
				if (array.length != 4) {
					continue;
				}

				for (int i = 1; i < 4; ++i) {
					if (!(facemap.containsKey(array[i]))) {
						String[] f = array[i].split("/");
						int vtx, nor, tex;

						if (f.length == 1) {
							vtx = Integer.parseInt(f[0]) - 1;
							nor = vtx;
							tex = vtx;
						} else if (f.length == 3) {
							vtx = Integer.parseInt(f[0]) - 1;
							tex = Integer.parseInt(f[1]) - 1;
							nor = Integer.parseInt(f[2]) - 1;
						} else {
							return null;
						}

						float x = 0;
						float y = 0;
						float z = 0;
						if (vtx * 3 + 2 < vertex.size()) {
							x = vertex.get(vtx * 3);
							y = vertex.get(vtx * 3 + 1);
							z = vertex.get(vtx * 3 + 2);
						}
						vertexArray.add(x);
						vertexArray.add(y);
						vertexArray.add(z);

						x = 0;
						y = 0;
						if (tex * 2 + 1 < texture.size()) {
							x = texture.get(tex * 2);
							y = texture.get(tex * 2 + 1);
						}
						textureArray.add(x);
						textureArray.add(y);

						x = 0;
						y = 0;
						z = 1;
						if (nor * 3 + 2 < normal.size()) {
							x = normal.get(nor * 3);
							y = normal.get(nor * 3 + 1);
							z = normal.get(nor * 3 + 2);
						}
						normalArray.add(x);
						normalArray.add(y);
						normalArray.add(z);
						facemap.put(array[i], index++);
					}
					indexArray.add(facemap.get(array[i]));
				}
			}
		}
		ObjModel result = new ObjModel();
		result.vertexPositions = floatConvert(vertexArray);
		result.vertexNormals = floatConvert(normalArray);
		result.vertexTextureCoords = floatConvert(textureArray);
		result.indices = shortConvert(indexArray);

		return result;
	}

	private static float[] floatConvert(ArrayList<Float> arrayList) {
		Float[] array = new Float[arrayList.size()];
		array = arrayList.toArray(array);
		float[] result = new float[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	private static short[] shortConvert(ArrayList<Short> arrayList) {
		Short[] array = new Short[arrayList.size()];
		array = arrayList.toArray(array);
		short[] result = new short[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = array[i];
		}
		return result;
	}

	public static class ObjModel {
		public float[] vertexPositions;
		public float[] vertexNormals;
		public float[] vertexTextureCoords;
		public short[] indices;
	}

	public static float[] multiplyMatrix(float[] a, float[] b) {
		float[] c = new float[16];
		c[0] = a[0] * b[0] + a[1] * b[4] + a[2] * b[8] + a[3] * b[12];
		c[4] = a[4] * b[0] + a[5] * b[4] + a[6] * b[8] + a[7] * b[12];
		c[8] = a[8] * b[0] + a[9] * b[4] + a[10] * b[8] + a[11] * b[12];
		c[12] = a[12] * b[0] + a[13] * b[4] + a[14] * b[8] + a[15] * b[12];
		c[1] = a[0] * b[1] + a[1] * b[5] + a[2] * b[9] + a[3] * b[13];
		c[5] = a[4] * b[1] + a[5] * b[5] + a[6] * b[9] + a[7] * b[13];
		c[9] = a[8] * b[1] + a[9] * b[5] + a[10] * b[9] + a[11] * b[13];
		c[13] = a[12] * b[1] + a[13] * b[5] + a[14] * b[9] + a[15] * b[13];
		c[2] = a[0] * b[2] + a[1] * b[6] + a[2] * b[10] + a[3] * b[14];
		c[6] = a[4] * b[2] + a[5] * b[6] + a[6] * b[10] + a[7] * b[14];
		c[10] = a[8] * b[2] + a[9] * b[6] + a[10] * b[10] + a[11] * b[14];
		c[14] = a[12] * b[2] + a[13] * b[6] + a[14] * b[10] + a[15] * b[14];
		c[3] = a[0] * b[3] + a[1] * b[7] + a[2] * b[11] + a[3] * b[15];
		c[7] = a[4] * b[3] + a[5] * b[7] + a[6] * b[11] + a[7] * b[15];
		c[11] = a[8] * b[3] + a[9] * b[7] + a[10] * b[11] + a[11] * b[15];
		c[15] = a[12] * b[3] + a[13] * b[7] + a[14] * b[11] + a[15] * b[15];
		return c;
	}

	public static float[] multiplyMatrixVector(float[] a, float[] b) {
		float[] c = new float[4];
		c[0] = a[0] * b[0] + a[1] * b[1] + a[2] * b[2] + a[3] * b[3];
		c[1] = a[4] * b[0] + a[5] * b[1] + a[6] * b[2] + a[7] * b[3];
		c[2] = a[8] * b[0] + a[9] * b[1] + a[10] * b[2] + a[11] * b[3];
		c[3] = a[12] * b[0] + a[13] * b[1] + a[14] * b[2] + a[15] * b[3];
		return c;
	}

	public static float[] mat4ToInverseMat3(float[] a) {
		float c = a[0], d = a[1], e = a[2], g = a[4], f = a[5], h = a[6], i = a[8], j = a[9], k = a[10], l = k
				* f - h * j, o = -k * g + h * i, m = j * g - f * i, n = c * l + d * o + e * m;
		if (n == 0)
			return null;
		n = 1 / n;
		float[] b = new float[9];
		b[0] = l * n;
		b[1] = (-k * d + e * j) * n;
		b[2] = (h * d - e * f) * n;
		b[3] = o * n;
		b[4] = (k * c - e * i) * n;
		b[5] = (-h * c + e * g) * n;
		b[6] = m * n;
		b[7] = (-j * c + d * i) * n;
		b[8] = (f * c - d * g) * n;
		return b;
	}

	public static float[] mat3Traspose(float[] a) {
		float[] b = new float[9];
		b[0] = a[0];
		b[1] = a[3];
		b[2] = a[6];
		b[3] = a[1];
		b[4] = a[4];
		b[5] = a[7];
		b[6] = a[2];
		b[7] = a[5];
		b[8] = a[8];
		return b;
	}
}
