//
//  SFGL20PipelineGraphics.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20PipelineGraphics__
#define SFGL20PipelineGraphics__

#include "../SFPipelineGraphics.h"
#include "SFGL20GenericProgram.h"
#include "SFGL20PrimitiveArray.h"
#include "SFGL20StructureArray.h"
#include "SFGL20Program.h"
#include "../../system/SFInitiable.h"
#include "../../system/SFInitiator.h"


namespace sf{
class SFGL20PipelineGraphics : public SFPipelineGraphics, SFInitiable {
    
	static const int N=20;
	static const int LINES_WIDTH=1;
	
    //You will not handle things this way...
	//vector<SFVertex2f> notExactlyCompiledPrimitives=new ArrayList<SFVertex2f>();
	//ArrayList<SFVertex2f> notExactlyCompiledPointsCloud=new ArrayList<SFVertex2f>();
	
	static SFGL20GenericProgram* program;
	static float* projection;
	static float* transform;
	
	static int baseQuadList;
	//TODO : should have all the tessellations...
	static int baseTriangleList;
	static int baseLineList;
	static int baseQuadsList;
	
public:
    
	SFGL20PipelineGraphics();
	
	static void setProgram(SFGL20GenericProgram* program);
    
	
	static void staticSetupTransform(float* transform);

	void setLod(int lod);
	
	void setupTransform(float transform[16]);
    
	
	float* getTransform();
	
	
	float* getProjection();
	
	int compilePointsCloud(SFPrimitiveArray* array, int firstElement, int lastElement);
	
	
	int compilePrimitiveArray(SFPrimitiveArray* array, int firstElement, int lastElement);
	
	
	void drawCompiledPointsCloud(SFPrimitiveArray* primitives, int compiledGeometry);
    
	
	void drawCompiledPrimitives(SFPrimitiveArray* primitives, int compiledGeometry);
	
	
	void updateCompiledPointsCloud(SFPrimitiveArray* array, int compiled);
	
	
	void updateCompiledPrimitive(SFPrimitiveArray* array, int compiled);
	
	
	void drawPrimitives(SFPrimitiveArray* primitives, int first, int count);

    
	void updateTransforms();
	
	
	void drawPointsCloud(SFPrimitiveArray* primitives, int first,
                                int count);
    
	
	void loadStructureData(Module module, SFStructureArray* array, int inProgramIndex,
                                  int indexOfData);
	
	
	void loadTexture(Module module, SFPipelineTexture* texture, int indexOfTexture);
	
	
	void setupProjection(float projection[16]);
    
	
	void drawBaseQuad();
	
	
	void init();
	
	
	void destroy();
	
	void setPipelineState(void* state);
	

	void pushBlendMode(BlendMode blendMode);

	void popBlendMode();
};
}



#endif /* defined(SFGL20PipelineGraphics__) */
