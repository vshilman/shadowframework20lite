package test.jogl;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import test.jogl.util.Util;

import com.sun.opengl.util.BufferUtil;

public class Test_sv1Drawer {
	private int vertexPositionAttribute;
	private int vertexColorAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int[] triangleVertexPositionBuffer = new int[3];
	private int[] triangleVertexColorBuffer = new int[3];
	private int[] squareVertexPositionBuffer = new int[3];
	private int[] squareVertexColorBuffer = new int[3];
	private FloatBuffer pMatrix;
	private FloatBuffer mvMatrix;

	public void initShaders(GL2 gl) {
		int shaderProgram = Util.getShaderProgram(gl, "shaders/shader1.fs", "shaders/shader1.vs");

		vertexPositionAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexPosition");
		gl.glEnableVertexAttribArray(vertexPositionAttribute);

		vertexColorAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexColor");
		gl.glEnableVertexAttribArray(vertexColorAttribute);

		pMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uMVMatrix");
	}

	public void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
	}

	public void initBuffers(GL2 gl) {
		gl.glGenBuffers(1, triangleVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, triangleVertexPositionBuffer[0]);
		float[] vertices = { 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices), GL2.GL_STATIC_DRAW);
		triangleVertexPositionBuffer[1] = 3;
		triangleVertexPositionBuffer[2] = 3;

		gl.glGenBuffers(1, triangleVertexColorBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, triangleVertexColorBuffer[0]);
		float[] colors = { 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, colors.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(colors), GL2.GL_STATIC_DRAW);
		triangleVertexColorBuffer[1] = 4;
		triangleVertexColorBuffer[2] = 3;

		gl.glGenBuffers(1, squareVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, squareVertexPositionBuffer[0]);
		float[] vertices2 = { 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices2), GL2.GL_STATIC_DRAW);
		squareVertexPositionBuffer[1] = 3;
		squareVertexPositionBuffer[2] = 4;

		gl.glGenBuffers(1, squareVertexColorBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, squareVertexColorBuffer[0]);
		float[] colors2 = new float[16];
		for (int i = 0; i < 4; i++) {
			int c = i << 2;
			colors2[c] = 1;
			colors2[c + 1] = 0.75f;
			colors2[c + 2] = 0.8f;
			colors2[c + 3] = 1;
		}
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, colors2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(colors2), GL2.GL_STATIC_DRAW);
		squareVertexColorBuffer[1] = 4;
		squareVertexColorBuffer[2] = 4;

	}

	public void drawScene(GL2 gl) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);

		gl.glViewport(0, 0, 500, 500);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		float[] pMatrixv = new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4 };
		pMatrix = BufferUtil.newFloatBuffer(pMatrixv);

		float[] mvMatrixv = new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, -1.5f, 0, 0, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, triangleVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, triangleVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, triangleVertexColorBuffer[0]);
		gl.glVertexAttribPointer(vertexColorAttribute, triangleVertexColorBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLES, 0, triangleVertexPositionBuffer[2]);

		mvMatrixv = new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1.5f, 0, 0, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, squareVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, squareVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, squareVertexColorBuffer[0]);
		gl.glVertexAttribPointer(vertexColorAttribute, squareVertexColorBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, squareVertexPositionBuffer[2]);

	}
}
