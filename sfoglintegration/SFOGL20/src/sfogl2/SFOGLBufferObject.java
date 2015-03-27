package sfogl2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

public class SFOGLBufferObject {
	
	public static final int SIZE_OF_FLOAT=4;
	public static final int SIZE_OF_SHORT=2;
	
	private int vertexBufferObject;
	
	public SFOGLBufferObject(){
		
	}
	
	public int getVertexBufferObject() {
		return vertexBufferObject;
	}
	
	public void setVertexBufferObject(int vertexBufferObject) {
		this.vertexBufferObject = vertexBufferObject;
	}
	
	public void loadData(GL2ES2 gl,float[] data){
		this.vertexBufferObject=loadBufferObjectf(gl,data);
	}
	
	public void loadData(GL2ES2 gl,short[] data){
		this.vertexBufferObject=loadBufferObjects(gl,data);
	}
	
	public void drawAsIndexedBuffer(GL2ES2 gl, int primitiveType, int primitiveIndicesSize){
		gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, vertexBufferObject);
		gl.glDrawElements(primitiveType, primitiveIndicesSize, GL.GL_UNSIGNED_SHORT, 0);
	}

	public static ByteBuffer loadFloatBuffer(float[] data){
		
		ByteBuffer buffer=ByteBuffer.allocateDirect(data.length*SIZE_OF_FLOAT);
		buffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer=buffer.asFloatBuffer();
		floatBuffer.put(data, 0, data.length);
		floatBuffer.rewind();
		return buffer;
	}
	
	public static ByteBuffer loadShortBuffer(short[] data){
		
		ByteBuffer buffer=ByteBuffer.allocateDirect(data.length*SIZE_OF_SHORT);
		buffer.order(ByteOrder.nativeOrder());
		ShortBuffer shortBuffer=buffer.asShortBuffer();
		shortBuffer.put(data, 0, data.length);
		shortBuffer.rewind();
		return buffer;
	}
	
	public static int loadBufferObjectf(GL2ES2 gl,float[] data){
		
		int vbo = createBufferObject(gl);
		
		ByteBuffer byteData=loadFloatBuffer(data);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, data.length*SIZE_OF_FLOAT, byteData, GL.GL_STATIC_DRAW);
		
		return vbo;
	}
	
	public static int loadBufferObjects(GL2ES2 gl,short[] data){
		
		int vbo = createBufferObject(gl);
		
		ByteBuffer byteData=loadShortBuffer(data);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, data.length*SIZE_OF_SHORT, byteData, GL.GL_STATIC_DRAW);
		
		return vbo;
	}

	private static int createBufferObject(GL2ES2 gl) {
		int[] vbos = new int[1];
		gl.glGenBuffers(1, vbos, 0);
		int vbo=vbos[0];
		return vbo;
	}
	
}
