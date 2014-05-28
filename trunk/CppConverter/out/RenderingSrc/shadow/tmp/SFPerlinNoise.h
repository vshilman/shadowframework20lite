#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "java/nio/ByteBuffer.h"

#include "shadow/image/SFBitmap.h"
#include "shadow/image/SFImageFormat.h"

//abstract class SFPerlinNoise extends SFBitmap {

//	protected float[] weights;
//	protected boolean rgb;

//	SFPerlinNoise() {
//		super();
	}

	
//	void init() {
//		super.init();
//		setup(weights, rgb);
	}

//	protected abstract float getOctaveValue(float u, float v, int octave);

//	protected void setup(float[] weights, boolean rgb) {
//		int width=getWidth();
//		int height=getHeight();

//		if(rgb){
//			this->setFormat(SFImageFormat.RGB8);
		}
//			this->setFormat(SFImageFormat.GRAY8);
		}
		this->weights=weights;

//		int size=1;
//		if(rgb){
//			size=3;
		}
//		ByteBuffer buffer=ByteBuffer.allocateDirect(width*height*size);

//		float stepH=1.0f/height;
//		float stepW=1.0f/width;

//		for(int i=0;i<height;i++){
//			for(int j=0;j<width;j++){

//				float u=stepW*j;
//				float v=stepH*i;

//				float color=0;
//				int oct=0;
//				for(int k=0;k<weights.length;k++){
//					color+=getOctaveValue(u,v,oct)*weights[k];
//					if(k==0){
//						oct=1;
					}
//						oct*=2;
					}
				}
//				if(color>255)
//					color=255;
//				if(color<0)
//					color=0;

//				buffer.put((byte)color); 
//				if(rgb){
//					buffer.put((byte)color);
//					buffer.put((byte)color);
				}
			}
		}
//		this->setWidth(width);
//		this->setHeight(height);
//		this->setData(buffer);
//		buffer.rewind();
	}
}
;
}
#endif
