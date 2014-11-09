//
//  SFBitmap.cpp
//  
//
//  Created by Alessandro Martinelli on 17/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFBitmap.h"

namespace sf{
    
	SFBitmap::SFBitmap() {
		data=0;
		width=0;
		height=0;
		format=ARGB8;
	}
	
	SFBitmap* SFBitmap::generateRGBImage(int width,int height){
		SFBitmap* ret=new SFBitmap();
		ret->setWidth(width);
		ret->setHeight(height);
		return ret;
	}
	
	SFBitmap* SFBitmap::generateRGBImage(int width,int height,SFImageFormat format){
		SFBitmap* ret=new SFBitmap();
		ret->setWidth(width);
		ret->setHeight(height);
		ret->setFormat(format);
		return ret;
	}
    
	int SFBitmap::getWidth() {
		return width;
	}
	
	int SFBitmap::getGray(int x,int y){
		char b= (((char*)data)[getSize()*(x+width*y)]);
		return (b>=0?b:b+256);
	}
    
	void SFBitmap::setWidth(int width) {
		this->width = width;
	}
    
	int SFBitmap::getHeight() {
		return height;
	}
    
	void SFBitmap::setHeight(int height) {
		this->height = height;
	}
    
	SFImageFormat SFBitmap::getFormat() {
		return format;
	}
    
	void SFBitmap::setFormat(SFImageFormat format) {
		this->format = format;
	}
    
	void*  SFBitmap::getData() {
		return data;
	}
    
	void SFBitmap::setData(void* data) {
		this->data = data;
	}
	
	void SFBitmap::init() {
		//TODO SFBitmap does not have an init implementation, is that generally correct?
	}
	
	void SFBitmap::destroy() {
		//TODO SFBitmap does not have a destroy implementation, is that generally correct?
	}
    
	int SFBitmap::getSize() {
		int size=1;
		if(getFormat()==RGB8){
			size=3;
		}
		return size;
	}
    
	void SFBitmap::generateBitmap(int width, int height, int* values, bool rgb) {
		setWidth(width);
		setHeight(height);
		if(rgb){
			setFormat(RGB8);
		}else{
			setFormat(GRAY8);
		}
		
        //		if(width*height!=values.length)
        //			throw new SFException("An SFBitmapArrayData must have an array of "+(width*height)+" values, there are "+values.length+"values");
		
		int size=1;
		if(rgb){
			size=3;
		}

		char* buffer=new char[width*height*size];
		
		for (int i = 0; i < width*height*size; i++) {
			((char*)buffer)[i]=(char)values[i];
		}
		
		setData(buffer);
		
	}
}

//TODO
