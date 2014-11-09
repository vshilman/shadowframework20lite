#ifndef SFFUNCTION_H
#define SFFUNCTION_H

#include <vector>

#include "parameters/SFParameter.h"

#include "expression/SFExpressionElement.h"

using namespace std;

namespace sf{
class SFFunction {

private:
    SFParameter* parameter;
    SFExpressionElement* function;

public:
    SFFunction(SFParameter* globalV, SFExpressionElement* function);
    
    ~SFFunction();

    SFParameteri* getParameter();

    SFExpressionElement* getFunction();
};
}

#endif // SFFUNCTION_H
