package shadow.integration.data;

import javax.media.opengl.GL2;

import sfogl.integration.BitmapTexture;
import sfogl2.SFOGLTextureModel;
import shadow.graphics.SFImageFormat;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class BitmapTextureData extends SFDataAsset<BitmapTexture>{
	
	public static int index = SFOGLTextureModel.generateTextureObjectModel(SFImageFormat.RGB, 
			GL2.GL_REPEAT, GL2.GL_REPEAT, GL2.GL_LINEAR_MIPMAP_LINEAR, GL2.GL_LINEAR);

	public BitmapTextureData() {
		setName("BitmapTexture");
		addObject("filename", new SFString(""));
	}
	
	@Override
	public BitmapTexture createResource(SFContext context,SFNamedParametersObject sfDataObject) {

		SFString name =sfDataObject.getDataObject(0);
		
		BitmapTexture bitmapTexture = BitmapTexture.loadBitmapTexture(name.getString(),index);
		
		context.getEngine().addInitiable(bitmapTexture);
		
		return bitmapTexture;
	}
	
}
