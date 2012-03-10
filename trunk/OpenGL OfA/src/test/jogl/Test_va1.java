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

public class Test_va1 implements GLEventListener, KeyListener {
	private int vertexPositionAttribute;
	private int textureCoordAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int samplerUniform;
	private int[] doorVertexPositionBuffer = new int[3];
	private int[] doorVertexTextureCoordBuffer = new int[3];
	private int[] greenVertexPositionBuffer = new int[3];
	private int[] greenVertexTextureCoordBuffer = new int[3];
	private int[] pyramidVertexPositionBuffer = new int[3];
	private int[] pyramidVertexTextureCoordBuffer = new int[3];
	private int[] cubeVertexPositionBuffer = new int[3];
	private int[] cubeVertexIndexBuffer = new int[3];
	private int[] cubeVertexTextureCoordBuffer = new int[3];
	private int[] textures = new int[4];
	private FloatBuffer pMatrix;
	private FloatBuffer mvMatrix;
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
		frame.setTitle("test_va1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_va1();
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

		gl.glClearColor(0.0f, 0.71f, 0.91f, 1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	private void initShaders(GL2 gl) {
		int shaderProgram = Util.getShaderProgram(gl, "shaders/shader2.fs", "shaders/shader2.vs");

		vertexPositionAttribute = gl.glGetAttribLocation(shaderProgram, "aVertexPosition");
		gl.glEnableVertexAttribArray(vertexPositionAttribute);

		textureCoordAttribute = gl.glGetAttribLocation(shaderProgram, "aTextureCoord");
		gl.glEnableVertexAttribArray(textureCoordAttribute);

		pMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uPMatrix");
		mvMatrixUniform = gl.glGetUniformLocation(shaderProgram, "uMVMatrix");
		samplerUniform = gl.glGetUniformLocation(shaderProgram, "uSampler");
	}

	private void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
	}

	private void initBuffers(GL2 gl) {

		gl.glGenBuffers(1, pyramidVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		float[] vertices1 = { 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices1.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices1), GL2.GL_STATIC_DRAW);
		pyramidVertexPositionBuffer[1] = 3;
		pyramidVertexPositionBuffer[2] = 6;

		gl.glGenBuffers(1, pyramidVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		float[] textureCoords1 = { 3.0f, 0.0f, 0.0f, 6.0f, 6.0f, 6.0f, 0.0f, 6.0f, 6.0f, 6.0f, 0.0f, 6.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords1.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords1), GL2.GL_STATIC_DRAW);
		pyramidVertexTextureCoordBuffer[1] = 2;
		pyramidVertexTextureCoordBuffer[2] = 6;

		gl.glGenBuffers(1, cubeVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		float[] vertices2 = { -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices2), GL2.GL_STATIC_DRAW);
		cubeVertexPositionBuffer[1] = 3;
		cubeVertexPositionBuffer[2] = 16;

		gl.glGenBuffers(1, cubeVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		float[] textureCoords2 = { 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords2), GL2.GL_STATIC_DRAW);
		cubeVertexTextureCoordBuffer[1] = 2;
		cubeVertexTextureCoordBuffer[2] = 16;

		gl.glGenBuffers(1, cubeVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		int[] cubeVertexIndices = { 0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15 };
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndices.length * BufferUtil.SIZEOF_INT, BufferUtil.newIntBuffer(cubeVertexIndices), GL2.GL_STATIC_DRAW);
		cubeVertexIndexBuffer[1] = 1;
		cubeVertexIndexBuffer[2] = 24;

		gl.glGenBuffers(1, greenVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		float[] vertices3 = { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices3.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices3), GL2.GL_STATIC_DRAW);
		greenVertexPositionBuffer[1] = 3;
		greenVertexPositionBuffer[2] = 4;

		gl.glGenBuffers(1, greenVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		float[] textureCoords3 = { 0.0f, 10.0f, 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords3.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords3), GL2.GL_STATIC_DRAW);
		greenVertexTextureCoordBuffer[1] = 2;
		greenVertexTextureCoordBuffer[2] = 4;

		gl.glGenBuffers(1, doorVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexPositionBuffer[0]);
		float[] vertices4 = { -0.2f, -1.0f, 1.001f, 0.2f, -1.0f, 1.001f, -0.2f, 0.0f, 1.001f, 0.2f, 0.0f, 1.001f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices4.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices4), GL2.GL_STATIC_DRAW);
		doorVertexPositionBuffer[1] = 3;
		doorVertexPositionBuffer[2] = 4;

		gl.glGenBuffers(1, doorVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		float[] textureCoords4 = { 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords4.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords4), GL2.GL_STATIC_DRAW);
		doorVertexTextureCoordBuffer[1] = 2;
		doorVertexTextureCoordBuffer[2] = 4;

	}

	private void initTexture(GL2 gl) {
		gl.glGenTextures(4, textures, 0);

		TextureData tex1;
		try {
			tex1 = TextureIO.newTextureData(new File("images/muro.gif"), false, null);
		} catch (IOException e) {
			tex1 = null;
		}
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[0]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex1.getWidth(), tex1.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex1.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex2;
		try {
			tex2 = TextureIO.newTextureData(new File("images/roof.gif"), false, null);
		} catch (IOException e) {
			tex2 = null;
		}
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[1]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex2.getWidth(), tex2.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex2.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex3;
		try {
			tex3 = TextureIO.newTextureData(new File("images/grass.gif"), false, null);
		} catch (IOException e) {
			tex3 = null;
		}
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[2]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex3.getWidth(), tex3.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex3.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex4;
		try {
			tex4 = TextureIO.newTextureData(new File("images/door.gif"), false, null);
		} catch (IOException e) {
			tex4 = null;
		}
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[3]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex4.getWidth(), tex4.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex4.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
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

		float[] mvMatrixv = new float[] { cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, -cx, zoom - sx, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		gl.glFrontFace(GL2.GL_CCW);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, cubeVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, cubeVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[0]);
		gl.glUniform1i(samplerUniform, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, cubeVertexIndexBuffer[2], GL2.GL_UNSIGNED_INT, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, doorVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, doorVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[3]);
		gl.glUniform1i(samplerUniform, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, doorVertexPositionBuffer[2]);

		gl.glDisable(GL2.GL_CULL_FACE);

		float[] mvMatrixv2 = mvMatrixv.clone();
		mvMatrixv[0] *= 10;
		mvMatrixv[1] *= 10;
		mvMatrixv[2] *= 10;
		mvMatrixv[8] *= 10;
		mvMatrixv[9] *= 10;
		mvMatrixv[10] *= 10;
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, greenVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, greenVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[2]);
		gl.glUniform1i(samplerUniform, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, greenVertexPositionBuffer[2]);
		mvMatrixv = mvMatrixv2;

		gl.glEnable(GL2.GL_CULL_FACE);

		mvMatrixv[13] += 2 * cx;
		mvMatrixv[14] += 2 * sx;
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, pyramidVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, pyramidVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[1]);
		gl.glUniform1i(samplerUniform, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_FAN, 0, pyramidVertexPositionBuffer[2]);

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
