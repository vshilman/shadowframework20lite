
#ifndef SFGL20ProgramBuilder_H
#define SFGL20ProgramBuilder_H

#include "../SFProgramBuilder.h"
#include "../SFProgram.h"

#include "SFGL20Program.h"
#include "SFGL20ImageProgram.h"
#include "SFGL20PipelineGraphics.h"


namespace sf{
class SFGL20ProgramBuilder : public SFProgramBuilder{
    
public:
    SFProgram* generateNewProgram() {
		return new SFGL20Program();
	}
	
    void loadProgram(SFProgram* program) {
		((SFGL20AbstractProgram*)program)->load();
        SFGL20PipelineGraphics::setProgram((SFGL20GenericProgram*)program);
	}
	
    SFProgram* generateImageProgram() {
		return new SFGL20ImageProgram();
	}
};
}


#endif /* defined(SFGL20ProgramBuilder__) */
