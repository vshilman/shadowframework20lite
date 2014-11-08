//  SFRenderedTexture.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFRenderedTexture.h"

namespace sf{


SFRenderedTexture::SFRenderedTexture() {
	this->colorsData=0;
	this->colorsDataLength=0;
	this->depthBuffer=0;
	this->stencilBuffer=0;
}


void SFRenderedTexture::setDepthBuffer(SFBufferData* depthBuffer) {
    this->depthBuffer = depthBuffer;
}

void SFRenderedTexture::setStencilBuffer(SFBufferData* stencilBuffer) {
    this->stencilBuffer = stencilBuffer;
}

void SFRenderedTexture::setColorData(SFBufferData** colorData,int length) {
    this->colorsData=colorData;
    this->colorsDataLength=length;
}

SFBufferData* SFRenderedTexture::getDepthBuffer() {
    return depthBuffer;
}

SFBufferData* SFRenderedTexture::getStencilBuffer() {
    return stencilBuffer;
}

SFBufferData** SFRenderedTexture::getColorsData() {
    return colorsData;
}

int SFRenderedTexture::getColorsDataLength() {
    return colorsDataLength;
}

}
