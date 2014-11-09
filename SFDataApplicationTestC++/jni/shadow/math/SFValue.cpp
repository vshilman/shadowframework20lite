//
//  SFValue.cpp
//  ShadowFrameworkViewer
//
//  Created by Alessandro Martinelli on 20/10/13.
//  Copyright (c) 2013 Alessandro Martinelli. All rights reserved.
//

#include "SFValue.h"


namespace sf{
    
    void SFValue::add(SFValue* value){
        int length=getSize();
        float* v=getV();
        float* valuev=value->getV();
        for (int i = 0; i < length; i++) {
            v[i]+=valuev[i];
        }
    }
    
    void SFValue::addMult(float m,SFValue* value){
        int length=getSize();
        float* v=getV();
        float* valuev=value->getV();
        for (int i = 0; i < length; i++) {
            v[i]+=m*valuev[i];
        }
    }
    
    float SFValue::dot(SFValue* value) {
        int length=getSize();
        float* v=getV();
        float* valuev=value->getV();
        float dot=0;
        for (int i = 0; i < length; i++) {
            dot+=v[i]*valuev[i];
        }
        return dot;
    }
    
    void SFValue::mult(float m) {
        int length=getSize();
        float* v=getV();
        for (int i = 0; i < length; i++) {
            v[i]*=m;
        }
    }
    
    void SFValue::multValue(SFValue* value){
        int length=getSize();
        int valuelength=value->getSize();
        float* v=getV();
        float* valuev=value->getV();
        for (int i = 0; i < length && i < valuelength; i++) {
            v[i]*=valuev[i];
        }
    }
    
    
    void SFValue::set(SFValue* value){
        int length=getSize();
        int valuelength=value->getSize();
        float* v=getV();
        float* valuev=value->getV();
        for (int i = 0; i < length && i < valuelength; i++) {
            v[i]=valuev[i];
        }
    }
    
    void SFValue::setByIndex(int index,float value) {
        float* v=getV();
        v[index]=value;
    }
    
    void SFValue::setArray(float* data) {
        int length=getSize();
        float* v=getV();
        for (int i = 0; i < length; i++) {
            v[i]=data[i];
        }
    }
    
    
    void SFValue::subtract(SFValue* value) {
        int length=getSize();
        int valuelength=value->getSize();
        float* v=getV();
        float* valuev=value->getV();
        for (int i = 0; i < length && i < valuelength; i++) {
            v[i]-=valuev[i];
        }
    }
    
}

