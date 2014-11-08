
#include "SFPrimitiveProgramAssociation.h"


namespace sf{
SFPrimitiveProgramAssociation::SFPrimitiveProgramAssociation(SFPipelineRegister* reg,
                                  SFProgramComponent* program) {
	this->reg = reg;
	this->program = program;
}

SFPipelineRegister* SFPrimitiveProgramAssociation::getRegister() {
	return reg;
}

SFProgramComponent* SFPrimitiveProgramAssociation::getProgram() {
	return program;
}
}
