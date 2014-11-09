
#ifndef SFPrimitiveProgramAssociation_H
#define SFPrimitiveProgramAssociation_H

#include "../parameters/SFPipelineRegister.h"
#include "../SFPRogramComponent.h"


namespace sf{
class SFPrimitiveProgramAssociation {

private:
	SFPipelineRegister* reg;
	SFProgramComponent* program;
	
public:
	SFPrimitiveProgramAssociation(SFPipelineRegister* reg,
                                  SFProgramComponent* program);
	
	SFPipelineRegister* getRegister();
	
	SFProgramComponent* getProgram();
    
};
}


#endif /* defined(SFPrimitiveProgramAssociation_H) */
