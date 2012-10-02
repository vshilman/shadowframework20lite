/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.image.bitmaps;

import java.nio.ByteBuffer;

import shadow.image.SFBitmap;
import shadow.image.SFImageFormat;

public class SFFunction2DBitmap extends SFBitmap{
	
	private SFBitmapFunction function;

	public SFFunction2DBitmap(int width,int height,boolean rgb,SFBitmapFunction function){
		super();
		setWidth((short)width);
		setHeight((short)height);
		this.function=function;

		if(rgb){
			this.setFormat(SFImageFormat.RGB8);
		}else{
			this.setFormat(SFImageFormat.GRAY8);
		}
		
	}
	
	public SFBitmapFunction getFunction() {
		return function;
	}

	public void setFunction(SFBitmapFunction function) {
		this.function = function;
	}

	@Override
	public void init() {
		super.init();

		int width=getWidth();
		int height=getHeight();
		
		int size = getSize();
		ByteBuffer buffer=ByteBuffer.allocateDirect(width*height*size);
		
		float stepH=1.0f/height;
		float stepW=1.0f/width;
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				
				float u=stepW*j;
				float v=stepH*i;
				
				float color=255*function.getValue(u,v);
				if(color>255)
					color=255;
				if(color<0)
					color=0;
				
				buffer.put((byte)color); 
				if(size==3){
					buffer.put((byte)color);
					buffer.put((byte)color);
				}
			}
		}
		this.setWidth(width);
		this.setHeight(height);
		this.setData(buffer);
		
		buffer.rewind();
	}
}
