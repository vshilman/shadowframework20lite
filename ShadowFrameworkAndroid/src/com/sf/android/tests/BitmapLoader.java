package com.sf.android.tests;

import java.nio.ByteBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class BitmapLoader {
	
	// Will load a texture out of a drawable resource file, and return an OpenGL texture ID:
	public static int loadTexture(GL10 gl, Context context, int resource) {
	    
	    // In which ID will we be storing this texture?
	    int id = newTextureID(gl);
	    
	    // Load up, and flip the texture:
	    Bitmap temp = BitmapFactory.decodeResource(context.getResources(), resource);
	    Bitmap bmp = temp;//Bitmap.createBitmap(temp, 0, 0, temp.getWidth(), temp.getHeight(), true);
	    //temp.recycle();
	    
	    gl.glBindTexture(GL10.GL_TEXTURE_2D, id);
	    
	    // Set all of our texture parameters:
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
	    
	    // Generate, and load up all of the mipmaps:
	    for(int level=0, height = bmp.getHeight(), width = bmp.getWidth(); true && level==0; level++) {
	        // Push the bitmap onto the GPU:
	        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, level, GLES20.GL_RGBA, bmp, GLES20.GL_UNSIGNED_INT, 0);
	        
//	        // We need to stop when the texture is 1x1:
//	        if(height==1 && width==1) break;
//	        
//	        // Resize, and let's go again:
//	        width >>= 1; height >>= 1;
//	        if(width<1)  width = 1;
//	        if(height<1) height = 1;
//	        
//	        Bitmap bmp2 = Bitmap.createScaledBitmap(bmp, width, height, true);
//	        bmp.recycle();
//	        bmp = bmp2;
	    }
	    
	    bmp.recycle();
	    
	    return id;
	}
	
	
	public static int loadTexture2(GL10 gl, Context context, int resource) {
		int id = newTextureID(gl);
	    
	    // Load up, and flip the texture:
	    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), resource);
	    
	    ByteBuffer buffer=ByteBuffer.allocateDirect(bmp.getWidth()*bmp.getHeight()*3);

    	for (int j = 0; j < bmp.getHeight(); j++) {
    		for (int i = 0; i < bmp.getWidth(); i++) {
				int pixel=bmp.getPixel(i, j);
				int red= Color.red(pixel);
				int green= Color.green(pixel);
				int blue= Color.blue(pixel);
				buffer.put((byte)red);
				buffer.put((byte)green);
				buffer.put((byte)blue);
				
			}
		}
	    buffer.position(0);
	    buffer.rewind();

	    gl.glBindTexture(GL10.GL_TEXTURE_2D, id);
	    
	    // Set all of our texture parameters:
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
	    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
	    gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGB, bmp.getWidth(), bmp.getHeight(), 0,
	    		GL10.GL_RGB, GL10.GL_UNSIGNED_BYTE, buffer);

	    bmp.recycle();
	    return id;
	}
	
	
	private static int newTextureID(GL10 gl) {
	    int[] temp = new int[1];
	    gl.glGenTextures(1, temp, 0);
	    return temp[0];        
	}


}

