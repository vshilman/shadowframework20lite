//
//  SFGridEngine.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGridEngine__
#define SFGridEngine__

#include "SFQuadGrid.h"
#include "SFLinearGrid.h"
#include "SFTriangularGrid.h"
#include "SFQuadrilateralGrid.h"
#include "SFTriangularGrid.h"
#include "shadow/pipeline/SFMesh.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveIndices.h"

namespace sf{

class SFGridEngine {

public:


    template <class S>
	static void closeRectangle(SFQuadGrid<S>* mainGrid,int closeX,int closeY){

		for (int j = 0; j < closeX; j++) {

			int otherIndex=mainGrid->getWidth()-closeX+j;
			for (int i = 0; i < mainGrid->getHeight(); i++) {
				mainGrid->setValue(i, otherIndex, mainGrid->getValue(i, j));
			}
		}

		for (int i = 0; i < closeY; i++) {
			int otherIndex=mainGrid->getHeight()-closeY+i;
			for (int j = 0; j < mainGrid->getWidth(); j++) {
				mainGrid->setValue(otherIndex, j, mainGrid->getValue(i, j));
			}
		}
    }

    template <class S>
    static int generateElementsX(SFLinearGrid<S>* mainGrid, int n){
		int elementsX=n==2?(mainGrid->getN()-1):mainGrid->getN()/(n-1);
		return elementsX;
	}

    template <class S>
    static int generateElementsX(SFQuadGrid<S>* mainGrid, int n){
		int elementsX=n==2?(mainGrid->getWidth()-1):mainGrid->getWidth()/(n-1);
		return elementsX;
	}

    template <class S>
    static int generateElementsY(SFQuadGrid<S>* mainGrid, int n){
    	int elementsY=n==2?(mainGrid->getHeight()-1):mainGrid->getHeight()/(n-1);
    	return elementsY;
    }


    template <class S>
	static SFLinearGrid<S>* breakLinearGrid(SFLinearGrid<S>* mainGrid,int n){
    	int elementsX = generateElementsX(mainGrid, n);

		SFLinearGrid<S> quadsGrid=new SFLinearGrid<S>(n);

		SFLinearGrid<S>* newGrids=new SFLinearGrid<S>[elementsX];

		int index=0;
		for (int j = 0; j < elementsX; j++) {
			SFLinearGrid<S> quadsGridClone=quadsGrid.sameGrid();
			for (int j1 = 0; j1 < n; j1++) {
				quadsGridClone.setValue( j1, mainGrid->getValue(j*(n-1)+j1));
			}
			newGrids[index]=quadsGridClone;
			index++;
		}

		return (SFLinearGrid<S>*)newGrids;
    }


    template <class S>
	static SFQuadrilateralGrid<S>* breakRectangularGrid(SFQuadGrid<S>* mainGrid,int n,int* size) {

		int elementsX = generateElementsX(mainGrid, n);
		int elementsY = generateElementsY(mainGrid, n);

		SFQuadrilateralGrid<S>* quadsGrid=new SFQuadrilateralGrid<S>(n);

		SFQuadrilateralGrid<S>* newGrids=new SFQuadrilateralGrid<S>[elementsX*elementsY];

		int index=0;
		for (int i = 0; i < elementsY; i++) {
			for (int j = 0; j < elementsX; j++) {
				SFQuadrilateralGrid<S>* quadsGridClone=quadsGrid->sameGrid();
				for (int i1 = 0; i1 < n; i1++) {
					for (int j1 = 0; j1 < n; j1++) {
						quadsGridClone->setValue(i1, j1, mainGrid->getValue(i*(n-1)+i1,j*(n-1)+j1));
					}
				}
				newGrids[index]=*quadsGridClone;
				index++;
			}
		}

		return newGrids;
	}



    template <class S>
	static SFTriangularGrid<S>* sliceQuads(SFQuadrilateralGrid<S>* quadsGrids,int quadsGridsSize){

		int n=quadsGrids[0].getN();

		SFTriangularGrid<S>* newGrids=new SFTriangularGrid<S>[quadsGridsSize<<1];

		int index=0;
		for (int i=0;i<quadsGridsSize;i++) {
			SFQuadrilateralGrid<S> quadGrid=quadsGrids[i];
			//first triangle
			SFTriangularGrid<S> triangleGrid=SFTriangularGrid<S>(n);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-i; j++) {
					triangleGrid.setValue(i, j, quadGrid.getValue(i, j));
				}
			}
			newGrids[index]=triangleGrid;
			triangleGrid=SFTriangularGrid<S>(n);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-i; j++) {
					triangleGrid.setValue(i, j, quadGrid.getValue(n-1-i, n-1-j));
				}
			}
			newGrids[index+1]=triangleGrid;
			index+=2;
		}


		return (SFTriangularGrid<S>*)newGrids;
	}


//    template <class S>
//	static void printLinearGrid(SFLinearGrid<S>* indices){
//		System.out.println("[");
//		for (int i = 0; i < indices->getN(); i++) {
//			System.out.print(" \t"+indices->getValue(i));
//		}
//		System.out.println("]");
//	}

//    template <class S>
//    static void printTriangularGrid(SFTriangularGrid<S>* indices){
//
//		for (int i = 0; i < indices->getN(); i++) {
//			for (int j = 0; j < indices->getN()-i; j++) {
//				System.out.print(" \t"+indices->getValue(i, j));
//			}
//			System.out.print(" \n");
//		}
//		System.out.println("]");
//	}

