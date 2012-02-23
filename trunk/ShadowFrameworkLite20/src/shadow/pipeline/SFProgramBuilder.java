package shadow.pipeline;

public interface SFProgramBuilder {
	public SFProgram generateNewProgram();
	public SFProgram generateImageProgram();
	public void loadProgram(SFProgram program);
}
