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

public class Test_sv2 implements GLEventListener {
	private int vertexPositionAttribute;
	private int vertexColorAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int[] pyramidVertexPositionBuffer = new int[3];
	private int[] pyramidVertexColorBuffer = new int[3];
	private int[] baseVertexPositionBuffer = new int[3];
	private int[] baseVertexColorBuffer = new int[3];
	private FloatBuffer pMatrix;
	private FloatBuffer mvMatrix;
	private double rAngle = 0;
	private long lastTime = 0;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("test_sv2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv2();
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
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	private void initShaders(GL2 gl) {
		int shaderProgram = Util.getShaderProgram(gl, "shaders/shader1.fs", "shaders/shader1.vs");

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
		gl.glGenBuffers(1, pyramidVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		float[] vertices = { 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices), GL2.GL_STATIC_DRAW);
		pyramidVertexPositionBuffer[1] = 3;
		pyramidVertexPositionBuffer[2] = 6;

		gl.glGenBuffers(1, pyramidVertexColorBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexColorBuffer[0]);
		float[] colors = { 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, colors.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(colors), GL2.GL_STATIC_DRAW);
		pyramidVertexColorBuffer[1] = 4;
		pyramidVertexColorBuffer[2] = 6;

		gl.glGenBuffers(1, baseVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, baseVertexPositionBuffer[0]);
		float[] vertices2 = { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices2), GL2.GL_STATIC_DRAW);
		baseVertexPositionBuffer[1] = 3;
		baseVertexPositionBuffer[2] = 4;

		gl.glGenBuffers(1, baseVertexColorBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, baseVertexColorBuffer[0]);
		float[] colors2 = { 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, colors2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(colors2), GL2.GL_STATIC_DRAW);
		baseVertexColorBuffer[1] = 4;
		baseVertexColorBuffer[2] = 4;

	}

	private void drawScene(GL2 gl) {
		gl.glViewport(0, 0, 500, 500);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		float cx = (float) Math.cos(rAngle);
		float sx = (float) Math.sin(rAngle);

		float[] pMatrixv = new float[] { 2.4142136573791504f, 0, 0, 0, 0, 2.4142136573791504f, 0, 0, 0, 0, -1.0020020008087158f, -1, 0, 0, -0.20020020008087158f, 0 };
		pMatrix = BufferUtil.newFloatBuffer(pMatrixv);

		float[] mvMatrixv = new float[] { cx, sx * sx, -cx * sx, 0, 0, cx, sx, 0, sx, -cx * sx, cx * cx, 0, 0, 0, -8, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		gl.glFrontFace(GL2.GL_CCW);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, pyramidVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexColorBuffer[0]);
		gl.glVertexAttribPointer(vertexColorAttribute, pyramidVertexColorBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_FAN, 0, pyramidVertexPositionBuffer[2]);

		gl.glFrontFace(GL2.GL_CW);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, baseVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, baseVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, baseVertexColorBuffer[0]);
		gl.glVertexAttribPointer(vertexColorAttribute, baseVertexColorBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, baseVertexPositionBuffer[2]);

		animate();
	}

	private void animate() {
		long timeNow = System.currentTimeMillis();
		if (lastTime != 0) {
			long elapsed = timeNow - lastTime;
			rAngle += 1.57 * elapsed * 0.001;
		}
		lastTime = timeNow;
	}
}
