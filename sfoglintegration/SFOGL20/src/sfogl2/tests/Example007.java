package sfogl2.tests;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;

import sfogl2.SFJOGLFrame;
import sfogl2.SFJOGLFrameListener;
import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLFrameBuffer;
import sfogl2.SFOGLModelViewMatrix;
import sfogl2.SFOGLRenderBuffer;
import sfogl2.SFOGLShader;
import sfogl2.SFOGLSystemState;
import sfogl2.SFOGLTexture2D;

public class Example007 {

	public static void main(String[] args) {
		SFJOGLFrame.createFrame("Example002", 950, 650, new SFJOGLFrameListener(new GraphicsListener()));
	}
	
	public static class GraphicsListener implements SFOGLDrawable{
		
		private SFOGLShader shader=new SFOGLShader();
		private SFOGLBufferObject bufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject nBufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject indexBufferObject=new SFOGLBufferObject();
		private SFOGLModelViewMatrix matrix=new SFOGLModelViewMatrix();

		private SFOGLShader textureShader=new SFOGLShader();
		private SFOGLBufferObject textureBufferObject=new SFOGLBufferObject();
		private SFOGLBufferObject textureTxBufferObject=new SFOGLBufferObject();
		
		private int[][] viewports=ExamplesStaticData.framBuffersViewports;
		private int[][] sizes=ExamplesStaticData.framBuffersSizes;
		int t=0;
		
		private SFOGLTexture2D[] textures=new SFOGLTexture2D[viewports.length];

		public void draw(GL2ES2 gl) {

			SFOGLSystemState.cleanupColorDepth(gl, 1, 1, 1, 1);
			
			t++;
			float rotation=0.2f+t*0.012f;

			matrix.loadIdentity();
			matrix.rotateX((float)(Math.PI/6.0f));
			matrix.rotateY(rotation);

			shader.apply(gl);
			shader.setUniformValue(gl, 1, 1, 0.5f, 0, 1);
			
			int index=t/100;
			
			if(index<6){
				gl.glViewport(viewports[index][0], viewports[index][1], viewports[index][2], viewports[index][3]);
				drawCube(gl,0.5f,0.5f,0.5f);
			}

			for (int i = 0; i < index && i<6; i++) {
				if(textures[i]==null){
					textures[i]=new SFOGLTexture2D();
					
					int width=sizes[i][0];
					int height=sizes[i][1];
					
					SFOGLRenderBuffer renderBuffer=new SFOGLRenderBuffer();
					SFOGLFrameBuffer frameBuffer=new SFOGLFrameBuffer();
					
					textures[i].setup(gl, width, height);
					renderBuffer.setup(gl, width, height);
					
					frameBuffer.prepare(gl);
					frameBuffer.attachRenderBuffer(gl, GL.GL_DEPTH_ATTACHMENT, renderBuffer);
					frameBuffer.attachTexture(gl, GL.GL_COLOR_ATTACHMENT0, textures[i]);
			
					SFOGLFrameBuffer.checkFrameBuffer(gl);
					
					gl.glBindFramebuffer(GL.GL_FRAMEBUFFER, frameBuffer.getFrameBufferObject());
					SFOGLSystemState.cleanupColorDepth(gl, 1, 1, 0.5f, 0);
					gl.glViewport(0,0,width,height);

					shader.apply(gl);
					shader.setUniformValue(gl, 1, 1, 0.5f, 0, 1);
					drawCube(gl,0.5f,0.5f,0.5f);
					
					frameBuffer.unapply(gl);
					textures[i].generateMipmap(gl);
					
					//TODO : delete FrameBuffer and RenderBuffer
					//closeFrameBuffer(gl, this.fbo);
				}

				gl.glViewport(viewports[i][0], viewports[i][1], viewports[i][2], viewports[i][3]);
				gl.glEnable(GL2.GL_TEXTURE_2D);
				textures[i].bind(gl);
				
				textureShader.apply(gl);
				textureShader.bindAttributef(gl, 0, textureBufferObject.getVertexBufferObject(), 2);
				textureShader.bindAttributef(gl, 1, textureTxBufferObject.getVertexBufferObject(), 2);
				
				gl.glDrawArrays(GL2ES2.GL_TRIANGLE_STRIP, 0, 4);
				
				gl.glDisable(GL.GL_TEXTURE_2D);
			}
		}

		private void drawCube(GL2ES2 gl,float sizeX,float sizeY,float sizeZ) {
			
			matrix.push();
			matrix.scale(sizeX, sizeY, sizeZ);
			
			float[] mat=matrix.asOpenGLMatrix();
			shader.setUniformValue(gl, 0, 16,  mat);
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

			textureShader=new SFOGLShader(ExamplesStaticData.textureVertexShader, ExamplesStaticData.textureFragmentShader);
			textureShader.setAttribs("position","txCoord");
			textureShader.setUniforms("texture");
			textureShader.compileShaderWithInfos(gl,System.out);
			textureShader.compileData(gl);

			textureBufferObject.loadData(gl, ExamplesStaticData.textureVertices);
			textureTxBufferObject.loadData(gl, ExamplesStaticData.textureTexCoord);
		}
		
		@Override
		public void dispose(GL2ES2 gl) {
		}
	}
}
