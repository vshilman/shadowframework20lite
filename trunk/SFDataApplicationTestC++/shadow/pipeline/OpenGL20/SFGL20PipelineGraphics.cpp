//
//  SFGL20PipelineGraphics.cpp
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFGL20PipelineGraphics.h"
#include "SFGL20Blend.h"

namespace sf{

	SFGL20GenericProgram* SFGL20PipelineGraphics::program;
	float* SFGL20PipelineGraphics::projection;
	float* SFGL20PipelineGraphics::transform;

	int SFGL20PipelineGraphics::baseQuadList;
	//TODO : should have all the tessellations...
	int SFGL20PipelineGraphics::baseTriangleList;
	int SFGL20PipelineGraphics::baseLineList;
	int SFGL20PipelineGraphics::baseQuadsList;

	SFGL20Blend blend;

	SFGL20PipelineGraphics::SFGL20PipelineGraphics(){
        SFInitiator::addInitiable(this);
	}

	void SFGL20PipelineGraphics::setProgram(SFGL20GenericProgram* program) {
        SFGL20PipelineGraphics::program=program;
	}


	void SFGL20PipelineGraphics::staticSetupTransform(float* transform) {
        SFGL20PipelineGraphics::transform=transform;
	}

	void SFGL20PipelineGraphics::setLod(int lod){
		//TODO
	}

	void SFGL20PipelineGraphics::setupTransform(float transform[16]) {
		staticSetupTransform(transform);
	}


	float* SFGL20PipelineGraphics::getTransform() {
		return SFGL20PipelineGraphics::transform;
	}


	float* SFGL20PipelineGraphics::getProjection() {
		return SFGL20PipelineGraphics::projection;
	}

	int SFGL20PipelineGraphics::compilePointsCloud(SFPrimitiveArray* array, int firstElement, int lastElement) {
		//notExactlyCompiledPointsCloud.add(new SFVertex2f(firstElement, lastElement));
		//return notExactlyCompiledPointsCloud.size()-1;
        return 0;
	}


	int SFGL20PipelineGraphics::compilePrimitiveArray(SFPrimitiveArray* array, int firstElement, int lastElement) {
		//notExactlyCompiledPrimitives.add(new SFVertex2f(firstElement, lastElement));
		//return notExactlyCompiledPrimitives.size()-1;
        return 0;
	}


	void SFGL20PipelineGraphics::drawCompiledPointsCloud(SFPrimitiveArray* primitives, int compiledGeometry) {
		//SFVertex2f v=notExactlyCompiledPointsCloud.get(compiledGeometry);
		//drawPointsCloud(primitives, (int)v.getX(), (int)v.getY());
	}


	void SFGL20PipelineGraphics::drawCompiledPrimitives(SFPrimitiveArray* primitives, int compiledGeometry) {
		//SFVertex2f v=notExactlyCompiledPrimitives.get(compiledGeometry);
		//drawPrimitives(primitives, (int)v.getX(), (int)v.getY());
	}


	void SFGL20PipelineGraphics::updateCompiledPointsCloud(SFPrimitiveArray* array, int compiled) {
		// TODO Auto-generated method stub
	}


	void SFGL20PipelineGraphics::updateCompiledPrimitive(SFPrimitiveArray* array, int compiled) {
		// TODO Auto-generated method stub
	}


	void SFGL20PipelineGraphics::drawPrimitives(SFPrimitiveArray* primitives, int first, int count) {

		//long time=System.nanoTime();

		updateTransforms();

		SFGL20PrimitiveArray* prArray=(SFGL20PrimitiveArray*) primitives;

		SFPrimitive* primitive=primitives->getPrimitive();

		//int list = getList(primitive);

		for (int i=first; i < count + first; i++) {

			SFPrimitiveIndices* indices=prArray->getValue(i);

			((SFGL20Program*)program)->setIndexedData(*indices,prArray->getPrimitiveData(),primitive);

            //TODO: this is really to be reworked
			//GL2 gl=SFgetGL();
			//glCallList(list);
		}

		//long time02=System.nanoTime();

		//System.err.println("Time "+((time02-time)*0.001*0.001)+" ms");
	}

//    int getList(SFPrimitive* primitive) {
//		int list=baseTriangleList;
//		if(primitive->getGridModel()==SFGridModel::Quad)
//			list=baseQuadsList;
//		else if(primitive->getGridModel()==SFGridModel::Line)
//			list=baseLineList;
//		return list;
//	}

