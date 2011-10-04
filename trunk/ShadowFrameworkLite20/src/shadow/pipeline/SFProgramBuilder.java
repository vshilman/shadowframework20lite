package shadow.pipeline;

public interface SFProgramBuilder {
	public SFProgram generateNewProgram();
	public void prepareProgram(SFProgram program);
	public void loadProgram(SFProgram program);
}
