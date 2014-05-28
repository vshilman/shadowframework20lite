#ifndef shadow_image_H_
#define shadow_image_H_

#include "java/nio/ByteBuffer.h"

#include "shadow/geometry/SFSurfaceFunction.h"

class SFSurfaceFunctionBitmap extends SFBitmap{

//	SFSurfaceFunction function;

//	SFSurfaceFunctionBitmap(int width,int height,boolean rgb,SFSurfaceFunction function){
//		super();
//		setWidth((short)width);
//		setHeight((short)height);
		this->function=function;

//		if(rgb){
//			this->setFormat(SFImageFormat.RGB8);
		}
//			this->setFormat(SFImageFormat.GRAY8);
		}

	}

//	SFSurfaceFunction getFunction() {
//		return function;
	}

//	void setFunction(SFSurfaceFunction function) {
		this->function = function;
	}

	
//	void init() {
//		super.init();

//		int width=getWidth();
//		int height=getHeight();

//		int size = getSize();
//		ByteBuffer buffer=ByteBuffer.allocateDirect(width*height*size);

//		float stepH=1.0f/height;
//		float stepW=1.0f/width;

//		for(int i=0;i<height;i++){
//			for(int j=0;j<width;j++){

//				float u=stepW*j;
//				float v=stepH*i;

//				function.getX(u,v);
//				float color=255*function.getZ(u,v);
//				System.err.println("function color  "+color+" "+u+" "+v);
//				if(color>255)
//					color=255;
//				if(color<0)
//					color=0;

//				buffer.put((byte)color); 
//				if(size==3){
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
