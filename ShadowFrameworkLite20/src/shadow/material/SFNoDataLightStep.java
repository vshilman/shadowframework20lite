package shadow.material;

public class SFNoDataLightStep implements SFLightStep{

	private String programName;
	
	public SFNoDataLightStep(String programName) {
		this.programName = programName;
	}

	@Override
	public void closeStep() {
		//Do nothing
	}
	
	@Override
	public void prepareStep() {
		//Do nothing
	}
	
	@Override
	public String getProgramName() {
		return programName;
	}
	
}
