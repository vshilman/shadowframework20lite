
//
//  SFPrimitiveIndices.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFPrimitiveIndices.h"


namespace sf{

SFPrimitiveIndices::SFPrimitiveIndices(){
    this->size=0;
    this->primitiveIndices=0;
}

SFPrimitiveIndices::SFPrimitiveIndices(SFPrimitive* primitive){
    //cout << "primitive->getIndicesCount() " << primitive->getIndicesCount() << endl ;
    this->primitiveIndices = new int[primitive->getIndicesCount()];
    this->size=primitive->getIndicesCount();
}

void SFPrimitiveIndices::set(SFPrimitiveIndices* indices){
    for (int i = 0; i < size; i++) {
        primitiveIndices[i]=indices->primitiveIndices[i];
    }
}

int SFPrimitiveIndices::length(){
    return size;
}

int* SFPrimitiveIndices::getPrimitiveIndices() {
    return primitiveIndices;
}

void SFPrimitiveIndices::setPrimitiveIndices(int* primitiveIndices) {
    this->primitiveIndices = primitiveIndices;
}

void SFPrimitiveIndices::setData(SFPrimitiveIndices indices,int firstIndex,int size){
    for (int j = firstIndex; j < firstIndex+size; j++) {
        primitiveIndices[j]=indices.primitiveIndices[j];
    }
}

/*SFPrimitiveIndices* SFPrimitiveIndices::clone(){
    SFPrimitiveIndices* indices=new SFPrimitiveIndices();
    indices->primitiveIndices=new int[size];
    return indices;
}*/
}
