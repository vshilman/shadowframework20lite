package test.jogl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

public class Test_sv9 implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private int vertexPositionAttribute;
	private int vertexNormalAttribute;
	private int textureCoordAttribute;
	private int pMatrixUniform;
	private int mvMatrixUniform;
	private int nMatrixUniform;
	private int samplerUniform;
	private int ambientColorUniform;
	private int pointLightingLocationUniform;
	private int pointLightingSpecularColorUniform;
	private int pointLightingDiffuseColorUniform;
	private int materialShininessUniform;
	private int alphaUniform;
	private int[] greenVertexPositionBuffer = new int[3];
	private int[] greenVertexTextureCoordBuffer = new int[3];
	private int[] greenVertexNormalBuffer = new int[3];
	private int[] sphereVertexPositionBuffer = new int[3];
	private int[] sphereVertexNormalBuffer = new int[3];
	private int[] sphereVertexIndexBuffer = new int[3];
	private int[] sphereVertexTextureCoordBuffer = new int[3];
	private int[] textures = new int[2];
	private FloatBuffer pMatrix;
	private FloatBuffer mvMatrix;
	private FloatBuffer normalMatrix;
	private float zoom = -8;
	private long lastTime2 = 0;
	private boolean[] commands = new boolean[6];
	private int lastMouseX = -1;
	private int lastMouseY = -1;
	private float[] rotationMatrix = new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("test_sv9");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GLEventListener listener = new Test_sv9();
		GLCanvas canvas = new GLCanvas();

		canvas.addGLEventListener(listener);

		frame.getContentPane().add(canvas);
		canvas.setFocusable(true);
		canvas.addKeyListener((KeyListener) listener);
		canvas.addMouseListener((MouseListener) listener);
		canvas.addMouseMotionListener((MouseMotionListener) listener);

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
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE);
		gl.glEnable(GL2.GL_BLEND);
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	private void initShaders(GL2 gl) {
		int shaderProgram = Util.getShaderProgram(gl, "shaders/shader6.fs", "shaders/shader6.vs");

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
		pointLightingLocationUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingLocation");
		pointLightingSpecularColorUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingSpecularColor");
		pointLightingDiffuseColorUniform = gl.glGetUniformLocation(shaderProgram, "uPointLightingDiffuseColor");
		materialShininessUniform = gl.glGetUniformLocation(shaderProgram, "uMaterialShininess");
		alphaUniform = gl.glGetUniformLocation(shaderProgram, "uAlpha");
	}

	private void setMatrixUniforms(GL2 gl) {
		gl.glUniformMatrix4fv(pMatrixUniform, 16, false, pMatrix);
		gl.glUniformMatrix4fv(mvMatrixUniform, 16, false, mvMatrix);
		gl.glUniformMatrix3fv(nMatrixUniform, 9, false, normalMatrix);

		gl.glUniform3f(ambientColorUniform, 0.1f, 0.1f, 0.1f);

		gl.glUniform3f(pointLightingLocationUniform, -10, 0, 5f);

		gl.glUniform3f(pointLightingDiffuseColorUniform, 0.4f, 0.4f, 0.4f);
		gl.glUniform3f(pointLightingSpecularColorUniform, 0.5f, 0.5f, 0.5f);

		gl.glUniform1f(materialShininessUniform, 50);
	}

	private void initBuffers(GL2 gl) {
		int latitudeBands = 30;
		int longitudeBands = 30;
		int radius = 2;

		float[] vertices = new float[3 * (latitudeBands + 1) * (longitudeBands + 1)];
		float[] normals = new float[3 * (latitudeBands + 1) * (longitudeBands + 1)];
		float[] textureCoords = new float[2 * (latitudeBands + 1) * (longitudeBands + 1)];
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
				float u = 1 - ((float) j / longitudeBands);
				float v = 1 - ((float) i / latitudeBands);

				int index = 3 * (j + i * (longitudeBands + 1));
				vertices[index] = radius * x;
				vertices[index + 1] = radius * y;
				vertices[index + 2] = radius * z;
				normals[index] = x;
				normals[index + 1] = y;
				normals[index + 2] = z;
				index = 2 * (j + i * (longitudeBands + 1));
				textureCoords[index] = u;
				textureCoords[index + 1] = v;
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

		gl.glGenBuffers(1, sphereVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexTextureCoordBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords), GL2.GL_STATIC_DRAW);
		sphereVertexTextureCoordBuffer[1] = 2;
		sphereVertexTextureCoordBuffer[2] = textureCoords.length / 2;

		gl.glGenBuffers(1, sphereVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, sphereVertexIndices.length * BufferUtil.SIZEOF_INT, BufferUtil.newIntBuffer(sphereVertexIndices), GL2.GL_STATIC_DRAW);
		sphereVertexIndexBuffer[1] = 1;
		sphereVertexIndexBuffer[2] = sphereVertexIndices.length;

		gl.glGenBuffers(1, greenVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		float[] vertices2 = { -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices2), GL2.GL_STATIC_DRAW);
		greenVertexPositionBuffer[1] = 3;
		greenVertexPositionBuffer[2] = 4;

		gl.glGenBuffers(1, greenVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		float[] textureCoords2 = { 0.0f, 10.0f, 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords2), GL2.GL_STATIC_DRAW);
		greenVertexTextureCoordBuffer[1] = 2;
		greenVertexTextureCoordBuffer[2] = 4;

		gl.glGenBuffers(1, greenVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexNormalBuffer[0]);
		float[] normals2 = { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals2), GL2.GL_STATIC_DRAW);
		greenVertexNormalBuffer[1] = 3;
		greenVertexNormalBuffer[2] = 4;

	}

	private void initTexture(GL2 gl) {
		gl.glGenTextures(2, textures, 0);

		TextureData tex1;
		try {
			tex1 = TextureIO.newTextureData(new File("images/bubble.gif"), false, null);
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
			tex2 = TextureIO.newTextureData(new File("images/grass.gif"), false, null);
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

		gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
	}

	private void drawScene(GL2 gl) {
		handleKeys();
		gl.glViewport(0, 0, 500, 500);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		float[] pMatrixv = new float[] { 2.4142136573791504f, 0, 0, 0, 0, 2.4142136573791504f, 0, 0, 0, 0, -1.0020020008087158f, -1, 0, 0, -0.20020020008087158f, 0 };
		pMatrix = BufferUtil.newFloatBuffer(pMatrixv);

		float[] mvMatrixv = new float[] { 10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0, zoom, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		float[] normalMatrixv = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, greenVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, greenVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, greenVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[1]);
		gl.glUniform1i(samplerUniform, 0);

		setMatrixUniforms(gl);
		gl.glUniform1f(alphaUniform, 1);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, greenVertexPositionBuffer[2]);

		mvMatrixv = new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, zoom, 1 };
		mvMatrixv = Util.multiplyMatrix(rotationMatrix, mvMatrixv);
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, sphereVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, sphereVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, sphereVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, sphereVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[0]);
		gl.glUniform1i(samplerUniform, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, sphereVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glUniform1f(alphaUniform, 0.8f);
		gl.glDrawElements(GL2.GL_TRIANGLES, sphereVertexIndexBuffer[2], GL2.GL_UNSIGNED_INT, 0);

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

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastMouseX = e.getX();
		lastMouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int deltaX = e.getX() - lastMouseX;
		int deltaY = e.getY() - lastMouseY;

		double rAnglex = deltaY * 0.01;
		double rAngley = deltaX * 0.01;

		float cx = (float) Math.cos(rAnglex);
		float sx = (float) Math.sin(rAnglex);
		float cy = (float) Math.cos(rAngley);
		float sy = (float) Math.sin(rAngley);

		float[] newRotationMatrix = new float[] { cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, 0, 0, 0, 1 };

		rotationMatrix = Util.multiplyMatrix(rotationMatrix, newRotationMatrix);

		lastMouseX = e.getX();
		lastMouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
