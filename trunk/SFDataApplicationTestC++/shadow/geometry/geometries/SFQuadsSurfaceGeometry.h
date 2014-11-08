#ifndef shadow_geometry_geometries_SFQuadsSurfaceGeometry_H_
#define shadow_geometry_geometries_SFQuadsSurfaceGeometry_H_

#include "shadow/geometry/geometries/structures/SFQuadsGrid.h"
#include "shadow/geometry/geometries/SFSurfaceMeshGeometry.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/operational/grid/SFGridEngine.h"
#include "shadow/operational/grid/SFQuadrilateralGrid.h"
#include "shadow/operational/grid/SFRectangularGrid.h"
#include "shadow/operational/grid/SFTriangularGrid.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPipelineGrid.h"
#include "shadow/system/SFArray.h"

namespace sf {
class SFQuadsSurfaceGeometry : public SFSurfaceMeshGeometry{

	SFQuadsGrid quadsGrid;
	int* gridSizes;
	float* gridUS;
	float* gridVS;
	int usize;
	int vsize;

	SFQuadsSurfaceGeometry(){
		gridSizes=0;
		gridUS=0;
		gridVS=0;
		usize=0;
		vsize=0;
	}

	void setQuadsGrid(SFQuadsGrid quadsGrid) {
		this->quadsGrid = quadsGrid;
	}

	void compile() {
		SFSurfaceMeshGeometry::compile();

		int gridCount=getPrimitive()->getGridsCount();

		gridSizes=new int[getPrimitive()->getGridsCount()];

		int primitivesSize=((quadsGrid.getNu()-1)*(quadsGrid.getNv()-1))<<(getPrimitive()->isQuad()?0:1);
		int primitiveIndex=getArray()->generateElements(primitivesSize);

		SFSurfaceMeshGeometry::valuesPositions=new int[gridCount];

		for (int gridIndex = 0; gridIndex < gridCount; gridIndex++) {

			SFPipelineGrid* pipelineGrid=getPrimitive()->getGrid(gridIndex);

			int n1=pipelineGrid->getN()-1;
			int width=SFQuadsGrid::getPartitionedSplitsSize(n1,quadsGrid.getNu());
			int height=SFQuadsGrid::getPartitionedSplitsSize(n1,quadsGrid.getNv());

			SFRectangularGrid<int>* indices=new SFRectangularGrid<int>(width,height);

			SFArray<SFValuenf>* arrayValues=getArray()->getPrimitiveData(gridIndex);

			this->valuesPositions[gridIndex]=arrayValues->generateElements(width*height);
			//samples[gridIndex]=arrayValues.generateSample();
			SFGridEngine::generateIndices(indices);

			if(quadsGrid.isCloseU() || quadsGrid.isCloseV()){
				SFGridEngine::closeRectangle(indices, quadsGrid.isCloseU()?1:0,  quadsGrid.isCloseV()?1:0);
			}
	
			int quadsSize;
			SFQuadrilateralGrid<int>* quads=SFGridEngine::breakRectangularGrid(indices, pipelineGrid->getN(),&quadsSize);
			if(pipelineGrid->isTriangular()){
				SFTriangularGrid<int>* triangles=SFGridEngine::sliceQuads(quads,quadsSize);
				SFGridEngine::loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, triangles,quadsSize*2);
			}else{
				SFGridEngine::loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, quads,quadsSize);
			}
		}

		for (unsigned int i = 0; i < fuctionInformations.size(); i++) {

			FunctionInformations fInfo=fuctionInformations.at(i);
			int sampleGridIndex = fInfo.gridIndex[0];
			int n=getPrimitive()->getGrid(sampleGridIndex)->getN();

			int n1=n-1;
			float stepn1=1.0f/n1;
			this->gridVS=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getV_splits(),vsize);
			this->gridUS=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getU_splits(),usize);

			SFArray<SFValuenf>* parametersArray=SFPipeline::getSfPipelineMemory()->generateValues(2);
			int positionArray=parametersArray->generateElements(usize*vsize);
			int index=positionArray;
			for (int j = 0; j < vsize; j++) {
				for (int k = 0; k < usize; k++) {
					parametersArray->setElement(index, new SFVertex2f(gridUS[k],gridVS[j]));
					index++;
				}
			}
			fInfo.parametersIndexRange=SFIndexRange(positionArray, usize*vsize);
			fInfo.parametersArray=parametersArray;
		}

	}

	SFQuadsGrid getQuadsGrid() {
		return quadsGrid;
	}

};

}
#endif
