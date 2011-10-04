package shadow.pipeline.openGL20;

import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramBuilder;
import shadow.system.SFInitiator;

public class SFGL20ProgramBuilder implements SFProgramBuilder{

	
	@Override
	public SFProgram generateNewProgram() {
		return new SFGL20Program();
	}
	
	@Override
	public void prepareProgram(SFProgram program) {
		SFInitiator.addInitiable((SFGL20Program)program);
	}
	
	@Override
	public void loadProgram(SFProgram program) {
		((SFGL20Program)program).load();
		SFGL20PipelineGraphics.setProgram((SFGL20Program)program);
	}
	
}
