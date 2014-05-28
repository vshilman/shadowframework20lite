#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/pipeline/SFPrimitiveIndices.h"
#include "shadow/system/SFArray.h"
#include "shadow/system/SFException.h"

class SFDerivedTexCoord extends SFMeshGeometry{

//	SFMeshGeometry geometry=new SFMeshGeometry();
//	SFDerivedTexCoordFunctionuv derivedTexCoordFunction;

//	SFDerivedTexCoord() {
//		// TODO Auto-generated constructor stub
	}

//	void addMeshGeometry(SFMeshGeometry geometry) throws SFException {
		this->geometry=geometry;
	}

//	void setDerivedTexCoordFunction(
//			SFDerivedTexCoordFunctionuv derivedTexCoordFunction) {
		this->derivedTexCoordFunction = derivedTexCoordFunction;
	}

	
//	void compile() {
//		super.compile();

//		//TODO: verify this have already 1 element.

//		int positionGridIndex=0;
//		int normalGridIndex=0;

//		SFPrimitive primitive=geometry.getPrimitive();
//		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
//			if(primitive.getBlock(gridIndex)==SFPrimitiveBlock.POSITION){
//				positionGridIndex=gridIndex;
			}
//			if(primitive.getBlock(gridIndex)==SFPrimitiveBlock.NORMAL){
//				normalGridIndex=gridIndex;
			}
		}

//		SFIndexRange rangePosition=geometry.getMesh().getPrimitiveDataRanges()[positionGridIndex];
//		SFIndexRange rangeNormal=geometry.getMesh().getPrimitiveDataRanges()[normalGridIndex];
//		SFArray<SFValuenf> positionData=geometry.getArray().getPrimitiveData(positionGridIndex);
//		SFArray<SFValuenf> normalData=geometry.getArray().getPrimitiveData(normalGridIndex);
//		SFArray<SFValuenf> data=getArray().getPrimitiveData(0);
//		int dataIndex=data.generateElements(rangePosition.getSize());

//		SFVertex3f positionValue=new SFVertex3f();
//		SFVertex3f normalValue=new SFVertex3f();
//		for (int i = 0; i < rangePosition.getSize(); i++) {
//			positionData.getElement(i+rangePosition.getPosition(), positionValue);
//			normalData.getElement(i+rangeNormal.getPosition(), normalValue);
//			SFVertex2f texCoords=derivedTexCoordFunction.getTexCoord(positionValue.getX(),
//					positionValue.getY(), positionValue.getZ(),normalValue.getX(),
//					normalValue.getY(), normalValue.getZ());
//			data.setElement(dataIndex+i, texCoords);
		}

//		SFPrimitiveIndices geometryIndices=geometry.getArray().generateSample();
//		SFPrimitiveIndices indices=getArray().generateSample();
//		int indicesPosition=primitive.getIndicesPositions()[positionGridIndex];
//		int indicesPositionSize=primitive.getIndicesSizes()[positionGridIndex];

//		int texCoordIndicesPosition=getArray().generateElements(geometry.getMesh().getSize());
//		//geometry.getFirstElement() must be zero..
//		for (int i = geometry.getFirstElement(); i < geometry.getLastElement(); i++) {
//			geometry.getArray().getElement(i, geometryIndices);
//			for (int j = 0; j < indicesPositionSize; j++) {
//				indices.getPrimitiveIndices()[j]=geometryIndices.getPrimitiveIndices()[indicesPosition+j];
			}
//			getArray().setElement(i-geometry.getFirstElement()+texCoordIndicesPosition, indices);
		}

//		setFirstElement(geometry.getFirstElement());
//		setLastElement(geometry.getLastElement());

	}

}
;
}
#endif
