#include "SFFunction.h"

namespace sf{

SFFunction::SFFunction(SFParameter* globalV, SFExpressionElement* function) {
    this->parameter = globalV;
    this->function = function;
}

SFFunction::~SFFunction(){
    delete parameter;
    delete function;
}

SFParameteri* SFFunction::getParameter() {
    return parameter;
}


SFExpressionElement* SFFunction::getFunction() {
    return function;
}

}
