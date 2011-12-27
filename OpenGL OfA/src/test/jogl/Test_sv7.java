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

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.BufferUtil;

public class Test_sv7 implements GLEventListener, KeyListener {
	private int vertexPositionAttribute;
	private int vertexNormalAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int nMatrixUniform;
	private int ambientColorUniform;
	private int pointLightingLocationUniform;
	private int pointLightingColorUniform;
	private int shaderUniform;
	private int shaderChoice = 0;
	private int[] sphereVertexPositionBuffer = new int[3];
	private int[] sphereVertexNormalBuffer = new int[3];
	private int[] sphereVertexIndexBuffer = new int[3];
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
		frame.setTitle("test_sv7");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv7();
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
		int fragmentShader = Util.getShader(gl, "shaders/shader4.fs", GL2.GL_FRAGMENT_SHADER);
		int vertexShader = Util.getShader(gl, "shaders/shader4.vs", GL2.GL_VERTEX_SHADER);

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

		vertexNormalAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexNormal");
		gl.glEnableVertexAttribArray(vertexNormalAttribute);

		pMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uMVMatrix");
		nMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uNMatrix");
		ambientColorUniform = gl.glGetUniformLocation(shaderProgram, "uAmbientColor");
		pointLightingLocationUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingLocation");
		pointLightingColorUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingColor");
		shaderUniform = gl.glGetUniformLocation(shaderProgram, "uShader");
	}

	private void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
		gl.glUniformMatrix3fv(nMatrixUniform, 9, false, normalMatrix);

		gl.glUniform3f(ambientColorUniform, 0.1f, 0.1f, 0.1f);

		gl.glUniform3f(pointLightingLocationUniform, 0, 0, -5.1f);

		gl.glUniform3f(pointLightingColorUniform, 0.7f, 0.7f, 0.7f);
	}

	private void initBuffers(GL2 gl) {
		int latitudeBands = 15;
		int longitudeBands = 15;
		int radius = 2;

		float[] vertices = new float[3 * (latitudeBands + 1) * (longitudeBands + 1)];
		float[] normals = new float[3 * (latitudeBands + 1) * (longitudeBands + 1)];
		int[] sphereVertexIndices = new int[6 * latitudeBands * longitudeBands];

		for (int i = 0; i <= latitudeBands; i++) {
			float theta = (float) (i * Math.PI / latitudeBands);
			float sinTheta = (float) Math.sin(theta);
			float cosTheta = (float) Math.cos(theta);
			for (int j = 0; j <= longitudeBands; j++) {
				float phi = (float) (j * 2 * Math.PI / longitudeBands);
				float sinPhi = (float) Math.sin(phi);
				float cosPhi = (float) Math.cos(phi);

				float x = cosPhi * sinTheta;
				float y = cosTheta;
				float z = sinPhi * sinTheta;

				int index = 3 * (j + i * (longitudeBands + 1));
				vertices[index] = radius * x;
				vertices[index + 1] = radius * y;
				vertices[index + 2] = radius * z;
				normals[index] = x;
				normals[index + 1] = y;
				normals[index + 2] = z;
			}
		}

		for (int i = 0; i < latitudeBands; i++) {
			for (int j = 0; j < longitudeBands; j++) {
				int first = (i * (longitudeBands + 1)) + j;
				int second = first + longitudeBands + 1;

				int index = 6 * (j + i * longitudeBands);

				sphereVertexIndices[index] = first;
				sphereVertexIndices[index + 1] = second;
				sphereVertexIndices[index + 2] = first + 1;
				sphereVertexIndices[index + 3] = second;
				sphereVertexIndices[index + 4] = second + 1;
				sphereVertexIndices[index + 5] = first + 1;
			}
		}

		gl.glGenBuffers(1, sphereVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexPositionBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices), GL2.GL_STATIC_DRAW);
		sphereVertexPositionBuffer[1] = 3;
		sphereVertexPositionBuffer[2] = vertices.length / 3;

		gl.glGenBuffers(1, sphereVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexNormalBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals), GL2.GL_STATIC_DRAW);
		sphereVertexNormalBuffer[1] = 3;
		sphereVertexNormalBuffer[2] = normals.length / 3;

		gl.glGenBuffers(1, sphereVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, sphereVertexIndices.length * BufferUtil.SIZEOF_INT, BufferUtil.newIntBuffer(sphereVertexIndices), GL2.GL_STATIC_DRAW);
		sphereVertexIndexBuffer[1] = 1;
		sphereVertexIndexBuffer[2] = sphereVertexIndices.length;

	}

	private void drawScene(GL2 gl) {
		handleKeys();
		gl.glViewport(0, 0, 500, 500);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glUniform1i(shaderUniform, shaderChoice);

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

		gl.glFrontFace(GL2.GL_CW);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, sphereVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, sphereVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, sphereVertexIndexBuffer[2], GL2.GL_UNSIGNED_INT, 0);

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
		if (e.getKeyCode() == 83) {
			shaderChoice = shaderChoice == 1 ? 0 : (shaderChoice + 1);
		}
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
