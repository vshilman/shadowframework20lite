#ifndef SFPROGRAM_H
#define SFPROGRAM_H

#include "SFPrimitive.h"
#include "SFProgramModule.h"

namespace sf{

class SFProgram{

public:
    
    virtual ~SFProgram(){};
    
    virtual void setPrimitive(SFPrimitive* primitive)=0;

    //More materials may be assigned. Materials should not ovveride same values.
    virtual void setTransform(SFProgramModule* transform)=0;

    //More materials may be assigned. Materials should not ovveride same values.
    virtual void setMaterial(SFProgramModule* material)=0;

    virtual void setLightStep(SFProgramModule* lightStep)=0;

};
}

#endif // SFPROGRAM_H
