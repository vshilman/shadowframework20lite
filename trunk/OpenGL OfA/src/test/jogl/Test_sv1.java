package test.jogl;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import test.jogl.util.Util;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.BufferUtil;

public class Test_sv1 implements GLEventListener {
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

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("test_sv1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv1();
		GLCanvas canvas = new GLCanvas();

		canvas.addGLEventListener(listener);
		frame.getContentPane().add(canvas);
		Animator animator = new Animator(canvas);
		animator.start();

		frame.setVisible(true);
	}

	@Override
	public void display(GLAutoDrawable arg0) {
		GL2 gl = arg0.getGL().getGL2();

		drawScene(gl);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void init(GLAutoDrawable arg0) {
		GL2 gl = arg0.getGL().getGL2();
		initShaders(gl);
		initBuffers(gl);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	private void initShaders(GL2 gl) {
		int fragmentShader = Util.getShader(gl, "shaders/shader1.fs", GL2.GL_FRAGMENT_SHADER);
		int vertexShader = Util.getShader(gl, "shaders/shader1.vs", GL2.GL_VERTEX_SHADER);

		int shaderProgram = gl.glCreateProgram();
		gl.glAttachShader(shaderProgram, vertexShader);
		gl.glAttachShader(shaderProgram, fragmentShader);
		gl.glLinkProgram(shaderProgram);

		int[] status = new int[1];
		gl.glGetProgramiv(shaderProgram, GL2.GL_LINK_STATUS, status, 0);
		if (status[0] == 0) {
			System.out.println("Could not initialise shaders");
		}

		gl.glUseProgram(shaderProgram);

		vertexPositionAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexPosition");
		gl.glEnableVertexAttribArray(vertexPositionAttribute);

		vertexColorAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexColor");
		gl.glEnableVertexAttribArray(vertexColorAttribute);

		pMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uMVMatrix");
	}

	private void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
	}

	private void initBuffers(GL2 gl) {
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

	private void drawScene(GL2 gl) {
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
