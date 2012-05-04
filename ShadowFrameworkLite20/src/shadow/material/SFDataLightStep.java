package shadow.material;

public class SFDataLightStep implements SFLightStep{
	
	private String name;
	
	public SFDataLightStep(String name) {
		super();
		this.name = name;
	}

	@Override
	public void closeStep() {
		//Do nothing
	}
	
	@Override
	public String getProgramName() {
		return name;
	}
	
	@Override
	public void prepareStep() {
		//Do nothing
	}
}
