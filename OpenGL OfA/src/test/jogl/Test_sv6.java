package test.jogl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import test.jogl.util.Util;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

public class Test_sv6 implements GLEventListener, KeyListener {
	private int vertexPositionAttribute;
	private int vertexNormalAttribute;
	private int textureCoordAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int nMatrixUniform;
	private int samplerUniform;
	private int ambientColorUniform;
	private int lightingDirectionUniform;
	private int directionalColorUniform;
	private int[] cubeVertexPositionBuffer = new int[3];
	private int[] cubeVertexNormalBuffer = new int[3];
	private int[] cubeVertexIndexBuffer = new int[3];
	private int[] cubeVertexTextureCoordBuffer = new int[3];
	private int[] textures = new int[1];
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
		frame.setTitle("test_sv6");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv6();
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
		initTexture(gl);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	private void initShaders(GL2 gl) {
		int shaderProgram = Util.getShaderProgram(gl, "shaders/shader3.fs", "shaders/shader3.vs");

		vertexPositionAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexPosition");
		gl.glEnableVertexAttribArray(vertexPositionAttribute);

		vertexNormalAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexNormal");
		gl.glEnableVertexAttribArray(vertexNormalAttribute);

		textureCoordAttribute = gl.glGetAttribLocation(shaderProgram, "aTextureCoord");
		gl.glEnableVertexAttribArray(textureCoordAttribute);

		pMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uMVMatrix");
		nMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uNMatrix");
		samplerUniform = gl.glGetUniformLocation(shaderProgram, "uSampler");
		ambientColorUniform = gl.glGetUniformLocation(shaderProgram, "uAmbientColor");
		lightingDirectionUniform = gl.glGetUniformLocation(shaderProgram, "uLightingDirection");
		directionalColorUniform = gl.glGetUniformLocation(shaderProgram, "uDirectionalColor");
	}

	private void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
		gl.glUniformMatrix3fv(nMatrixUniform, 9, false, normalMatrix);

		gl.glUniform3f(ambientColorUniform, 0.1f, 0.1f, 0.1f);

		float[] lightingDirectionv = { 0.57735026918962576450914878050196f, 0.57735026918962576450914878050196f, 0.57735026918962576450914878050196f };
		FloatBuffer lightingDirection = BufferUtil.newFloatBuffer(lightingDirectionv);
		gl.glUniform3fv(lightingDirectionUniform, lightingDirectionv.length, lightingDirection);

		gl.glUniform3f(directionalColorUniform, 0.7f, 0.7f, 0.7f);
	}

	private void initBuffers(GL2 gl) {
		gl.glGenBuffers(1, cubeVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		float[] vertices = { -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices), GL2.GL_STATIC_DRAW);
		cubeVertexPositionBuffer[1] = 3;
		cubeVertexPositionBuffer[2] = 24;

		gl.glGenBuffers(1, cubeVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexNormalBuffer[0]);
		float[] normals = { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals), GL2.GL_STATIC_DRAW);
		cubeVertexNormalBuffer[1] = 3;
		cubeVertexNormalBuffer[2] = 24;

		gl.glGenBuffers(1, cubeVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		float[] textureCoords = { -1.0f, -1.0f, 2.0f, -1.0f, 2.0f, 2.0f, -1.0f, 2.0f, 2.0f, -1.0f, 2.0f, 2.0f, -1.0f, 2.0f, -1.0f, -1.0f, -1.0f, 2.0f, -1.0f, -1.0f, 2.0f, -1.0f, 2.0f, 2.0f, 2.0f, 2.0f, -1.0f, 2.0f, -1.0f, -1.0f, 2.0f, -1.0f, 2.0f, -1.0f, 2.0f, 2.0f, -1.0f, 2.0f, -1.0f, -1.0f, -1.0f, -1.0f, 2.0f, -1.0f, 2.0f, 2.0f, -1.0f, 2.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords), GL2.GL_STATIC_DRAW);
		cubeVertexTextureCoordBuffer[1] = 2;
		cubeVertexTextureCoordBuffer[2] = 24;

		gl.glGenBuffers(1, cubeVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		int[] cubeVertexIndices = { 0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15, 16, 17, 18, 16, 18, 19, 20, 21, 22, 20, 22, 23 };
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndices.length * BufferUtil.SIZEOF_INT, BufferUtil.newIntBuffer(cubeVertexIndices), GL2.GL_STATIC_DRAW);
		cubeVertexIndexBuffer[1] = 1;
		cubeVertexIndexBuffer[2] = 36;

	}

	private void initTexture(GL2 gl) {
		gl.glGenTextures(1, textures, 0);

		TextureData tex;
		try {
			tex = TextureIO.newTextureData(new File("images/muro.gif"), false, null);
		} catch (IOException e) {
			tex = null;
		}
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[0]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex.getWidth(), tex.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
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
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, cubeVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, cubeVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, cubeVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[0]);
		gl.glUniform1i(samplerUniform, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, cubeVertexIndexBuffer[2], GL2.GL_UNSIGNED_INT, 0);

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
