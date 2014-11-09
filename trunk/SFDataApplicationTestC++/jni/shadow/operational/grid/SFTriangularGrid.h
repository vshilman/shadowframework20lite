//
//  SFTriangularGrid.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFTriangularGrid__
#define SFTriangularGrid__

#include "SFRegularGeometricGrid.h"
#include "SFGridCircle.h"
#include "SFCircleIndex.h"

namespace sf{
template <class T>
class SFTriangularGrid :public SFRegularGeometricGrid<T> {

public:

	SFTriangularGrid():SFRegularGeometricGrid<T>(0){

	}

	SFTriangularGrid(int n):SFRegularGeometricGrid<T>(n){
		int grids=(n-1)/3;
		SFRegularGeometricGrid<T>::circles=(new SFGridCircle<T>[grids+1]);
		for (int i = 0; i < SFRegularGeometricGrid<T>::n; i++) {
			SFRegularGeometricGrid<T>::circles[i]=SFGridCircle<T>(n-3*i,3);
		}
	}

  static SFCircleIndex getCircleIndex(int i1,int i2,int i3) {
	  	  int vs[3];
	  	  vs[0]=i1;
	  	  vs[1]=i2;
	  	  vs[2]=i3;

		SFCircleIndex circleIndex;
		int min=vs[0];
		circleIndex.edgeIndex=0;
		circleIndex.inEdgeIndex=vs[1]-vs[0];
		for (int index = 1; index < 3; index++) {
			if(vs[index]<=min && vs[index]!=vs[index-1]){
				min=vs[index];
				circleIndex.edgeIndex=3-index;
				circleIndex.inEdgeIndex=vs[SFGridCircle<T>::round(index+1, 3)]-vs[index];
			}
		}
		circleIndex.circleIndex=min;

		return circleIndex;
  }

    template <class S>
	SFTriangularGrid<S> sameGrid(){
		SFTriangularGrid<S> grid=new SFTriangularGrid<S>(this->n);
		return grid;
	}

	void setValue(int i,int j,T value){
		int k=SFRegularGeometricGrid<T>::n-1-i-j;
		setValue(i, j, k, value);
	}

	void setValue(int i, int j, int k, T value) {
		SFCircleIndex circle=getCircleIndex(i, j, k);
		SFRegularGeometricGrid<T>::circles[circle.circleIndex].setValue(circle.edgeIndex,circle.inEdgeIndex,value);
	}

	T getValue(int i,int j){
		int k=SFRegularGeometricGrid<T>::n-1-i-j;
		return getValue(i, j, k);
	}

	T getValue(int i, int j, int k) {
		SFCircleIndex circle=getCircleIndex(i, j, k);
		return (this->circles[circle.circleIndex].getValue(circle.edgeIndex,circle.inEdgeIndex));
	}

};
}



#endif /* defined(SFTriangularGrid__) */
