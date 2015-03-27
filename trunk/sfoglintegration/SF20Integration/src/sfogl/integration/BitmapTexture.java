package sfogl.integration;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sfogl2.SFOGLTexture2D;
import shadow.graphics.SFBitmap;
import shadow.graphics.SFImageFormat;
import shadow.system.SFInitiable;

public class BitmapTexture implements SFInitiable {

	private SFOGLTexture2D texture;
	private SFBitmap bitmap;
	private int textureModel;
	
	public BitmapTexture(SFBitmap bitmap,int textureModel) {
		super();
		this.bitmap = bitmap;
		this.textureModel=textureModel;
	}

	public SFOGLTexture2D getTexture() {
		return texture;
	}

	public void setTexture(SFOGLTexture2D texture) {
		this.texture = texture;
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init() {
		texture=new SFOGLTexture2D(textureModel);
		texture.setup(SFGL2.getGL(), bitmap);
	}


    public static BitmapTexture loadBitmapTexture(String name,int textureModel){

    	try {
			BufferedImage image=ImageIO.read(new File(name));
			
			int width = image.getWidth();
			int height = image.getHeight();
			
			int[] values=new int[width*height*3];
			int index=0;
			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {
					int color=image.getRGB(i, j);
					values[index]=(color & 0xff0000)>>16;
					index++;
					values[index]=(color & 0xff00)>>8;
					index++;
					values[index]=(color & 0xff);
					index++;
				}	
			}
			
			SFBitmap bitmap=new SFBitmap();
			bitmap.generateBitmap(width, height, values, SFImageFormat.RGB);
			return new BitmapTexture(bitmap,textureModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
}
