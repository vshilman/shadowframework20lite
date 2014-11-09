//
//  SFPipelineRenderingState.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineRenderingState__
#define SFPipelineRenderingState__

namespace sf{

class SFPipelineRenderingState {
    
public:
	enum StencilFunction{
        NEVER,
        LESS,
        LEQUAL,
        GREATER,
        GEQUAL,
        EQUAL,
        NOTEQUAL,
        ALWAYS
	};
	
	enum StencilOperation{
        KEEP,
        ZERO,
        REPLACE,
        INCR,
        INCR_WRAP,
        DECR,
        DECR_WRAP,
        INVERT
	};
	
	
private:
	bool depthTest;
    bool stencilTest;
	
    StencilFunction function;
    int functionValue;
    int mask;
	StencilOperation stencilFail;
	StencilOperation depthFail;
	StencilOperation depthPass;
	
public:
	void setStencilTest(SFPipelineRenderingState::StencilFunction function,int functionValue,int mask,
			SFPipelineRenderingState::StencilOperation stencilFail,
			SFPipelineRenderingState::StencilOperation depthFail,SFPipelineRenderingState::StencilOperation depthPass);
	
	void apply();

	void unapply();

	void disableStencilTest();
    
	void enableDepthTest();
	
	void disableDepthTest();
	
	bool isDepthTest();
	
	bool isStencilTest();
    
	SFPipelineRenderingState::StencilFunction getFunction();
    
	int getStencilValue();
    
	int getStencilMask();

	SFPipelineRenderingState::StencilOperation getStencilFail();
    
	SFPipelineRenderingState::StencilOperation getDepthFail();
    
	SFPipelineRenderingState::StencilOperation getDepthPass();
	


};

}


#endif /* defined(SFPipelineRenderingState__) */
