package sfogl2;

import java.io.PrintStream;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;

public class SFOGLShader {

	private String fragmentShader;
	private String vertexShader;
	private SFOGLAttrib[] attribs;
	private SFOGLUniform[] uniforms;
	
	private int shadingProgram,vShader,fShader;
	private boolean initialized=false;
	
	public SFOGLShader() {
		super();
	}
	
	public void setAttribs(String... attribs) {
		this.attribs=new SFOGLAttrib[attribs.length];
		for (int i = 0; i < attribs.length; i++) {
			this.attribs[i]=new SFOGLAttrib(attribs[i]);
		}
	}
	
	public void setUniforms(String... uniforms) {
		this.uniforms=new SFOGLUniform[uniforms.length];
		for (int i = 0; i < uniforms.length; i++) {
			this.uniforms[i]=new SFOGLUniform(uniforms[i]);
		}
	}
	
	public void init(GL2ES2 gl){
		if(!initialized){
			compileShader(gl);
			compileData(gl);
		}
	}

	public void compileData(GL2ES2 gl) {
		for (int i = 0; i < attribs.length; i++) {
			attribs[i].init(gl, shadingProgram);
		}
		
		for (int i = 0; i < uniforms.length; i++) {
			uniforms[i].init(gl, shadingProgram);
		}
	}
	
	public int getAttribLocation(int index){
		return attribs[index].getAttribLocation();
	}
	
	public void bindAttributef(GL2ES2 gl,int index,int vertexBufferObject,int valueSize){
		attribs[index].bindAttributef(gl, vertexBufferObject, valueSize);
	}
	
	public int getUniformLocation(int index){
		return uniforms[index].getUniformLocation();
	}
	
	public float[] getUniformValue(int index){
		return uniforms[index].getValue();
	}

	public void setUniformValue(GL2ES2 gl, int index,int size,float... value){
		uniforms[index].setValue(gl,0,size, value);
	}
	
	public void setUniformValue(GL2ES2 gl, int index,int arrayindex,int size,float... value){
		uniforms[index].setValue(gl,arrayindex,size, value);
	}
	
	public void setUniformValuei(GL2ES2 gl, int index,int... value){
		uniforms[index].setValuei(gl, value);
	}
	
	
	public SFOGLShader(String vertexShader, String fragmentShader) {
		super();
		this.vertexShader = vertexShader;
		this.fragmentShader = fragmentShader;
	}
	
	public void setShaders(String fragmentShader, String vertexShader) {
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;
	}
	
	public void compileShaderWithInfos(GL2ES2 gl,PrintStream writer){
		
		this.shadingProgram=gl.glCreateProgram();
		
		writer.println("Compiling Vertex Shader");
		writer.print(vertexShader);
		compileVertexShader(gl);
		String compilerOutput = compiledShaderInfo((GL2)gl, vShader);
		writer.println(compilerOutput);

		writer.println("Compiling Fragment Shader");
		writer.print(fragmentShader);
		compileFragmentShader(gl);
		compilerOutput = compiledShaderInfo((GL2)gl, fShader);
		writer.println(compilerOutput);

		writer.println("Linking Program");
		compileProgram(gl,shadingProgram,vShader,fShader);
		compilerOutput = compiledProgramInfo((GL2)gl, shadingProgram);
		writer.println(compilerOutput);
	}

	public void compileShader(GL2ES2 gl){
		
		this.shadingProgram=gl.glCreateProgram();
		
		compileVertexShader(gl);
		
		compileFragmentShader(gl);
		
		compileProgram(gl,shadingProgram,vShader,fShader);
		
		initialized=true;
	}

	public void compileFragmentShader(GL2ES2 gl) {
		this.fShader = loadShader(gl, fragmentShader, GL2ES2.GL_FRAGMENT_SHADER);
	}

	public void compileVertexShader(GL2ES2 gl) {
		this.vShader = loadShader(gl, vertexShader, GL2ES2.GL_VERTEX_SHADER);
	}
	
	public void apply(GL2ES2 gl){
		gl.glUseProgram(shadingProgram);
	}
	
	public void unapply(GL2ES2 gl){
		gl.glUseProgram(0);
	}
	
	public int getShadingProgram() {
		return shadingProgram;
	}
	
	public void delete(GL2ES2 gl){
		gl.glDeleteProgram(shadingProgram);
		gl.glDeleteShader(vShader);
		gl.glDeleteShader(fShader);
	}

	public static void compileProgram(GL2ES2 gl,int shadingProgram,int vShader,int fShader) {
		gl.glAttachShader(shadingProgram, vShader);
		
		gl.glAttachShader(shadingProgram, fShader);
		
		gl.glLinkProgram(shadingProgram);
		
		gl.glValidateProgram(shadingProgram);
	}
	
	public static String compiledShaderInfo(GL2 gl, int shader) {
		int status[]=new int[1];
		
		gl.glGetObjectParameterivARB(shader,GL2.GL_OBJECT_COMPILE_STATUS_ARB,status,0);
	
		if(status[0]==0){
			int len[]=new int[1];
			gl.glGetObjectParameterivARB(shader, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
					len,0);

			byte[] b=new byte[20000];
			gl.glGetInfoLogARB(shader,b.length,status,0,b,0);

			return new String(b);
		}
        
		return "";
	}
	
	public static String compiledProgramInfo(GL2 gl, int program) {

		int[] status=new int[1];
		gl.glGetObjectParameterivARB(program, GL2.GL_OBJECT_LINK_STATUS_ARB, status,0);
		
		//System.out.println("Status "+status2[0]);
		if(status[0]==0){
			int len[]=new int[1];
			gl.glGetObjectParameterivARB(program, GL2.GL_OBJECT_COMPILE_STATUS_ARB,
					len,0);

			byte[] b=new byte[2000];
			gl.glGetInfoLogARB(program,b.length,status,0,b,0);

			return new String(b);
		}

		return "";

	}

	public static void bindAttribs(GL2ES2 gl,int program,String... attribs){
		for (int i = 0; i < attribs.length; i++) {
			gl.glBindAttribLocation(program, i, attribs[i]);
		}
	} 
	
	public static int loadShader(GL2ES2 gl, String shaderSource, int shaderType) {
		int shaderID = gl.glCreateShader(shaderType);
		String[] shadersText={shaderSource};
		int[] shaderLength={shaderSource.length()};
		gl.glShaderSource(shaderID, 1, shadersText, shaderLength, 0);
		gl.glCompileShader(shaderID);
		return shaderID;
	}
	
	public static int loadShader(GL2ES2 gl, String[] shaderSource, int shaderType) {
		int shaderID = gl.glCreateShader(shaderType);
		int[] shaderLength=new int[shaderSource.length];
		for (int i = 0; i < shaderLength.length; i++) {
			shaderLength[i]=shaderSource.length;
		}
		gl.glShaderSource(shaderID, shaderSource.length, shaderSource, shaderLength, 0);
		gl.glCompileShader(shaderID);
		return shaderID;
	}
}

