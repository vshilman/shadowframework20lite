package test.jogl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.FloatBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import test.jogl.util.Util;
import test.jogl.util.Util.ObjModel;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.BufferUtil;

public class Test_sv8 implements GLEventListener, KeyListener {
	private int vertexPositionAttribute;
	private int vertexNormalAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int nMatrixUniform;
	private int ambientColorUniform;
	private int pointLightingLocationUniform;
	private int pointLightingSpecularColorUniform;
	private int pointLightingDiffuseColorUniform;
	private int materialShininessUniform;
	private int[] teapotVertexPositionBuffer = new int[3];
	private int[] teapotVertexNormalBuffer = new int[3];
	private int[] teapotVertexIndexBuffer = new int[3];
	private FloatBuffer pMatrix;
	private FloatBuffer mvMatrix;
	private FloatBuffer normalMatrix;
	private double rAnglex = 0;
	private float speedx = 0;
	private double rAngley = 0;
	private float speedy = 0;
	private float zoom = -8;
	private long lastTime = 0;
	private long lastTime2 = 0;
	private boolean[] commands = new boolean[6];

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("test_sv8");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv8();
		GLCanvas canvas = new GLCanvas();

		canvas.addGLEventListener(listener);

		frame.getContentPane().add(canvas);
		frame.addKeyListener((KeyListener) listener);

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
		int shaderProgram = Util.getShaderProgram(gl, "shaders/shader5.fs", "shaders/shader5.vs");

		vertexPositionAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexPosition");
		gl.glEnableVertexAttribArray(vertexPositionAttribute);

		vertexNormalAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexNormal");
		gl.glEnableVertexAttribArray(vertexNormalAttribute);

		pMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uMVMatrix");
		nMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uNMatrix");
		ambientColorUniform = gl.glGetUniformLocation(shaderProgram, "uAmbientColor");
		pointLightingLocationUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingLocation");
		pointLightingSpecularColorUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingSpecularColor");
		pointLightingDiffuseColorUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingDiffuseColor");
		materialShininessUniform = gl.glGetUniformLocation(shaderProgram, "uMaterialShininess");
	}

	private void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
		gl.glUniformMatrix3fv(nMatrixUniform, 9, false, normalMatrix);

		gl.glUniform3f(ambientColorUniform, 0.1f, 0.1f, 0.1f);

		gl.glUniform3f(pointLightingLocationUniform, -1, 0, -5.1f);

		gl.glUniform3f(pointLightingDiffuseColorUniform, 0.7f, 0.7f, 0.7f);
		gl.glUniform3f(pointLightingSpecularColorUniform, 0.7f, 0.7f, 0.7f);

		gl.glUniform1f(materialShininessUniform, 10);
	}

	private void initBuffers(GL2 gl) {
		ObjModel teapot = Util.loadObj("models/teapot.obj");

		gl.glGenBuffers(1, teapotVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, teapotVertexPositionBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, teapot.vertexPositions.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(teapot.vertexPositions), GL2.GL_STATIC_DRAW);
		teapotVertexPositionBuffer[1] = 3;
		teapotVertexPositionBuffer[2] = teapot.vertexPositions.length / 3;

		gl.glGenBuffers(1, teapotVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, teapotVertexNormalBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, teapot.vertexNormals.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(teapot.vertexNormals), GL2.GL_STATIC_DRAW);
		teapotVertexNormalBuffer[1] = 3;
		teapotVertexNormalBuffer[2] = teapot.vertexNormals.length / 3;

		gl.glGenBuffers(1, teapotVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, teapotVertexIndexBuffer[0]);
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, teapot.indices.length * BufferUtil.SIZEOF_INT, BufferUtil.newIntBuffer(teapot.indices), GL2.GL_STATIC_DRAW);
		teapotVertexIndexBuffer[1] = 1;
		teapotVertexIndexBuffer[2] = teapot.indices.length;

	}

	private void drawScene(GL2 gl) {
		handleKeys();
		gl.glViewport(0, 0, 500, 500);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		float cx = (float) Math.cos(rAnglex);
		float sx = (float) Math.sin(rAnglex);
		float cy = (float) Math.cos(rAngley);
		float sy = (float) Math.sin(rAngley);

		float[] pMatrixv = new float[] { 2.4142136573791504f, 0, 0, 0, 0, 2.4142136573791504f, 0, 0, 0, 0, -1.0020020008087158f, -1, 0, 0, -0.20020020008087158f, 0 };
		pMatrix = BufferUtil.newFloatBuffer(pMatrixv);

		float[] mvMatrixv = new float[] { cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, 0, zoom, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		float[] normalMatrixv = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glFrontFace(GL2.GL_CCW);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, teapotVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, teapotVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, teapotVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, teapotVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, teapotVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, teapotVertexIndexBuffer[2], GL2.GL_UNSIGNED_INT, 0);

		animate();
	}

	private void animate() {
		long timeNow = System.currentTimeMillis();
		if (lastTime != 0) {
			long elapsed = timeNow - lastTime;
			rAnglex += speedx * elapsed * 0.001;
			rAngley += speedy * elapsed * 0.001;
		}
		lastTime = timeNow;
	}

	private void handleKeys() {
		long timeNow = System.currentTimeMillis();
		if (lastTime2 != 0) {
			long elapsed = timeNow - lastTime2;
			if (commands[0]) {
				zoom += 5 * elapsed * 0.001;
			}
			if (commands[1]) {
				zoom -= 5 * elapsed * 0.001;
			}
			if (commands[2]) {
				speedx -= 2 * elapsed * 0.001;
			}
			if (commands[3]) {
				speedx += 2 * elapsed * 0.001;
			}
			if (commands[4]) {
				speedy -= 2 * elapsed * 0.001;
			}
			if (commands[5]) {
				speedy += 2 * elapsed * 0.001;
			}
		}
		lastTime2 = timeNow;
	}

	private void setCommands(KeyEvent e, boolean value) {
		if (e.getKeyCode() == 65) {
			commands[0] = value;
		}
		if (e.getKeyCode() == 90) {
			commands[1] = value;
		}
		if (e.getKeyCode() == 38) {
			commands[2] = value;
		}
		if (e.getKeyCode() == 40) {
			commands[3] = value;
		}
		if (e.getKeyCode() == 37) {
			commands[4] = value;
		}
		if (e.getKeyCode() == 39) {
			commands[5] = value;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setCommands(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		setCommands(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