	void SFGL20PipelineGraphics::updateTransforms() {
		((SFGL20Program*)program)->setTransformData(transform);

		if(projection!=0)
			((SFGL20Program*)program)->setupProjection(projection);
	}


	void SFGL20PipelineGraphics::drawPointsCloud(SFPrimitiveArray* primitives, int first,
                                int count) {
		updateTransforms();

		//SFGL20PrimitiveArray* prArray=(SFGL20PrimitiveArray*) primitives;

		//SFPrimitive* primitive=primitives->getPrimitive();

		//int list = getList(primitive);

		SFArray<SFValue>* positions=primitives->getPrimitiveData(0);

		SFValue* value=positions->generateSample();

		for (int i=first; i < count + first; i++) {

			positions->getElement(i, value);

			//((SFGL20Program*)program).sendVertex(value);

			//glCallList(list);
		}
	}


	void SFGL20PipelineGraphics::loadStructureData(Module module, SFStructureArray* array, int inProgramIndex,
                                  int indexOfData) {
		SFGL20StructureArray* strArray=(SFGL20StructureArray*) array;

		program->setData(module,inProgramIndex,strArray->getValue(indexOfData));
	}


	void SFGL20PipelineGraphics::loadTexture(Module module, SFPipelineTexture* texture, int indexOfTexture) {
		program->setTextureData(module,indexOfTexture,texture);
	}


	void SFGL20PipelineGraphics::setupProjection(float projection[16]) {
        SFGL20PipelineGraphics::projection=projection;
	}


	void SFGL20PipelineGraphics::drawBaseQuad() {

        //TODO
		//GL2 gl=SFgetGL();

		//glCallList(baseQuadList);
	}


	void SFGL20PipelineGraphics::init() {

		/*GL2 gl=SFgetGL();

		baseTriangleList=glGenLists(1);
		baseQuadsList=glGenLists(1);
		baseQuadList=glGenLists(1);
		baseLineList=glGenLists(1);

		int N=SFGL20PipelineGraphics.N;
		float step=1.0f / N;

		glNewList(baseTriangleList, GL_COMPILE);
        for (int k=0; k < N; k++) {
            float v1=k * step;
            float v2=v1 + step;
            glBegin(GL_TRIANGLE_STRIP);
            for (int j=0; j < N - k; j++) {
                float u=j * step;
                glVertex3f(u,v1,1 - u - v1);
                glVertex3f(u,v2,1 - u - v2);
            }
            float u=(N - k) * step;
            glVertex3f(u,v1,1 - u - v1);
            glEnd();
        }
		glEndList();

		glNewList(baseQuadsList, GL_COMPILE);
        for (int k=0; k < N; k++) {

            float v1=k * step;
            float v2=v1 + step;
            glBegin(GL_TRIANGLE_STRIP);
            for (int j=0; j <= N ; j++) {
                float u=j * step;
                glVertex3f(u,v1,1 - u - v1);
                glVertex3f(u,v2,1 - u - v2);
            }
            glEnd();
        }
		glEndList();

		glNewList(baseQuadList, GL_COMPILE);
        glBegin(GL_TRIANGLE_STRIP);
        glVertex2f(-1,-1);
        glVertex2f(1,-1);
        glVertex2f(-1,1);
        glVertex2f(1,1);
        glEnd();
		glEndList();


		N=N*N;
		step=1.0f / N;
		glNewList(baseLineList, GL_COMPILE);
        glLineWidth(LINES_WIDTH);
        glBegin(GL_LINE_STRIP);
        for (int k=0; k <= N; k++) {
            float t=k * step;
            glVertex3f(t,0,0);
        }
        glEnd();
        glLineWidth(1);
		glEndList();*/

	}


