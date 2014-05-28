#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/system/SFResource.h"

///**
// * Responsibility: draw.
// * 
// * @author Alessandro Martinelli
// */
class SFPointsCloud  extends SFGeometry {

//	protected SFPrimitive primitive;
	Array array;
//	int firstPoint;
//	int pointsSize;
//	int compiledIndex=-1;
//	SFResource resource=new SFResource(0);

//	SFPointsCloud() {
//		super();
	}

//	void setPrimitive(SFPrimitive primitive) {
		this->primitive=primitive;
	}

	
//	SFPrimitive getPrimitive() {
//		return primitive;
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	void drawGeometry(int lod) {
//		//lod is still ignored
//		//this is much ok...
//		if(compiledIndex!=-1)
//			SFPipeline.getSfPipelineGraphics().drawCompiledPointsCloud(getArray(), compiledIndex);
//		else if(firstPoint!=-1)
//			SFPipeline.getSfPipelineGraphics().drawPointsCloud(array,firstPoint,pointsSize);
	}

	
//	void compile() {
	}

	
//	void update() {
//		//Do nothing
	}


	
//	void decompile() {
//		SFPrimitiveArray array=getArray();
//		SFPipeline.getSfPipelineMemory().destroyPrimitiveArray(array);
	}

	
//	void init() {
//		if(firstPoint==-1){

//			verifyArray();

//			firstPoint=(getArray().getElementsCount());
//			int[] rangePositions=new int[getPrimitive().getGridsCount()];
//			for (int i = 0; i < rangePositions.length; i++) {
//				rangePositions[i]=getArray().getPrimitiveData(i).getElementsCount();
			}

//			compile();

//			pointsSize=(getArray().getElementsCount())-firstPoint;
//			SFIndexRange[] ranges=new SFIndexRange[getPrimitive().getGridsCount()];
//			for (int i = 0; i < rangePositions.length; i++) {
//				int rangePosition=getArray().getPrimitiveData(i).getElementsCount();
//				ranges[i]=new SFIndexRange(rangePositions[i], rangePosition-rangePositions[i]);
			}

//			update();

//			compiledIndex=SFPipeline.getSfPipelineGraphics().compilePrimitiveArray(
//					getArray(),getFirstPoint(), getFirstPoint()+getPointsSize());

		}
	}

//	void verifyArray() {
//		if(getArray()==null)			
//			setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(getPrimitive()));
	}

	
//	void destroy() {
//		if(firstPoint!=-1){
//			decompile();	
		}
	}

//	protected SFPointsCloud(SFPrimitive primitive) {
//		super();
		this->primitive=primitive;
	}

//	SFPrimitiveArray getArray() {
//		return array;
	}

//	void setArray(SFPrimitiveArray array) {
		this->array = array;
	}

//	int getFirstPoint() {
//		return firstPoint;
	}

//	void setFirstPoint(int firstPoint) {
		this->firstPoint = firstPoint;
	}

//	int getPointsSize() {
//		return pointsSize;
	}

//	void setPointsSize(int pointsSize) {
		this->pointsSize = pointsSize;
	}

}
;
}
#endif
