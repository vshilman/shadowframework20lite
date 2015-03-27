package sfogl2.tests;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLShader;
import sfogl2.SFOGLSystemState;

public class Example004 {

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
			
			shader.apply(gl);
			shader.setUniformValue(gl, 1, 1, 0, 0, 1);/*color*/
			
			t+=0.03f;
			float rotation=0.2f+t;
			float scaling=0.4f;
			
			drawCube(gl, rotation, scaling);
		}
		

		private void drawCube(GL2ES2 gl, float rotation, float scaling) {
			float cos=(float)(Math.cos(rotation));
			float sin=(float)(Math.sin(rotation));
			
			float[] matrix={
				scaling,	0,0,0,
				0,	scaling*cos,scaling*sin,0,
				0,	-scaling*sin,scaling*cos,0,
				0,0,0,1,
			};
			
			shader.setUniformValue(gl, 0, 16, matrix);/*transform*/
			shader.bindAttributef(gl, 0, bufferObject.getVertexBufferObject(), 3);/*position*/
			shader.bindAttributef(gl, 1, nBufferObject.getVertexBufferObject(), 3);/*normal*/
			
			indexBufferObject.drawAsIndexedBuffer(gl, GL.GL_TRIANGLES, 36);
		}

		/* init method
		 * */
		public void init(GL2ES2 gl) {
			
			shader=new SFOGLShader(ExamplesStaticData.vertexShader02, ExamplesStaticData.fragmentShader02);
			shader.setAttribs("position"/*0*/,"normal"/*1*/);
			shader.setUniforms("transform"/*0*/,"color"/*1*/);
			shader.init(gl);
			
			bufferObject.loadData(gl, ExamplesStaticData.cubeVertices);
			nBufferObject.loadData(gl, ExamplesStaticData.cubeNormals);
			indexBufferObject.loadData(gl, ExamplesStaticData.cubeIndices);
			
		}
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
	}
}
