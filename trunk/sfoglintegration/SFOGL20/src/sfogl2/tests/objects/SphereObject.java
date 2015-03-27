package sfogl2.tests.objects;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLShader;
import sfogl2.tests.ExamplesSphereModel;
import sfogl2.tests.ExamplesStaticData;

public class SphereObject implements SFOGLDrawable{


	private SFOGLBufferObject bufferObject=new SFOGLBufferObject();
	private SFOGLBufferObject nBufferObject=new SFOGLBufferObject();
	private SFOGLBufferObject indexBufferObject=new SFOGLBufferObject();

	private SFOGLShader shader=new SFOGLShader();
	
	float t=0;

	public void draw(GL2ES2 gl) {
		shader.apply(gl);

		float[] matrix = getUpdatedMatrix();
		
		shader.setUniformValue(gl, 0, 16, matrix);
		shader.setUniformValue(gl, 1, 1, 0, 0, 1);
		
		shader.bindAttributef(gl, 0, bufferObject.getVertexBufferObject(), 3);
		shader.bindAttributef(gl, 1, nBufferObject.getVertexBufferObject(), 3);
		indexBufferObject.drawAsIndexedBuffer(gl, GL.GL_TRIANGLES, 2880);
	}

	private float[] getUpdatedMatrix() {
		t+=0.03f;
		float cos=(float)(Math.cos(0.2f+t));
		float sin=(float)(Math.sin(0.2f+t));
		
		float[] matrix={
			0.4f,0,0,0,
			0,0.4f*cos,0.4f*sin,0,
			0,-0.4f*sin,0.4f*cos,0,
			0,0,0,1,
		};
		return matrix;
	}
	
	public void init(GL2ES2 gl) {
		bufferObject.loadData(gl, ExamplesSphereModel.sphere_vertices);
		nBufferObject.loadData(gl, ExamplesSphereModel.sphere_normals);
		indexBufferObject.loadData(gl, ExamplesSphereModel.sphere_indices);			
	
		shader=new SFOGLShader(ExamplesStaticData.vertexShader02, ExamplesStaticData.fragmentShader02);
		shader.setAttribs("position","normal");
		shader.setUniforms("transform","color");
		shader.init(gl);
	}
	
	@Override
	public void dispose(GL2ES2 gl) {
		// TODO Auto-generated method stub
		
	}
}
