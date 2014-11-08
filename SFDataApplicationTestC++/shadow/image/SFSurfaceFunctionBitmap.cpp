
#include "SFSurfaceFunctionBitmap.h"

namespace sf{


	SFSurfaceFunctionBitmap::SFSurfaceFunctionBitmap(int width,int height,bool rgb,SFSurfaceFunction* function){
		setWidth((short)width);
		setHeight((short)height);
		this->function=function;

		if(rgb){
				this->setFormat(RGB8);
		}else{
				this->setFormat(GRAY8);
		}

	}

	SFSurfaceFunction* SFSurfaceFunctionBitmap::getFunction() {
		return function;
	}

	void SFSurfaceFunctionBitmap::setFunction(SFSurfaceFunction* function) {
		this->function = function;
	}


	void SFSurfaceFunctionBitmap::init() {
		int width=getWidth();
		int height=getHeight();

		int size=1;
		if(this->getFormat()==RGB8){
			size=3;
		}

		char* buffer=new char[width*height*size];

			float stepH=1.0f/height;
			float stepW=1.0f/width;

			unsigned int i=0;
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){

					float u=stepW*j;
					float v=stepH*i;

					function->getX(u,v);
					float color=255*function->getZ(u,v);
					if(color>255)
						color=255;
					if(color<0)
						color=0;
					for(int k=0;k<size;k++){
						((char*)buffer)[i]=(char)color;
						i++;
					}
			}
		}

		setData(buffer);
	}

}
