package util;

import shadow.math.SFVertex3f;


public class Triangle {
	
	private SFVertex3f vertex1;
	private SFVertex3f vertex2;
	private SFVertex3f vertex3;
	
	private SFVertex3f normal1;
	private SFVertex3f normal2;
	private SFVertex3f normal3;
	
	private float ao1;
	private float ao2;
	private float ao3;
	
	
	public Triangle (SFVertex3f p1, SFVertex3f p2, SFVertex3f p3, SFVertex3f n1, SFVertex3f n2, SFVertex3f n3){
		
		this.vertex1 = p1;
		this.vertex2 = p2;
		this.vertex3 = p3;
		this.normal1 = n1;
		this.normal2 = n2;
		this.normal3 = n3;
		this.ao1 = 0;
		this.ao2 = 0;
		this.ao3 = 0;
		
		
	}
	
	public SFVertex3f getVertex1(){
		return this.vertex1;
	}
	
	public SFVertex3f getVertex2(){
		return this.vertex2;
	}
	
	public SFVertex3f getVertex3(){
		return this.vertex3;
	}
	
	public SFVertex3f getNormal1(){
		return this.normal1;
	}
	
	public SFVertex3f getNormal2(){
		return this.normal2;
	}
	
	public SFVertex3f getNormal3(){
		return this.normal3;
	}
	
	public float getAO1(){
		return this.ao1;
	}
	
	public float getAO2(){
		return this.ao2;
	}
	
	public float getAO3(){
		return this.ao3;
	}
	
	public void setAO1(float f){
		this.ao1=f;
	}
	
	public void setAO2(float f){
		this.ao2=f;
	}
	
	public void setAO3(float f){
		this.ao3=f;
	}
	
	
	public SFVertex3f[] getVertices(){
		SFVertex3f[] vertices = new SFVertex3f[3];
		vertices[0] = this.vertex1;
		vertices[1] = this.vertex2;
		vertices[2] = this.vertex3;
		return vertices;
	}
	
	public SFVertex3f[] getNormals(){
		SFVertex3f[] normals = new SFVertex3f[3];
		normals[0] = this.normal1;
		normals[1] = this.normal2;
		normals[2] = this.normal3;
		return normals;
	}
	
	public float[] getAOValues(){
		float[] ao = new float[3];
		ao[0] = this.ao1;
		ao[1] = this.ao2;
		ao[2] = this.ao3;
		return ao;
	}

}



