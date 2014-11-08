//
//  SFPipelineRenderingState.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFPipelineRenderingState.h"


namespace sf{

void SFPipelineRenderingState::setStencilTest(StencilFunction function,int functionValue,int mask,
                    StencilOperation stencilFail,
                    StencilOperation depthFail,StencilOperation depthPass) {
    this->stencilTest = true;
    this->function=function;
    this->functionValue=functionValue;
    this->mask=mask;
    this->stencilFail=stencilFail;
    this->depthFail=depthFail;
    this->depthPass=depthPass;
}

void SFPipelineRenderingState::apply() {
    //TODO
}

void SFPipelineRenderingState::unapply() {
    //TODO
}

void SFPipelineRenderingState::disableStencilTest() {
    this->stencilTest = false;
}

void SFPipelineRenderingState::enableDepthTest() {
    this->depthTest = true;
}

void SFPipelineRenderingState::disableDepthTest() {
    this->depthTest = false;
}

bool SFPipelineRenderingState::isDepthTest() {
    return depthTest;
}

bool SFPipelineRenderingState::isStencilTest() {
    return stencilTest;
}

SFPipelineRenderingState::StencilFunction SFPipelineRenderingState::getFunction() {
    return function;
}

int SFPipelineRenderingState::getStencilValue() {
    return functionValue;
}

int SFPipelineRenderingState::getStencilMask() {
    return mask;
}

SFPipelineRenderingState::StencilOperation SFPipelineRenderingState::getStencilFail() {
    return stencilFail;
}

SFPipelineRenderingState::StencilOperation SFPipelineRenderingState::getDepthFail() {
    return depthFail;
}

SFPipelineRenderingState::StencilOperation SFPipelineRenderingState::getDepthPass() {
    return depthPass;
}

}
