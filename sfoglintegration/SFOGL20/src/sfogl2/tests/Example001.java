package sfogl2.tests;

import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLShader;
import sfogl2.SFOGLSystemState;

public class Example001 {

	public static void main(String[] args) {
		SFJOGLFrame.createFrame("Example001", 600, 600, new SFJOGLFrameListener(new GraphicsListener()));
	}
	
	public static class GraphicsListener implements SFOGLDrawable{
				
		private SFOGLShader shader=new SFOGLShader();
		private SFOGLBufferObject bufferObject=new SFOGLBufferObject();

		public void draw(GL2ES2 gl) {
			
			SFOGLSystemState.cleanupColorDepth(gl, 1, 1, 1, 1);
			
			shader.apply(gl);
			shader.setUniformValue(gl, 0, 1, 0, 0, 1);
			shader.bindAttributef(gl, 0, bufferObject.getVertexBufferObject(), 3);
			
			gl.glDrawArrays(GL2ES2.GL_TRIANGLES, 0, 3);
		}

		/* init method
		 * */
		public void init(GL2ES2 gl) {
			
			shader=new SFOGLShader(ExamplesStaticData.vertexShader01, ExamplesStaticData.fragmentShader01);
			shader.setAttribs("position");
			shader.setUniforms("color");
			shader.init(gl);
			
			bufferObject.loadData(gl, ExamplesStaticData.triangleVertices);
		}
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
	}
}
