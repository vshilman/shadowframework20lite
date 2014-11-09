#ifndef SFPROGRAMBUILDER_H
#define SFPROGRAMBUILDER_H

#include "SFProgram.h"



namespace sf{

class SFProgramBuilder {

public:
    
    virtual ~SFProgramBuilder(){};
    
    virtual SFProgram* generateNewProgram()=0;
    virtual SFProgram* generateImageProgram()=0;
    virtual void loadProgram(SFProgram* program)=0;
};

}

#endif // SFPROGRAMBUILDER_H
