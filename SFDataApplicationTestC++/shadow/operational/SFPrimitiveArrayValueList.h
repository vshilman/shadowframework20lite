////
////  SFPrimitiveArrayValueList.h
////  
////
////  Created by Alessandro Martinelli on 23/10/12.
////  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
////
//
//#ifndef SFPrimitiveArrayValueList__
//#define SFPrimitiveArrayValueList__
//
//#include "../pipeline/expression/SFExpressionValuesList.h"
//#include "../pipeline/SFPrimitiveArray.h"
//
//class SFPrimitiveArrayValueList : public SFExpressionValuesList{
//
//private:
//	int* primitiveIndices;
//	SFPrimitiveArray* array;
//	int position=0;
//	SFArray<SFValuenf>* arrayData;
//
//public:
//	SFPrimitiveArrayValueList(int* primitiveIndices, SFPrimitiveArray* array) {
//		this->primitiveIndices = primitiveIndices;
//		this->array = array;
//	}
//
//	void setGridIndex(int gridIndex){
//		position=array->getPrimitive()->getIndicesPositions()[gridIndex];
//		arrayData=array->getPrimitiveData(gridIndex);
//	}
//
//	SFValuenf generateValue() {
//		return arrayData->generateSample();
//	}
//
//	int getDataIndex(int index){
//		return primitiveIndices[position+index];
//	}
//
//	SFValuenf getValue(int index) {
//		SFValuenf value=arrayData->generateSample();
//		arrayData->getElement(getDataIndex(index), value);
//        return value;
//	}
//
//};
//
//
//
//#endif /* defined(SFPrimitiveArrayValueList__) */
