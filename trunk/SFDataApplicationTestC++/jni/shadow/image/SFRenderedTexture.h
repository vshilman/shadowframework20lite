//
//  SFRenderedTexture.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFRenderedTexture_H
#define SFRenderedTexture_H

#include "SFBufferData.h"
#include "SFImageFormat.h"
#include <vector>

using namespace std;

namespace sf{

class SFRenderedTexture {
    
private:
	SFBufferData* depthBuffer;
	SFBufferData* stencilBuffer;
	SFBufferData** colorsData;
	int colorsDataLength;

public:
	SFRenderedTexture();
    
	void setDepthBuffer(SFBufferData* depthBuffer);
    
	void setStencilBuffer(SFBufferData* stencilBuffer);
    
	void setColorData(SFBufferData** colorData,int length);
    
	SFBufferData* getDepthBuffer();
    
	SFBufferData* getStencilBuffer();
    
	SFBufferData** getColorsData();

	int getColorsDataLength();
	
};

}

#endif /* defined(SFRenderedTexture__) */
