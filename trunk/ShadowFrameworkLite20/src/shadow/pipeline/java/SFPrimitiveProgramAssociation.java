package shadow.pipeline.java;

import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFPrimitiveProgramAssociation {
	
	private SFPipelineRegister register;
	private SFProgramComponent program;
	
	public SFPrimitiveProgramAssociation(SFPipelineRegister register, SFProgramComponent program) {
		super();
		this.register = register;
		this.program = program;
	}
	
	public SFPipelineRegister getRegister() {
		return register;
	}
	
	public SFProgramComponent getProgram() {
		return program;
	}

}
