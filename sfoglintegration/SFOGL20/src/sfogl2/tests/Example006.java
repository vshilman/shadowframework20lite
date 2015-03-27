package sfogl2.tests;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLModelViewMatrix;
import sfogl2.SFOGLShader;
import sfogl2.SFOGLSystemState;

public class Example006 {

	public static void main(String[] args) {
		SFJOGLFrame.createFrame("Example002", 600, 600, new SFJOGLFrameListener(new GraphicsListener()));
	}
	
	public static class GraphicsListener implements SFOGLDrawable{

		private static final int NUMBER_OF_POLES=14;
		
		private SFOGLShader shader=new SFOGLShader();
		private SFOGLBufferObject bufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject nBufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject indexBufferObject=new SFOGLBufferObject();
		private SFOGLModelViewMatrix matrix=new SFOGLModelViewMatrix();
		float t=0;

		public void draw(GL2ES2 gl) {

			SFOGLSystemState.cleanupColorDepth(gl, 1, 1, 1, 1);
			
			shader.apply(gl);
			shader.setUniformValue(gl, 1, 1, 0, 0, 1);
			
			t+=0.01f;
			float rotation=0.2f+t;

			matrix.loadIdentity();
			matrix.rotateX((float)(Math.PI/6.0f));
			matrix.rotateY(rotation);

			shader.setUniformValue(gl, 1, 1, 0.5f, 0, 1);
			drawCube(gl,0.5f,0.05f,0.5f);

			float step=(float)(2*Math.PI/NUMBER_OF_POLES);
			
			for (int i = 0; i < NUMBER_OF_POLES; i++) {
				matrix.push();
					matrix.rotateY(i*step);
					matrix.translate3f(0.4f,0.25f,0.0f);
					shader.setUniformValue(gl, 1, 1, 1, 0, 1);
					drawCube(gl,0.02f,0.25f,0.02f);
					float y=(float)(0.10f*(Math.sin(rotation*2.0f+i*step*10)));
					matrix.translate3f(0,y,0);
					drawCube(gl,0.05f,0.05f,0.05f);
				matrix.pop();
			}
		}

		private void drawCube(GL2ES2 gl,float sizeX,float sizeY,float sizeZ) {
			
			matrix.push();
			matrix.scale(sizeX, sizeY, sizeZ);
			
			float[] mat=matrix.asOpenGLMatrix();
			shader.setUniformValue(gl, 0,16,  mat);
			shader.bindAttributef(gl, 0, bufferObject.getVertexBufferObject(), 3);
			shader.bindAttributef(gl, 1, nBufferObject.getVertexBufferObject(), 3);
			indexBufferObject.drawAsIndexedBuffer(gl, GL.GL_TRIANGLES, 36);
			
			matrix.pop();
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
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
	}
}
