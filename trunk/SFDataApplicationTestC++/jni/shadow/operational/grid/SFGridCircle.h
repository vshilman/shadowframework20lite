//
//  SFGridCircle.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGridCircle__
#define SFGridCircle__

namespace sf{

template <class T>
class SFGridCircle {
    
	int edges;
	int n;
	T** values;
    int valuesSize;
	
public:

	SFGridCircle() {
		edges=0;
		n=0;
		values=0;
		valuesSize=0;
	}
	/**
	 * Create a grid circle
	 * @param n
	 * @param edges
	 */
	SFGridCircle(int n,int edges) {
		this->n = n;
		this->edges = edges;
		
		if(n==1){//1 point grid...
            this->values=new T*[1];
            this->values[0]=new T[1];
            valuesSize=1;
		}else{
			this->values=new T*[edges];
            for(int i=0;i<edges;i++){
                this->values[i]=new T[n];
            }
            valuesSize=edges;
		}
	}
	
	int getValuesSize(){
		return valuesSize;
	}
	
	T getValue(int edgeIndex,int index) {
		return values[edgeIndex][index];
	}
	
	void setValue(int edgeIndex,int index,T value){
		values[edgeIndex][index]=value;
	}
    
	int round(int index){
		int n=getEdges();
		return round(index, n);
	}
    
	static int round(int index, int n) {
		while(index>=n)
			index-=n;
		while(index<0)
			index+=n;
		return index;
	}
    
	void setN(int n) {
		this->n = n;
	}
    
	int getN() {
		return n;
	}
    
	void setEdges(int edges) {
		this->edges = edges;
	}
    
	int getEdges() {
		return edges;
	}
	
};

}


#endif /* defined(SFGridCircle__) */
