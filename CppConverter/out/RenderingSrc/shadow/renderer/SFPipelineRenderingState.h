#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "shadow/pipeline/SFPipelineGraphics.StencilFunction.h"
#include "shadow/pipeline/SFPipelineGraphics.StencilOperation.h"

///**
// * @pattern Data_Structure
// * 
// * @author Alessandro Martinelli
// */
class SFPipelineRenderingState {

//	boolean depthTest=true;
//	boolean stencilTest=false;

//	StencilFunction function;
//	int functionValue;
//	int mask;
//	StencilOperation stencilFail;
//	StencilOperation depthFail;
//	StencilOperation depthPass;

//	void setStencilTest(StencilFunction function,int functionValue,int mask,
//			StencilOperation stencilFail,
//			StencilOperation depthFail,StencilOperation depthPass) {
		this->stencilTest = true;
		this->function=function;
		this->functionValue=functionValue;
		this->mask=mask;
		this->stencilFail=stencilFail;
		this->depthFail=depthFail;
		this->depthPass=depthPass;
	}

//	void apply(){
//		//TODO
	}

//	void unapply(){
//		//TODO
	}

//	void disableStencilTest() {
		this->stencilTest = false;
	}

//	void enableDepthTest() {
		this->depthTest = true;
	}

//	void disableDepthTest() {
		this->depthTest = false;
	}

//	boolean isDepthTest() {
//		return depthTest;
	}

//	boolean isStencilTest() {
//		return stencilTest;
	}

//	StencilFunction getFunction() {
//		return function;
	}

//	int getStencilValue() {
//		return functionValue;
	}

//	int getStencilMask() {
//		return mask;
	}

//	StencilOperation getStencilFail() {
//		return stencilFail;
	}

//	StencilOperation getDepthFail() {
//		return depthFail;
	}

//	StencilOperation getDepthPass() {
//		return depthPass;
	}


}
;
}
#endif
