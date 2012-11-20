package shadow.pipeline;


public class SFProgramModule extends SFPipelineElement{

	protected SFProgramComponent[] components=new SFProgramComponent[0];
	
	public SFProgramModule() {
	}
	
	public SFProgramModule(String name) {
		setName(name);
	}

	public void setComponents(SFProgramComponent[] components) {
		this.components = components;
	}

	public SFProgramComponent[] getComponents() {
		return components;
	}
	
	@Override
	public SFProgramModule clone() {
		SFProgramModule module=new SFProgramModule(getName());
		module.components=components;
		return module;
	}
}