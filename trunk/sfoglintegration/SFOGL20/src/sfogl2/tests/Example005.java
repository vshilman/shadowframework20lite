package sfogl2.tests;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLShader;
import sfogl2.SFOGLSystemState;
import shadow.math.SFMatrix3f;

public class Example005 {

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
			shader.setUniformValue(gl, 1, 1, 0, 0, 1);
			
			t+=0.01f;
			float rotation=0.2f+t;

			shader.setUniformValue(gl, 1, 1, 0.5f, 0, 1);
			drawCube(gl, rotation, rotation*2,rotation*0.5f,0.1f,0.3f,0.4f, 0,0.4f,0);

			shader.setUniformValue(gl, 1, 1, 1, 0, 1);
			drawCube(gl, rotation*0.5f, rotation,rotation*2,0.1f,0.1f,0.15f, 0.4f,-0.4f,0);

			shader.setUniformValue(gl, 1, 0, 1, 1, 1);
			drawCube(gl, rotation*2, rotation*0.5f,rotation,0.2f,0.1f,0.5f, -0.4f,-0.4f,0);
		}
		
		private void drawCube(GL2ES2 gl, float rotX,float rotY,float rotZ,
				float scaleX,float scaleY,float scaleZ,float positionX,float posiziontY,float positionZ) {
			
			SFMatrix3f matrix=SFMatrix3f.getIdentity();
			matrix=matrix.MultMatrix(SFMatrix3f.getScale(scaleX, scaleY, scaleZ));
			matrix=matrix.MultMatrix(SFMatrix3f.getRotationX(rotX));
			matrix=matrix.MultMatrix(SFMatrix3f.getRotationY(rotY));
			matrix=matrix.MultMatrix(SFMatrix3f.getRotationZ(rotZ));
			
			float[] mat={
				matrix.getA(),matrix.getB(),matrix.getC(),0,
				matrix.getD(),matrix.getE(),matrix.getF(),0,
				matrix.getG(),matrix.getH(),matrix.getI(),0,
				positionX,posiziontY,positionZ,1,
			};
			
			shader.setUniformValue(gl, 0, 16, mat);
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
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
	}
}
