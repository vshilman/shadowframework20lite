package sfogl2.tests.objects;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;

import sfogl2.SFOGLBufferObject;
import sfogl2.SFOGLDrawable;
import sfogl2.SFOGLRenderedTexture;
import sfogl2.SFOGLShader;
import sfogl2.tests.ExamplesStaticData;

public class BlurredScreenObject implements SFOGLDrawable{
	
	private int frameBufferSize=256;
	private SFOGLRenderedTexture renderedTexture;//=new SFOGLRenderedTexture(512,true);

	public BlurredScreenObject(int frameBufferSize,
			SFOGLRenderedTexture renderedTexture) {
		super();
		this.frameBufferSize = frameBufferSize;
		this.renderedTexture = renderedTexture;
	}


	private SFOGLShader blurShader=new SFOGLShader();
	
	private SFOGLBufferObject screenBufferObject=new SFOGLBufferObject();
	private SFOGLBufferObject screenTextureBufferObject=new SFOGLBufferObject();
	private SFOGLBufferObject screenIndexBufferObject=new SFOGLBufferObject();

	public void init(GL2ES2 gl) {
	
		blurShader=new SFOGLShader(ExamplesStaticData.blurVertexShader, ExamplesStaticData.blurFragmentShader);
		blurShader.setAttribs("position","txCoord");
		blurShader.setUniforms("texture","texelSize");
		//blurShader.init(gl);

		blurShader.compileShaderWithInfos(gl,System.out);
		blurShader.compileData(gl);
		
		screenBufferObject.loadData(gl, ExamplesStaticData.screenQuadVertices);
		screenTextureBufferObject.loadData(gl, ExamplesStaticData.textureQuadVertices);
		screenIndexBufferObject.loadData(gl, ExamplesStaticData.quadIndices);
	}
	

	public void draw(GL2ES2 gl) {
		blurShader.apply(gl);
		blurShader.bindAttributef(gl, 0, screenBufferObject.getVertexBufferObject(), 3);
		blurShader.bindAttributef(gl, 1, screenTextureBufferObject.getVertexBufferObject(), 3);
		float texelSize=1.01f/(1.0f*frameBufferSize);
	    blurShader.setUniformValue(gl, 1, 16, texelSize);
	    blurShader.setUniformValue(gl, 0, 0);
	    renderedTexture.applyTexture(gl,0);
	    
	    screenIndexBufferObject.drawAsIndexedBuffer(gl, GL.GL_TRIANGLES, 2880);
	}
	
	@Override
	public void dispose(GL2ES2 gl) {
		// TODO Auto-generated method stub
		
	}
}
