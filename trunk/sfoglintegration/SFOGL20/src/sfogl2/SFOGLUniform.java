package sfogl2;

import javax.media.opengl.GL2ES2;

public class SFOGLUniform {

	private int uniformLocation=-1;
	private String name;
	private float[] value;
	private int[] intValue;
	
	public SFOGLUniform(String name) {
		super();
		this.name = name;
	}
	
	public float[] getValue() {
		return value;
	}
	
	public int getUniformLocation() {
		return uniformLocation;
	}
	
	public void init(GL2ES2 gl,int shadingProgram){
		uniformLocation = gl.glGetUniformLocation(shadingProgram, name);
	}

	public void setValue(GL2ES2 gl,int startingIndex,int size,float... value) {
		this.value = value;
		if(uniformLocation!=-1 && gl!=null){
			switch (size) {
				case 1: gl.glUniform1fv(uniformLocation, 1, value, startingIndex);break;
				case 2: gl.glUniform2fv(uniformLocation, 1, value, startingIndex);break;
				case 3: gl.glUniform3fv(uniformLocation, 1, value, startingIndex);break;
				case 4: gl.glUniform4fv(uniformLocation, 1, value, startingIndex);break;
				case 9: gl.glUniformMatrix3fv(uniformLocation, 1, false, value, startingIndex);break;
				case 16: gl.glUniformMatrix4fv(uniformLocation, 1, false, value, startingIndex);break;
				default:
			}
		}
	}
	
	public void setValuei(GL2ES2 gl,int... value) {
		this.intValue = value;
		if(uniformLocation!=-1 && gl!=null){
			switch (value.length) {
				case 1: gl.glUniform1iv(uniformLocation, 1, intValue, 0);break;
				case 2: gl.glUniform2iv(uniformLocation, 1, intValue, 0);break;
				case 3: gl.glUniform3iv(uniformLocation, 1, intValue, 0);break;
				case 4: gl.glUniform4iv(uniformLocation, 1, intValue, 0);break;
				default:
			}
		}
	}
}
