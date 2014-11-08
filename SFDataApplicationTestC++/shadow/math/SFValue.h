//
//  SFValue.h
//  ShadowFrameworkViewer
//
//  Created by Alessandro Martinelli on 20/10/13.
//  Copyright (c) 2013 Alessandro Martinelli. All rights reserved.
//

#ifndef __ShadowFrameworkViewer__SFValue__
#define __ShadowFrameworkViewer__SFValue__

#include <iostream>

namespace sf{

class SFValue{
    
public:
    virtual ~SFValue(){}
    
    virtual float* getV()=0;
    
    virtual int getSize()=0;
    
    void add(SFValue* value);
    
    void addMult(float m,SFValue* value);
    
    float dot(SFValue* value);
    
    void mult(float m);
    
    void multValue(SFValue* value);
    
    void set(SFValue* value);
    
    void setByIndex(int index, float element);
    
    void setArray(float* data);
    
    void subtract(SFValue* value);
};
    
}

#endif /* defined(__ShadowFrameworkViewer__SFValue__) */
