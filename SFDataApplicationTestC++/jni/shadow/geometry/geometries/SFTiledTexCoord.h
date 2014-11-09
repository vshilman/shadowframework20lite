/*
 * SFTiledTexCoord.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFTILEDTEXCOORD_H_
#define SFTILEDTEXCOORD_H_

#include "SFMeshGeometry.h"
#include "shadow/operational/grid/SFQuadrilateralGrid.h"
#include "shadow/operational/grid/SFGridEngine.h"
#include "shadow/pipeline/SFPipelineGrid.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/geometry/geometries/structures/SFTilesSet.h"

namespace sf {

class SFTiledTexCoord : public SFMeshGeometry {

	int* matrix;
	SFTilesSet space;
	int tilesX;
	int tilesY;

public:
	SFTiledTexCoord();
	virtual ~SFTiledTexCoord();

	SFTiledTexCoord(int* matrix, SFTilesSet space,int tilesX,int tilesY) {
		this->matrix = matrix;
		this->space = space;
		this->tilesX=tilesX;
		this->tilesY=tilesY;
	}

	SFTiledTexCoord(int* matrix,int tilesX,int tilesY) {
		this->matrix = matrix;
		this->tilesX=tilesX;
		this->tilesY=tilesY;
	}

	void compile() {
		SFMeshGeometry::compile();

		SFPrimitiveArray* array=getArray();

		int gridN=array->getPrimitive()->getGrid(0)->getN();
		//vs.length=Nv * (gridN-1)+1;
		int gridX= (tilesX);
		int gridY= (tilesY);
//		if(matrix.length!=gridX*gridY){
//			throw new SFException("SFTiledTexCoord with "+matrix.length+" total tiles is being used on a "+gridX+"x"+gridY+" Grid!");
//		}

		int primitiveIndex=0;
		if(getPrimitive()->isQuad()){
			primitiveIndex=array->generateElements(gridX*gridY);
			setFirstElement(primitiveIndex);
			setLastElement(primitiveIndex+gridX*gridY);
		}else{
			primitiveIndex=array->generateElements(gridX*gridY*2);
			setFirstElement(primitiveIndex);
			setLastElement(primitiveIndex+gridX*gridY*2);
		}

		int modelPosition=0;
		int deltaPosition=gridN*gridN;
		float stepX=space.getStepX()*1.0f/((gridN-1));
		float stepY=space.getStepY()*1.0f/((gridN-1));
		for (int x = 0; x < space.getSizeX(); x++) {
			for (int y = 0; y < space.getSizeY(); y++) {

				int tileIndex=x*space.getSizeY()+y;
				//SFRectangularGrid<SFValuenf> values=grids[tileIndex];
				SFArray<SFValuenf>* arrayValues=array->getPrimitiveData(0);
				int position=arrayValues->generateElements(gridN*gridN);
				float xSpace=space.getStepX()*x;
				float ySpace=space.getStepY()*y;

				for (int indexX = 0; indexX < gridN; indexX++) {
					for (int indexY = 0; indexY < gridN; indexY++) {
						arrayValues->setElement(position+indexX*gridN+indexY, new SFVertex2f(xSpace+stepX*indexX, ySpace+stepY*indexY));
					}
				}

				if(tileIndex==0){
					modelPosition=position;
				}
			}
		}

		for (int x = 0; x < gridX; x++) {
			for (int y = 0; y < gridY; y++) {

				int quadIndex=x*gridY+y;
				int tileIndex=matrix[quadIndex];
				SFQuadrilateralGrid<int>* indices=new SFQuadrilateralGrid<int>(gridN);

				SFPipelineGrid* pipelineGrid=array->getPrimitive()->getGrid(0);

				SFGridEngine::generateIndices(indices/*values, , arrayValues, modelPosition+deltaPosition*tileIndex*/);
				int deltaIndex=modelPosition+deltaPosition*tileIndex;
				for (int i = 0; i < gridN; i++) {
					for (int j = 0; j < gridN; j++) {
						indices->setValue(i, j, indices->getValue(i, j)+deltaIndex);
					}
				}

				SFQuadrilateralGrid<int>* quads=indices;
				//quads[0]=*indices;
				if(pipelineGrid->isTriangular()){
					SFTriangularGrid<int>* triangles=SFGridEngine::sliceQuads(quads,1);
					SFGridEngine::loadPrimitiveIndices(array, primitiveIndex, 0, triangles,2);
					primitiveIndex+=2;//triangles.length;

				}else{
					SFGridEngine::loadPrimitiveIndices(array, primitiveIndex, 0, quads,1);
					primitiveIndex+=1;
				}
			}
		}
		SFIndexRange* ranges=getMesh().getPrimitiveDataRanges();
		ranges[0]=SFIndexRange(modelPosition, deltaPosition*space.getSizeX()*space.getSizeY());

	}

	void setTilesX(int tilesX) {
		this->tilesX = tilesX;
	}

	void setTilesY(int tilesY) {
		this->tilesY = tilesY;
	}

	int* getMatrix() {
		return matrix;
	}

	void setMatrix(int* matrix) {
		this->matrix = matrix;
	}

	SFTilesSet getSpace() {
		return space;
	}

	void setSpace(SFTilesSet space) {
		this->space = space;
	}

	void init() {
		//Do nothing
	}

	void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
};

} /* namespace sf */
#endif /* SFTILEDTEXCOORD_H_ */
