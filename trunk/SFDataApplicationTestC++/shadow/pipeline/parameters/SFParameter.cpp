//
//  SFParameter.cpp
//  
//
//  Created by Alessandro Martinelli on 14/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFParameter.h"


namespace sf{

SFParameter::SFParameter() {
    this->type=GLOBAL_GENERIC;
}

SFParameter::SFParameter(string name) {
    this->name = name;
    this->type = GLOBAL_GENERIC;
}

SFParameter::SFParameter(string name, short type) {
    this->name = name;
    this->type = type;
}

void SFParameter::setName(string name) {
    this->name=name;
}

string SFParameter::getName() {
    return name;
}

short SFParameter::getType() {
    return this->type;
}

void SFParameter::setType(short type) {
    this->type = type;
}

}
