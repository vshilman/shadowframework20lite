//
//  SFParameter.cpp
//  SpotLightTownTest
//
//  Created by Alessandro Martinelli on 14/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFParameter.h"



SFParameter::SFParameter() {
    this->type=GLOBAL_GENERIC;
}

SFParameter::SFParameter(string name) {
    this->name = name;
}

SFParameter::SFParameter(string name, short type) {
    this->name = name;
    this->type = type;
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