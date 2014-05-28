#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveIndices.h"
#include "shadow/system/SFException.h"

class SFParametrizedGeometry extends SFSurfaceMeshGeometry{

//	SFMeshGeometry parametersGeometry;

//	SFParametrizedGeometry() {
//		super();
	}

//	void addMeshGeometry(SFMeshGeometry geometry) throws SFException {
//		parametersGeometry=geometry;
	}

//	SFParametrizedGeometry(SFMeshGeometry parametersGeometry){
//		super();
		this->parametersGeometry=parametersGeometry;
	}

//	void setParametersGeometry(SFMeshGeometry parametersGeometry) {
		this->parametersGeometry = parametersGeometry;
	}

//	SFParametrizedGeometry(SFPrimitive primitive,SFMeshGeometry parametersGeometry){
//		setPrimitive(primitive);
		this->parametersGeometry=parametersGeometry;
	}

	
//	void compile() {
//		super.compile();

//		parametersGeometry.init();

//		SFIndexRange range=parametersGeometry.getMesh().getPrimitiveDataRanges()[0];

//		int[] deltaPosition=new int[getPrimitive().getGridsCount()];
		this->valuesPositions=new int[getPrimitive().getGridsCount()];

//		for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {

//				int position=getArray().getPrimitiveData(gridIndex).generateElements(range.getSize());

			this->valuesPositions[gridIndex]=position;
//			deltaPosition[gridIndex]=position;//-range.getPosition();
		}

//		int elementsPosition=getArray().generateElements(parametersGeometry.getElementsCount());

//		SFPrimitiveIndices indices=getArray().generateSample();
//		SFPrimitiveIndices paramIndices=parametersGeometry.getArray().generateSample();

//		for (int i = 0; i < parametersGeometry.getElementsCount(); i++) {
//			parametersGeometry.getArray().getElement(i+parametersGeometry.getFirstElement(), paramIndices);

//			for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
//				int[] paramIndicesVec=paramIndices.getPrimitiveIndices();
//				for (int j = 0; j < paramIndicesVec.length; j++) {
//					indices.getPrimitiveIndices()[gridIndex*paramIndicesVec.length+j]=paramIndicesVec[j]+deltaPosition[gridIndex];	
				}
			}
//			getArray().setElement(i+elementsPosition, indices);
		}

//		for (int i = 0; i < fuctionInformations.size(); i++) {

//			FunctionInformations fInfo=fuctionInformations.get(i);
//			fInfo.parametersIndexRange=range;
//			fInfo.parametersArray=parametersGeometry.getArray().getPrimitiveData(0);
		}

	}

}
;
}
#endif