	void SFGL20PipelineGraphics::destroy() {

		//glDeleteLists(baseQuadsList, 1);
		//glDeleteLists(baseQuadList, 1);
		//glDeleteLists(baseTriangleList, 1);
		//glDeleteLists(baseLineList, 1);
	}

//    int SFGL20PipelineGraphics::getStencilFunc(SFPipelineRenderingState::StencilFunction function){
//		switch (function) {
//			case SFPipelineRenderingState::ALWAYS: return GL_ALWAYS;
//			case SFPipelineRenderingState::EQUAL: return GL_EQUAL;
//			case SFPipelineRenderingState::GEQUAL: return GL_GEQUAL;
//			case SFPipelineRenderingState::GREATER: return GL_GREATER;
//			case SFPipelineRenderingState::LEQUAL: return GL_LEQUAL;
//			case SFPipelineRenderingState::LESS: return GL_LESS;
//			case SFPipelineRenderingState::NEVER: return GL_NEVER;
//			case SFPipelineRenderingState::NOTEQUAL: return GL_NOTEQUAL;
//		}
//		return GL_ALWAYS;
//	}
//
//    int SFGL20PipelineGraphics::getStencilOp(SFPipelineRenderingState::StencilOperation operation){
//		switch (operation) {
//			case SFPipelineRenderingState::DECR: return GL_DECR;
//			case SFPipelineRenderingState::DECR_WRAP: return GL_DECR_WRAP;
//			case SFPipelineRenderingState::INCR: return GL_INCR;
//			case SFPipelineRenderingState::INCR_WRAP: return GL_INCR_WRAP;
//			case SFPipelineRenderingState::INVERT: return GL_INVERT;
//			case SFPipelineRenderingState::KEEP: return GL_KEEP;
//			case SFPipelineRenderingState::REPLACE: return GL_REPLACE;
//			case SFPipelineRenderingState::ZERO: return GL_ZERO;
//		}
//		return GL_ALWAYS;
//	}
//
//
//	void SFGL20PipelineGraphics::setPipelineState(void* pointer) {
//
//		SFPipelineRenderingState state=*((SFPipelineRenderingState*)pointer);
//
//		if(state.isDepthTest()){
//			glEnable(GL_DEPTH_TEST);
//		}else{
//			glDisable(GL_DEPTH_TEST);
//		}
//		if(state.isStencilTest()){
//			glEnable(GL_STENCIL_TEST);
//			//THIS IS GONNA TO BE WIDELY CHECKED
////			glStencilFunc(getStencilFunc(state.getFunction()), state.getStencilValue(), state.getStencilMask());
////			glStencilOp(getStencilOp(state.getStencilFail()), getStencilOp(state.getDepthFail()), getStencilOp(state.getDepthPass()));
//		}else{
//			glDisable(GL_STENCIL_TEST);
//		}
//	}
//
//
//
//	void SFGL20PipelineGraphics::executeAccumulationOperation(SFPipelineRenderingState::AccumulatorOperation operation,
//                                             float value) {
//
//		//int accumOperation=-1;
//
//        //Accumulator not available....
////
////		switch (operation) {
////			case SFPipelineRenderingState::AccumulatorOperation::ACCUM: accumOperation=GL_ACCUM;
////			case SFPipelineRenderingState::AccumulatorOperation::ADD: accumOperation=GL_ADD;
////			case SFPipelineRenderingState::AccumulatorOperation::LOAD: accumOperation=GL_LOAD;
////			case SFPipelineRenderingState::AccumulatorOperation::MULT: accumOperation=GL_MULT;
////			case SFPipelineRenderingState::AccumulatorOperation::RETURN: accumOperation=GL_RETURN;
////		}
////
////		glAccum(accumOperation, value);
//	}


	void SFGL20PipelineGraphics::pushBlendMode(SFPipelineGraphics::BlendMode blendMode) {

		blend.pushBlend(blendMode);
	}

	void SFGL20PipelineGraphics::popBlendMode() {
		blend.popBlend();
	}
}
