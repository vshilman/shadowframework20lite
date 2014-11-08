////
////  SFRectangularGrid.h
////  
////
////  Created by Alessandro Martinelli on 23/10/12.
////  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
////
//
#ifndef SFRectangularGrid__
#define SFRectangularGrid__

#include "shadow/operational/grid/SFQuadGrid.h"

namespace sf{
template <class T>
class SFRectangularGrid : public SFQuadGrid<T>{

    int width,height;

	T** grid;

public:
	SFRectangularGrid(int width, int height) {
		this->width = width;
		this->height = height;
		this->grid = new T*[height];
		for(int i=0;i<height;i++){
			this->grid[i]=new T[width];
		}
	}

	~SFRectangularGrid(){
		for(int i=0;i<height;i++){
			delete[] this->grid[i];
		}
		delete this->grid;
	}

	/* (non-Javadoc)
	 * @see shadow.operational.grid.SFQuadGrid#getValue(int, int)
	 */
	T getValue(int i,int j){
		return (T)(grid[i][j]);
	}

	/* (non-Javadoc)
	 * @see shadow.operational.grid.SFQuadGrid#setValue(int, int, T)
	 */
	void setValue(int i,int j,T value){
		grid[i][j]=value;
	}

	int getWidth() {
		return width;
	}

	void setWidth(int width) {
		this->width = width;
	}

	int getHeight() {
		return height;
	}

	void setHeight(int height) {
		this->height = height;
	}
};
}

#endif /* defined(SFRectangularGrid__) */
