package sfogl2.tests;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLShader;
import sfogl2.SFOGLSystemState;

public class Example003 {

	public static void main(String[] args) {
		SFJOGLFrame.createFrame("Example002", 600, 600, new SFJOGLFrameListener(new GraphicsListener()));
	}
	
	public static class GraphicsListener implements SFOGLDrawable{
			
		private SFOGLShader shader=new SFOGLShader();
		private SFOGLBufferObject bufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject nBufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject indexBufferObject=new SFOGLBufferObject();
		float t=0;

		public void draw(GL2ES2 gl) {

			SFOGLSystemState.cleanupColorDepth(gl, 1, 1, 1, 1);
			
			float[] matrix = getUpdatedMatrix();

			shader.apply(gl);
			shader.setUniformValue(gl, 0, 16,  matrix);
			shader.setUniformValue(gl, 1, 1, 0, 0, 1);
			shader.bindAttributef(gl, 0, bufferObject.getVertexBufferObject(), 3);
			shader.bindAttributef(gl, 1, nBufferObject.getVertexBufferObject(), 3);
			
			indexBufferObject.drawAsIndexedBuffer(gl, GL.GL_TRIANGLES, 36);
		}

		/* init method
		 * */
		public void init(GL2ES2 gl) {
			
			shader=new SFOGLShader(ExamplesStaticData.vertexShader02, ExamplesStaticData.fragmentShader02);
			shader.setAttribs("position","normal");
			shader.setUniforms("transform","color");
			shader.init(gl);
			
			bufferObject.loadData(gl, ExamplesStaticData.cubeVertices);
			nBufferObject.loadData(gl, ExamplesStaticData.cubeNormals);
			indexBufferObject.loadData(gl, ExamplesStaticData.cubeIndices);
			
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
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
	}
}
