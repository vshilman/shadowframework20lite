package test.utilsP;

import javax.media.opengl.GL2ES2;

import com.jogamp.opengl.util.glsl.ShaderUtil;

public class Shader {

	private String fragmentShader;
	private String vertexShader;

	private int shadingProgram;

	public Shader(String fragmentShader, String vertexShader) {
		super();
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;
	}

	public void compileShader(GL2ES2 gl){

		shadingProgram=gl.glCreateProgram();

		int vShader = loadShader(gl, vertexShader, GL2ES2.GL_VERTEX_SHADER);
		int fShader = loadShader(gl, fragmentShader, GL2ES2.GL_FRAGMENT_SHADER);

		gl.glAttachShader(shadingProgram, vShader);

		gl.glAttachShader(shadingProgram, fShader);

		gl.glLinkProgram(shadingProgram);

		gl.glValidateProgram(shadingProgram);
		
		String vertexError = ShaderUtil.getShaderInfoLog(gl, vShader);		
		if(!vertexError.equals("")){
			System.err.println("Error in Vertex Shader");
			System.err.println(vertexError);
		}
		
		String fragmentError = ShaderUtil.getShaderInfoLog(gl, fShader);
		if(!fragmentError.equals("")) {
			System.err.println("Error in Fragment Shader");
			System.err.println(fragmentError);
		}
	}

	public int getShadingProgram() {
		return shadingProgram;
	}

	private int loadShader(GL2ES2 gl, String shader, int shaderCode) {
		int shaderID = gl.glCreateShader(shaderCode);
		String[] shadersText={shader};
		int[] shaderLength={shader.length()};
		gl.glShaderSource(shaderID, 1, shadersText, shaderLength, 0);
		gl.glCompileShader(shaderID);
		return shaderID;
	}
}
