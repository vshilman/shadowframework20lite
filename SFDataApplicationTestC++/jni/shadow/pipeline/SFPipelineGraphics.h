//
//  SFPipelineGraphics.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFPipelineGraphics_h
#define _SFPipelineGraphics_h

#include "SFPrimitiveArray.h"
#include "SFPipelineTexture.h"
#include "SFStructureArray.h"


namespace sf{

	class SFPipelineGraphics {

	public:

		enum Module{
			TRANSFORM,
			MATERIAL,
			LIGHT
		};

		enum BlendMode{
	        NONE,
	        COLORSUM,
	        ALPHABLEND
		};

	    virtual ~SFPipelineGraphics(){};

		virtual void setupProjection(float* projection)=0;

		virtual void setupTransform(float* transform)=0;

		virtual float* getProjection()=0;

		virtual void setLod(int lod)=0;

		virtual float* getTransform()=0;

		virtual void drawCompiledPrimitives(SFPrimitiveArray* primitives,int compiledGeometry)=0;

		virtual void drawCompiledPointsCloud(SFPrimitiveArray* primitives,int compiledGeometry)=0;

		virtual void drawPrimitives(SFPrimitiveArray* primitives,int first,int count)=0;

		virtual void drawPointsCloud(SFPrimitiveArray* primitives,int first,int count)=0;

		virtual int compilePrimitiveArray(SFPrimitiveArray* array,int firstElement,int lastElement)=0;

		virtual int compilePointsCloud(SFPrimitiveArray* array,int firstElement,int lastElement)=0;

		virtual void updateCompiledPrimitive(SFPrimitiveArray* array,int compiled)=0;

		virtual void updateCompiledPointsCloud(SFPrimitiveArray* array,int compiled)=0;

		virtual void loadStructureData(Module module,SFStructureArray* array, int inProgramIndex ,int indexOfData)=0;

		virtual void loadTexture(Module module,SFPipelineTexture* texture ,int indexOfTexture)=0;

		virtual void drawBaseQuad()=0;

		virtual void pushBlendMode(BlendMode blendMode)=0;

		virtual void popBlendMode()=0;

		//virtual void executeAccumulationOperation(SFPipelineRenderingState::AccumulatorOperation operation,float value)=0;

	};

}

#endif
