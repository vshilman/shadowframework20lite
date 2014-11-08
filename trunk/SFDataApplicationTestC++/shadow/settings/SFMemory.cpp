//
//  SFMemory.cpp
//  ShadowFrameworkViewer
//
//  Created by Alessandro Martinelli on 11/10/13.
//  Copyright (c) 2013 Alessandro Martinelli. All rights reserved.
//

#include "SFMemory.h"

GCMEMORY* GCMEMORY::gCMemory=new GCMEMORY();

void printAll(){
    
    sflog ( "gC size %d \n", GCMEMORY::gCMemory->getGcSize() );
    
    GCMEMORY::gCMemory->printGcContent();
}