package test.jogl;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Arrays;

import javax.media.opengl.GL2;

import test.jogl.util.Util;
import test.jogl.util.Util.ObjModel;

import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

public class Test_va2Drawer {
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
	private int[] doorVertexPositionBuffer = new int[3];
	private int[] doorVertexNormalBuffer = new int[3];
	private int[] doorVertexTextureCoordBuffer = new int[3];
	private int[] greenVertexPositionBuffer = new int[3];
	private int[] greenVertexNormalBuffer = new int[3];
	private int[] greenVertexTextureCoordBuffer = new int[3];
	private int[] pyramidVertexPositionBuffer = new int[3];
	private int[] pyramidVertexNormalBuffer = new int[3];
	private int[] pyramidVertexIndexBuffer = new int[3];
	private int[] pyramidVertexTextureCoordBuffer = new int[3];
	private int[] cubeVertexPositionBuffer = new int[3];
	private int[] cubeVertexNormalBuffer = new int[3];
	private int[] cubeVertexIndexBuffer = new int[3];
	private int[] cubeVertexTextureCoordBuffer = new int[3];
	private int[] tractorVertexPositionBuffer = new int[3];
	private int[] tractorVertexNormalBuffer = new int[3];
	private int[] tractorVertexTextureCoordBuffer = new int[3];
	private int[] tractorVertexIndexBuffer = new int[3];
	private int[] seagulVertexPositionBuffer = new int[3];
	private int[] seagulVertexNormalBuffer = new int[3];
	private int[] seagulVertexTextureCoordBuffer = new int[3];
	private int[] seagulVertexIndexBuffer = new int[3];
	private int[] textures = new int[6];
	private FloatBuffer pMatrix;
	private FloatBuffer mvMatrix;
	private FloatBuffer normalMatrix;
	private long lastTime = 0;
	private boolean[] commands = new boolean[8];
	private double seagulAngle = 0;
	private float tractorx = 0;
	private double pitch = 0;
	private double yaw = 0;
	private float xPos = 0;
	private float yStart = -1.5f;
	private float yPos = yStart;
	private float zPos = 10;
	private float speed = 0;
	private float lspeed = 0;
	private float pitchRate = 0;
	private float yawRate = 0;
	private double joggingAngle = 0;

	public void initShaders(GL2 gl) {
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
	}

	public void initBuffers(GL2 gl) {

		gl.glGenBuffers(1, pyramidVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		float[] vertices1 = { 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices1.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices1), GL2.GL_STATIC_DRAW);
		pyramidVertexPositionBuffer[1] = 3;
		pyramidVertexPositionBuffer[2] = 12;

		gl.glGenBuffers(1, pyramidVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexNormalBuffer[0]);
		float[] normals1 = { -0.89442719099991587856366946749251f, 0.44721359549995793928183473374626f, 0.0f, -0.89442719099991587856366946749251f, 0.44721359549995793928183473374626f, 0.0f, -0.89442719099991587856366946749251f, 0.44721359549995793928183473374626f, 0.0f, 0.0f, 0.44721359549995793928183473374626f, -0.89442719099991587856366946749251f, 0.0f, 0.44721359549995793928183473374626f, -0.89442719099991587856366946749251f, 0.0f, 0.44721359549995793928183473374626f, -0.89442719099991587856366946749251f, 0.0f, 0.44721359549995793928183473374626f, 0.89442719099991587856366946749251f, 0.0f, 0.44721359549995793928183473374626f, 0.89442719099991587856366946749251f, 0.0f, 0.44721359549995793928183473374626f, 0.89442719099991587856366946749251f, 0.89442719099991587856366946749251f, 0.44721359549995793928183473374626f, 0.0f, 0.89442719099991587856366946749251f, 0.44721359549995793928183473374626f, 0.0f, 0.89442719099991587856366946749251f, 0.44721359549995793928183473374626f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals1.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals1), GL2.GL_STATIC_DRAW);
		pyramidVertexNormalBuffer[1] = 3;
		pyramidVertexNormalBuffer[2] = 12;

