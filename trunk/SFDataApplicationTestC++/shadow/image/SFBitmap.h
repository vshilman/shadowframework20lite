
#ifndef SFBITMAP_H
#define SFBITMAP_H


#include "stdlib.h"
#include "SFImageFormat.h"
#include "SFInitiable.h"


namespace sf{

class SFBitmap : public SFInitiable{
	
private:
    
	/** width of the image in pixels*/
	int width;
	/** height of the image in pixels*/
	int height;
	/** format of the image*/
	SFImageFormat format;
	
	/** data type depends on Image generation mode*/
	void* data;
    
public:
    
	SFBitmap();
	
	/**
	 * Generate an image with the desidered with and height.
	 * Default format is RGB.
	 * @param width
	 * @param height
	 * @return the image being generated.
	 */
	static SFBitmap* generateRGBImage(int width,int height);
	
	/**
	 * Generate an image with the desidered with and height.
	 * @param width width of the image
	 * @param height height of the image
	 * @param format format of the image
	 * @return the image being generated.
	 */
	static SFBitmap* generateRGBImage(int width,int height,SFImageFormat format);
    
	int getWidth();
	
	int getGray(int x,int y);
    
	void setWidth(int width);
    
	int getHeight();
    
	void setHeight(int height);
    
	SFImageFormat getFormat();
    
	void setFormat(SFImageFormat format);
    
	void*  getData();
    
	void setData(void* data);
	
	void init();
	
	void destroy();
    
	int getSize();
    
	void generateBitmap(int width, int height, int* values, bool rgb);
};

}

#endif /* SFBITMAP_H */
