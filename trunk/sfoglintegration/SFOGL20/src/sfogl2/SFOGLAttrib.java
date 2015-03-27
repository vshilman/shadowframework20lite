package sfogl2;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

public class SFOGLAttrib {

	private int attribLocation=-1;
	private String name;
	
	public SFOGLAttrib(String name) {
		super();
		this.name = name;
	}
	
	public void init(GL2ES2 gl,int shadingProgram){
		attribLocation = gl.glGetAttribLocation(shadingProgram, name);
	}
	
	public int getAttribLocation() {
		return attribLocation;
	}
	
	public void bindAttributef(GL2ES2 gl,int vertexBufferObject,int valueSize){
		gl.glEnableVertexAttribArray(attribLocation);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferObject);
		gl.glVertexAttribPointer(attribLocation, valueSize, GL2ES2.GL_FLOAT, false, 0, 0);
	}
}