		gl.glGenBuffers(1, pyramidVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		float[] textureCoords1 = { 3.0f, 0.0f, 0.0f, 6.0f, 6.0f, 6.0f, 3.0f, 0.0f, 0.0f, 6.0f, 6.0f, 6.0f, 3.0f, 0.0f, 6.0f, 6.0f, 0.0f, 6.0f, 3.0f, 0.0f, 0.0f, 6.0f, 6.0f, 6.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords1.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords1), GL2.GL_STATIC_DRAW);
		pyramidVertexTextureCoordBuffer[1] = 2;
		pyramidVertexTextureCoordBuffer[2] = 12;

		gl.glGenBuffers(1, pyramidVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, pyramidVertexIndexBuffer[0]);
		short[] pyramidVertexIndices = { 0, 1, 2, 3, 5, 4, 6, 7, 8, 9, 10, 11 };
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, pyramidVertexIndices.length * BufferUtil.SIZEOF_SHORT, BufferUtil.newShortBuffer(pyramidVertexIndices), GL2.GL_STATIC_DRAW);
		pyramidVertexIndexBuffer[1] = 1;
		pyramidVertexIndexBuffer[2] = 12;

		gl.glGenBuffers(1, cubeVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexPositionBuffer[0]);
		float[] vertices2 = { -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices2), GL2.GL_STATIC_DRAW);
		cubeVertexPositionBuffer[1] = 3;
		cubeVertexPositionBuffer[2] = 16;

		gl.glGenBuffers(1, cubeVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexNormalBuffer[0]);
		float[] normals2 = { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals2), GL2.GL_STATIC_DRAW);
		cubeVertexNormalBuffer[1] = 3;
		cubeVertexNormalBuffer[2] = 16;

		gl.glGenBuffers(1, cubeVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, cubeVertexTextureCoordBuffer[0]);
		float[] textureCoords2 = { 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 0.0f, 10.0f, 10.0f, 0.0f, 10.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords2.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords2), GL2.GL_STATIC_DRAW);
		cubeVertexTextureCoordBuffer[1] = 2;
		cubeVertexTextureCoordBuffer[2] = 16;

		gl.glGenBuffers(1, cubeVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0]);
		short[] cubeVertexIndices = { 0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15 };
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndices.length * BufferUtil.SIZEOF_SHORT, BufferUtil.newShortBuffer(cubeVertexIndices), GL2.GL_STATIC_DRAW);
		cubeVertexIndexBuffer[1] = 1;
		cubeVertexIndexBuffer[2] = 24;

		gl.glGenBuffers(1, greenVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexPositionBuffer[0]);
		float[] vertices3 = { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices3.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices3), GL2.GL_STATIC_DRAW);
		greenVertexPositionBuffer[1] = 3;
		greenVertexPositionBuffer[2] = 4;

		gl.glGenBuffers(1, greenVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, greenVertexNormalBuffer[0]);
		float[] normals3 = { 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals3.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals3), GL2.GL_STATIC_DRAW);
		greenVertexNormalBuffer[1] = 3;
		greenVertexNormalBuffer[2] = 4;

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

		gl.glGenBuffers(1, doorVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexNormalBuffer[0]);
		float[] normals4 = { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, normals4.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(normals4), GL2.GL_STATIC_DRAW);
		doorVertexNormalBuffer[1] = 3;
		doorVertexNormalBuffer[2] = 4;

		gl.glGenBuffers(1, doorVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		float[] textureCoords4 = { 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f };
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, textureCoords4.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(textureCoords4), GL2.GL_STATIC_DRAW);
		doorVertexTextureCoordBuffer[1] = 2;
		doorVertexTextureCoordBuffer[2] = 4;

		ObjModel tractor = Util.loadObj("models/tractor.obj");

		gl.glGenBuffers(1, tractorVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, tractorVertexPositionBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, tractor.vertexPositions.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(tractor.vertexPositions), GL2.GL_STATIC_DRAW);
		tractorVertexPositionBuffer[1] = 3;
		tractorVertexPositionBuffer[2] = tractor.vertexPositions.length / 3;

		gl.glGenBuffers(1, tractorVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, tractorVertexNormalBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, tractor.vertexNormals.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(tractor.vertexNormals), GL2.GL_STATIC_DRAW);
		tractorVertexNormalBuffer[1] = 3;
		tractorVertexNormalBuffer[2] = tractor.vertexNormals.length / 3;

		gl.glGenBuffers(1, tractorVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, tractorVertexTextureCoordBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, tractor.vertexTextureCoords.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(tractor.vertexTextureCoords), GL2.GL_STATIC_DRAW);
		tractorVertexTextureCoordBuffer[1] = 2;
		tractorVertexTextureCoordBuffer[2] = tractor.vertexTextureCoords.length / 2;

		gl.glGenBuffers(1, tractorVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, tractorVertexIndexBuffer[0]);
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, tractor.indices.length * BufferUtil.SIZEOF_SHORT, BufferUtil.newShortBuffer(tractor.indices), GL2.GL_STATIC_DRAW);
		tractorVertexIndexBuffer[1] = 1;
		tractorVertexIndexBuffer[2] = tractor.indices.length;

		ObjModel seagul = Util.loadObj("models/seagul.obj");

		gl.glGenBuffers(1, seagulVertexPositionBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, seagulVertexPositionBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, seagul.vertexPositions.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(seagul.vertexPositions), GL2.GL_STATIC_DRAW);
		seagulVertexPositionBuffer[1] = 3;
		seagulVertexPositionBuffer[2] = seagul.vertexPositions.length / 3;

		gl.glGenBuffers(1, seagulVertexNormalBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, seagulVertexNormalBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, seagul.vertexNormals.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(seagul.vertexNormals), GL2.GL_STATIC_DRAW);
		seagulVertexNormalBuffer[1] = 3;
		seagulVertexNormalBuffer[2] = seagul.vertexNormals.length / 3;

		gl.glGenBuffers(1, seagulVertexTextureCoordBuffer, 0);
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, seagulVertexTextureCoordBuffer[0]);
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, seagul.vertexTextureCoords.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(seagul.vertexTextureCoords), GL2.GL_STATIC_DRAW);
		seagulVertexTextureCoordBuffer[1] = 2;
		seagulVertexTextureCoordBuffer[2] = seagul.vertexTextureCoords.length / 2;

		gl.glGenBuffers(1, seagulVertexIndexBuffer, 0);
		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, seagulVertexIndexBuffer[0]);
		gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER, seagul.indices.length * BufferUtil.SIZEOF_SHORT, BufferUtil.newShortBuffer(seagul.indices), GL2.GL_STATIC_DRAW);
		seagulVertexIndexBuffer[1] = 1;
		seagulVertexIndexBuffer[2] = seagul.indices.length;
	}

	public void initTexture(GL2 gl) throws IOException {
		gl.glGenTextures(6, textures, 0);

		TextureData tex1 = TextureIO.newTextureData(new File("images/muro.gif"), false, null);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[0]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex1.getWidth(), tex1.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex1.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex2 = TextureIO.newTextureData(new File("images/roof.gif"), false, null);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[1]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex2.getWidth(), tex2.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex2.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex3 = TextureIO.newTextureData(new File("images/grass.gif"), false, null);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[2]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex3.getWidth(), tex3.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex3.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex4 = TextureIO.newTextureData(new File("images/door.gif"), false, null);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[3]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex4.getWidth(), tex4.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex4.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex5 = TextureIO.newTextureData(new File("images/tractor.gif"), false, null);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[4]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex5.getWidth(), tex5.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex5.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_MIRRORED_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_MIRRORED_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		TextureData tex6 = TextureIO.newTextureData(new File("images/seagul.gif"), false, null);
		
		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[5]);
		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, tex6.getWidth(), tex6.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, tex6.getBuffer());
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
		gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
	}

	public void drawScene(GL2 gl) {
		gl.glClearColor(0.0f, 0.71f, 0.91f, 1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);

		handleKeys();
		gl.glViewport(0, 0, 500, 500);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		float cx = (float) Math.cos(-pitch);
		float sx = (float) Math.sin(-pitch);
		float cy = (float) Math.cos(-yaw);
		float sy = (float) Math.sin(-yaw);

		float[] pMatrixv = new float[] { 2.4142136573791504f, 0, 0, 0, 0, 2.4142136573791504f, 0, 0, 0, 0, -1.0020020008087158f, -1, 0, 0, -0.20020020008087158f, 0 };
		pMatrix = BufferUtil.newFloatBuffer(pMatrixv);

		float[] mvMatrixv = new float[] { cy, sx * sy, -cx * sy, 0, 0, cx, sx, 0, sy, -cy * sx, cx * cy, 0, -cy * xPos - sy * zPos, -cx - sx * sy * xPos - cx * yPos + cy * sx * zPos, -sx + cx * sy * xPos - sx * yPos - cx * cy * zPos, 1 };
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		float[] normalMatrixv = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glUniform3f(ambientColorUniform, 0.1f, 0.1f, 0.1f);

		float[] lightLoc = new float[] { 0, 4, 2, 1 };
		float[] tlightLoc = Util.multiplyMatrixVector(pMatrixv, Util.multiplyMatrixVector(mvMatrixv, lightLoc));
		gl.glUniform3f(pointLightingLocationUniform, tlightLoc[0], tlightLoc[1], tlightLoc[2]);

		gl.glUniform3f(pointLightingDiffuseColorUniform, 0.5f, 0.5f, 0.5f);
		gl.glUniform3f(pointLightingSpecularColorUniform, 0.5f, 0.5f, 0.5f);

		gl.glUniform1f(materialShininessUniform, 50);
		gl.glUniform1f(alphaUniform, 1);

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
		gl.glDrawElements(GL2.GL_TRIANGLES, cubeVertexIndexBuffer[2], GL2.GL_UNSIGNED_SHORT, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, doorVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, doorVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, doorVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, doorVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[3]);
		gl.glUniform1i(samplerUniform, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, doorVertexPositionBuffer[2]);

		gl.glDisable(GL2.GL_CULL_FACE);

		float[] mvMatrixv2 = Arrays.copyOf(mvMatrixv, mvMatrixv.length);
		mvMatrixv[0] *= 10;
		mvMatrixv[1] *= 10;
		mvMatrixv[2] *= 10;
		mvMatrixv[8] *= 10;
		mvMatrixv[9] *= 10;
		mvMatrixv[10] *= 10;
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		normalMatrixv = new float[9];
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

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[2]);
		gl.glUniform1i(samplerUniform, 0);

		setMatrixUniforms(gl);
		gl.glDrawArrays(GL2.GL_TRIANGLE_STRIP, 0, greenVertexPositionBuffer[2]);

		mvMatrixv = Arrays.copyOf(mvMatrixv2, mvMatrixv2.length);

		gl.glEnable(GL2.GL_CULL_FACE);

		mvMatrixv[13] += 2 * cx;
		mvMatrixv[14] += 2 * sx;
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		normalMatrixv = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, pyramidVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, pyramidVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, pyramidVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, pyramidVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[1]);
		gl.glUniform1i(samplerUniform, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, pyramidVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, pyramidVertexIndexBuffer[2], GL2.GL_UNSIGNED_SHORT, 0);

		mvMatrixv = Arrays.copyOf(mvMatrixv2, mvMatrixv2.length);
		for (int i = 0; i < 12; i++) {
			mvMatrixv[i] *= 0.01;
		}
		float xt = -11 + tractorx;
		float yt = -1;
		float zt = 4;
		mvMatrixv[12] += cy * xt + sy * zt;
		mvMatrixv[13] += sx * sy * xt + cx * yt - cy * sx * zt;
		mvMatrixv[14] += -cx * sy * xt + sx * yt + cx * cy * zt;
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		normalMatrixv = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, tractorVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, tractorVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, tractorVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, tractorVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, tractorVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, tractorVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[4]);
		gl.glUniform1i(samplerUniform, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, tractorVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, tractorVertexIndexBuffer[2], GL2.GL_UNSIGNED_SHORT, 0);

		mvMatrixv = mvMatrixv2;
		float cs = (float) Math.cos(seagulAngle);
		float ss = (float) Math.sin(seagulAngle);
		xt = -3 * cs - xPos;
		yt = 2 - yPos;
		zt = 4 * ss - zPos;
		float s = 0.3f;
		mvMatrixv[0] = cs * cy * s - s * ss * sy;
		mvMatrixv[1] = cy * s * ss * sx + cs * s * sx * sy;
		mvMatrixv[2] = -cx * cy * s * ss - cs * cx * s * sy;
		mvMatrixv[3] = 0;
		mvMatrixv[4] = 0;
		mvMatrixv[5] = cx * s;
		mvMatrixv[6] = s * sx;
		mvMatrixv[7] = 0;
		mvMatrixv[8] = cy * s * ss + cs * s * sy;
		mvMatrixv[9] = -cs * cy * s * sx + s * ss * sx * sy;
		mvMatrixv[10] = cs * cx * cy * s - cx * s * ss * sy;
		mvMatrixv[11] = 0;
		mvMatrixv[12] = cy * xt + sy * zt;
		mvMatrixv[13] = -cx + sx * sy * xt + cx * yt - cy * sx * zt;
		mvMatrixv[14] = -sx - cx * sy * xt + sx * yt + cx * cy * zt;
		mvMatrixv[15] = 1;
		mvMatrix = BufferUtil.newFloatBuffer(mvMatrixv);

		normalMatrixv = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				normalMatrixv[i + 3 * j] = mvMatrixv[i + 4 * j];
			}
		}
		normalMatrix = BufferUtil.newFloatBuffer(normalMatrixv);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, seagulVertexPositionBuffer[0]);
		gl.glVertexAttribPointer(vertexPositionAttribute, seagulVertexPositionBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, seagulVertexNormalBuffer[0]);
		gl.glVertexAttribPointer(vertexNormalAttribute, seagulVertexNormalBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, seagulVertexTextureCoordBuffer[0]);
		gl.glVertexAttribPointer(textureCoordAttribute, seagulVertexTextureCoordBuffer[1], GL2.GL_FLOAT, false, 0, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, textures[5]);
		gl.glUniform1i(samplerUniform, 0);

		gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, seagulVertexIndexBuffer[0]);
		setMatrixUniforms(gl);
		gl.glDrawElements(GL2.GL_TRIANGLES, seagulVertexIndexBuffer[2], GL2.GL_UNSIGNED_SHORT, 0);

		animate();

	}

	private void animate() {
		long timeNow = System.currentTimeMillis();
		if (lastTime != 0) {
			long elapsed = timeNow - lastTime;
			boolean move = false;
			if (speed != 0) {
				xPos -= Math.sin(yaw) * speed * elapsed;
				zPos -= Math.cos(yaw) * speed * elapsed;
				move = true;
			}
			if (lspeed != 0) {
				xPos += Math.cos(yaw) * lspeed * elapsed;
				zPos -= Math.sin(yaw) * lspeed * elapsed;
				move = true;
			}

			if (move) {
				joggingAngle += elapsed * 0.0104;
				yPos = (float) (Math.sin(joggingAngle) / 20 + yStart);
			}

			yaw += yawRate * elapsed;
			pitch += pitchRate * elapsed;

			seagulAngle += 0.0008 * elapsed;
			tractorx += 0.0008 * elapsed;
			if (tractorx > 22) {
				tractorx = 0;
			}
		}
		lastTime = timeNow;
	}

	private void handleKeys() {
		if (commands[0]) {
			speed = 0.003f;
		} else if (commands[1]) {
			speed = -0.003f;
		} else {
			speed = 0;
		}

		if (commands[2]) {
			pitchRate = 0.00174f;
		} else if (commands[3]) {
			pitchRate = -0.00174f;
		} else {
			pitchRate = 0;
		}

		if (commands[4]) {
			yawRate = 0.00174f;
		} else if (commands[5]) {
			yawRate = -0.00174f;
		} else {
			yawRate = 0;
		}

		if (commands[6]) {
			lspeed = 0.003f;
		} else if (commands[7]) {
			lspeed = -0.003f;
		} else {
			lspeed = 0;
		}

	}

	private void setCommands(KeyEvent e, boolean value) {
		if (e.getKeyCode() == 87) {
			commands[0] = value;
		}
		if (e.getKeyCode() == 83) {
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
		if (e.getKeyCode() == 68) {
			commands[6] = value;
		}
		if (e.getKeyCode() == 65) {
			commands[7] = value;
		}
	}

	public void keyPressed(KeyEvent e) {
		setCommands(e, true);
	}

	public void keyReleased(KeyEvent e) {
		setCommands(e, false);
	}
}
