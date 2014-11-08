//
//  SFLinearGrid.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFLinearGrid__
#define SFLinearGrid__


template <class T>
class SFLinearGrid {

private:
	int n;
	T* values;
	
public:
	/**
	 * Create a grid circle
	 * @param n
	 * @param edges
	 */
	SFLinearGrid(int n) {
		this->setN(n);
		this->values=new T[n];
	}
    
	void setValue(int index,T value){
		values[index]=value;
	}
	
    template <class S>
	SFLinearGrid<S> sameGrid(){
		SFLinearGrid<S> grid=new SFLinearGrid<S>(this->n);
		return grid;
	}
    
	T getValue(int index){
		return (T)values[index];
	}
	
	int getN() {
		return n;
	}
    
	void setN(int n) {
		this->n = n;
	}
	
};

#endif /* defined(SFLinearGrid__) */
