//
//  SFMemory.h
//  ShadowFrameworkViewer
//
//  Created by Alessandro Martinelli on 11/10/13.
//  Copyright (c) 2013 Alessandro Martinelli. All rights reserved.
//

#ifndef __ShadowFrameworkViewer__SFMemory__
#define __ShadowFrameworkViewer__SFMemory__


#include <vector>
#include <iostream>
using namespace std;


#ifdef __APPLE__
#include <stdio.h>
#define  sflog(...)  printf(__VA_ARGS__)
#endif

#ifdef ANDROID
#include <android/log.h>
#define  sflog(...)  __android_log_print(ANDROID_LOG_INFO,"Log Tag ",__VA_ARGS__);
#endif

#define SF_CPP_DEBUG_MODE
//#define SF_CPP_RUN_MODE

class GCMEMORY{

public:
    static GCMEMORY* gCMemory;
    
    vector<void*> ____gC;
    vector<short> ____gCSizes;
    
    
    int getGcSize(){
        return GCMEMORY::gCMemory->____gC.size();
    }
    
    void printGcContent(){
        for(unsigned int i=0;i<GCMEMORY::gCMemory->____gC.size();i++){
            sflog(" [%d], \n",GCMEMORY::gCMemory->____gCSizes[i] );
        }
    }

};

#ifdef SF_CPP_RUN_MODE

template <class T>
T* alloc(){
    return new T();
}

template <class T>
T* allocv(int size){
    return new T[size];
}

template <class T>
void dealloc(T* t){
    delete t;
}

template <class T>
void deallocv(T* t,int size){
    delete[] t;
}

#endif


#ifdef SF_CPP_DEBUG_MODE

template <class T>
T* alloc(){
    T* t=new T();
    GCMEMORY::gCMemory->____gC.push_back(t);
    GCMEMORY::gCMemory->____gCSizes.push_back(sizeof(T));
    return t;
}

template <class T>
T* allocv(int size){
    T* t=new T[size];
    GCMEMORY::gCMemory->____gC.push_back(t);
    GCMEMORY::gCMemory->____gCSizes.push_back(sizeof(T)*size);
    return t;
}

template <class T>
void dealloc(T* t){
    int position=find(GCMEMORY::gCMemory->____gC.begin(),
                      GCMEMORY::gCMemory->____gC.end(), t)-
    GCMEMORY::gCMemory->____gC.begin();

    if(position+GCMEMORY::gCMemory->____gC.begin()!=GCMEMORY::gCMemory->____gC.end()){
        GCMEMORY::gCMemory->____gC.erase(position+GCMEMORY::gCMemory->____gC.begin());
        GCMEMORY::gCMemory->____gCSizes.erase(position+GCMEMORY::gCMemory->____gCSizes.begin());
        
        delete t;
    }else{
        cout << "Error on deleting element " << position << " " << sizeof(T) ;
    }
    
}

template <class T>
void deallocv(T* t,int size){
    //TODO : find t, remove it from gC and gCSize, check the size!!
    int position=find(GCMEMORY::gCMemory->____gC.begin(),
                      GCMEMORY::gCMemory->____gC.end(), t)-
                      GCMEMORY::gCMemory->____gC.begin();
    
    if(GCMEMORY::gCMemory->____gCSizes[position]!=size*sizeof(T)){
        cout << "deallocv size issue. Was " << GCMEMORY::gCMemory->____gCSizes[position]
            << " found " << size*sizeof(T);
    }
    
    if(position+GCMEMORY::gCMemory->____gC.begin()!=GCMEMORY::gCMemory->____gC.end()){
        GCMEMORY::gCMemory->____gC.erase(position+GCMEMORY::gCMemory->____gC.begin());
        GCMEMORY::gCMemory->____gCSizes.erase(position+GCMEMORY::gCMemory->____gCSizes.begin());
    }
    
    delete[] t;
}

#endif

void printAll();

#endif /* defined(__ShadowFrameworkViewer__SFMemory__) */
