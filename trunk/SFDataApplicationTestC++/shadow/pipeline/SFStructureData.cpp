//
//  SFStructureData.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFStructureData.h"

namespace sf{

SFStructureData::SFStructureData(SFPipelineStructure* structure) {
    this->structure=structure;
    short length=structure->size();
    SFParameter* parameters=structure->getAllParameters();
    values=new SFValue*[length];
    //cout << "values size "<< structure->size()<<endl;
    
    valuesSize=structure->size();
    
    for(int i=0;i<length;i++){
        values[i]=SFParameteri::generateValue(parameters[i].getType());
    }
}

int SFStructureData::size() {
    return valuesSize;
}

SFPipelineStructure* SFStructureData::getStructure() {
    return structure;
}

SFValue* SFStructureData::getValue(int index) {
    return values[index];
}

SFValue** SFStructureData::getValues() {
    return values;
}

}