//	static <S> void printQuadsGrid(SFQuadGrid<S> indices){
//		System.out.println("[");
//		for (int i = 0; i < indices->getHeight(); i++) {
//			for (int j = 0; j < indices->getWidth(); j++) {
//				System.out.print(" \t"+indices->getValue(i, j));
//			}
//			System.out.print(" \n");
//		}
//		System.out.println("]");
//	}

	static void loadPrimitiveIndices(SFPrimitiveArray* array,int primitiveIndex,int gridIndex,SFTriangularGrid<int>* indicesVector,int indicesVectorLength){

		SFPrimitiveIndices* thisTmpIndices=array->generateSample();

		int position=array->getPrimitive()->getIndicesPositions()[gridIndex];

		for (int m = 0; m < indicesVectorLength; m++) {
			array->getElement(primitiveIndex+ m, thisTmpIndices);
			SFTriangularGrid<int>* indices=indicesVector+m;

			loadPrimitiveIndices(array, thisTmpIndices, position, primitiveIndex+ m,indices);
		}
	}

	static void loadPrimitiveIndices(SFPrimitiveArray* array,int primitiveIndex,int gridIndex,SFQuadrilateralGrid<int>* indicesVector,int indicesVectorLength){

		SFPrimitiveIndices* thisTmpIndices=array->generateSample();

		int position=array->getPrimitive()->getIndicesPositions()[gridIndex];
		//int primitiveIndex=array->generateElements(indicesVector.length);

		for (int m = 0; m < indicesVectorLength; m++) {
			array->getElement(primitiveIndex+ m, thisTmpIndices);
			SFQuadrilateralGrid<int>* indices=indicesVector+m;

			loadPrimitiveIndices(array, thisTmpIndices, position, primitiveIndex+ m,
					indices);

		}
	}


	static void loadPrimitiveIndices(SFPrimitiveArray* array,
			SFPrimitiveIndices* thisTmpIndices, int position, int primitiveIndex,
			SFTriangularGrid<int>* indices) {
		int gridSize=indices->getN()-1;
		int* tmpIndices=thisTmpIndices->getPrimitiveIndices();

		int inGridIndex=0;
		tmpIndices[position]=indices->getValue(0, 0);
		tmpIndices[position+1]=indices->getValue(0, gridSize);
		tmpIndices[position+2]=indices->getValue(gridSize, 0);
		inGridIndex+=3;
		for (int i = 1; i < gridSize; i++) {
						tmpIndices[position+2+i]=indices->getValue(0, i);
						tmpIndices[position+2+i+gridSize-1]=indices->getValue(i,gridSize-i);
						tmpIndices[position+2+i+2*(gridSize-1)]=indices->getValue(gridSize-i,0);
						inGridIndex+=3;
					}
		for (int k = 1; k < gridSize-1; k++) {
			for (int l = 1; l < gridSize-1; l++) {
				tmpIndices[position+inGridIndex]=indices->getValue(k, l);
				inGridIndex++;
			}
		}
		array->setElement(primitiveIndex, thisTmpIndices);
	}

	static void loadPrimitiveIndices(SFPrimitiveArray* array,
			SFPrimitiveIndices* thisTmpIndices, int position, int primitiveIndex,
			SFQuadrilateralGrid<int>* indices) {
		int gridSize=indices->getN()-1;
		int* tmpIndices=thisTmpIndices->getPrimitiveIndices();

		int inGridIndex=0;
		tmpIndices[position]=indices->getValue(0, 0);
		tmpIndices[position+1]=indices->getValue(0, gridSize);
		tmpIndices[position+2]=indices->getValue(gridSize, gridSize);
		tmpIndices[position+3]=indices->getValue(gridSize, 0);
		inGridIndex+=4;
		for (int i = 1; i < gridSize; i++) {
						tmpIndices[position+3+i]=indices->getValue(0, i);
						tmpIndices[position+3+i+gridSize-1]=indices->getValue(i,gridSize);
						tmpIndices[position+3+i+2*(gridSize-1)]=indices->getValue(gridSize,gridSize-i);
						tmpIndices[position+3+i+3*(gridSize-1)]=indices->getValue(gridSize-i,0);
						inGridIndex+=4;
					}
		for (int k = 1; k < gridSize; k++) {
			for (int l = 1; l < gridSize; l++) {
				tmpIndices[position+inGridIndex]=indices->getValue(k, l);
				inGridIndex++;
			}
		}
		array->setElement(primitiveIndex, thisTmpIndices);
	}

	static void loadGridAndGenerateIndices(SFQuadGrid<SFValuenf>* values,
			SFQuadGrid<int>* indices, SFArray<SFValuenf>* arrayValues, int position) {
		int index=0;
		for (int i = 0; i < indices->getHeight(); i++) {
			for (int j = 0; j < indices->getWidth(); j++) {
				SFValuenf tmp=values->getValue(i, j);
				arrayValues->setElement(position+index,&tmp);
				indices->setValue(i, j, index);
				index++;
			}
		}
	}

	static void generateIndices(SFQuadGrid<int>* indices) {
		int index=0;
		for (int i = 0; i < indices->getHeight(); i++) {
			for (int j = 0; j < indices->getWidth(); j++) {
				//arrayValues.setElement(position+index,values.getValue(i, j));
				indices->setValue(i, j, index);
				index++;
			}
		}
	}

};

}




#endif /* defined(SFGridEngine__) */
