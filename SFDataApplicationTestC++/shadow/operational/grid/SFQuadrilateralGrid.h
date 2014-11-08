//
//  SFQuadrilateralGrid.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFQuadrilateralGrid__
#define SFQuadrilateralGrid__

#include "SFRegularGeometricGrid.h"
#include "SFQuadGrid.h"
#include "SFCircleIndex.h"

namespace sf{

template <class T>
class SFQuadrilateralGrid : public SFRegularGeometricGrid<T>, public SFQuadGrid<T>{

public:
	SFQuadrilateralGrid():SFRegularGeometricGrid<T>(0){

	}

	SFQuadrilateralGrid(int n):SFRegularGeometricGrid<T>(n){
		int grids=(n+1)>>1;
		/* examples :
		 * 7 has 4
		 * 6,5 has 3
		 * 4,3 has 2
		 * 2,1 has 1
		 */
		SFRegularGeometricGrid<T>::circles=(new SFGridCircle<T>[grids]);
		for (int i = 0; i < grids; i++) {
			SFRegularGeometricGrid<T>::circles[i]=SFGridCircle<T>(n-2*i,4);
		}
	}

	SFQuadrilateralGrid<T>* sameGrid(){
		SFQuadrilateralGrid<T>* grid=new SFQuadrilateralGrid<T>(this->n);
		return grid;
	}

	int getHeight() {
		return SFRegularGeometricGrid<T>::getN();
	}

	int getWidth() {
		return SFRegularGeometricGrid<T>::getN();
	}

  static SFCircleIndex getCircleIndex(int i1,int i2,int i3,int i4) {
		  int vs[4];
		  vs[0]=i1;
		  vs[1]=i2;
		  vs[2]=i3;
		  vs[3]=i4;

		SFCircleIndex circleIndex;
		int min=vs[0];
		circleIndex.edgeIndex=0;
		circleIndex.inEdgeIndex=vs[1]-vs[0];
		for (int index = 1; index < 4; index++) {
			if(vs[index]<=min && vs[index]!=vs[index-1]){
				min=vs[index];
				circleIndex.edgeIndex=4-index;
				circleIndex.inEdgeIndex=vs[SFGridCircle<T>::round(index+1, 4)]-vs[index];
			}
		}
		circleIndex.circleIndex=min;

		return circleIndex;
  }

	SFCircleIndex getCircle(int i,int j){
		int k=SFRegularGeometricGrid<T>::n-1-i;
		int l=SFRegularGeometricGrid<T>::n-1-j;
		return getCircleIndex(i, j, k, l);
	}

	void setValue(int i, int j, T value) {
		SFCircleIndex circle=getCircle(i, j);
		this->circles[circle.circleIndex].setValue(circle.edgeIndex,circle.inEdgeIndex,value);
	}

	T getValue(int i, int j) {
		SFCircleIndex circle=getCircle(i, j);
		return (this->circles[circle.circleIndex].getValue(circle.edgeIndex,circle.inEdgeIndex));
	}

	//private static void stampCircleIndex(SFCircleIndex index){
	//	System.out.println(" "+index.circleIndex+" "+index.edgeIndex+" "+index.inEdgeIndex);
	//}

};
}

#endif /* defined(SFQuadrilateralGrid__) */
