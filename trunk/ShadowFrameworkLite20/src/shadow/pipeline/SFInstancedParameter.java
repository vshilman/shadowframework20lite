package shadow.pipeline;

import shadow.pipeline.parameters.SFParameteri;

public class SFInstancedParameter extends SFParameteri {

	private SFParameteri parameter;
	private String name;
	
	public SFInstancedParameter(SFParameteri parameter, String name) {
		super();
		this.parameter = parameter;
		this.name = name;
	}

	//overrides SFParameter.getName();
	public String getName() {
		return name;
	}
	
	@Override
	public short getType() {
		return parameter.getType();
	}

	public SFParameteri getParameter() {
		return parameter;
	}

	public void setParameter(SFParameteri parameter) {
		this.parameter = parameter;
	}

	public void setName(String name) {
		this.name = name;
	}
}
