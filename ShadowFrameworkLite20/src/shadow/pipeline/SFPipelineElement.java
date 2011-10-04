package shadow.pipeline;


public abstract class SFPipelineElement {

	String name;

	public SFPipelineElement() {
		super();
	}

	/**
	 * Name must be a valid SFCode
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name must be a valid SFCode
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}
}