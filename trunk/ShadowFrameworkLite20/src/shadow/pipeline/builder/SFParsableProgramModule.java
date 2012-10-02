package shadow.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFProgramModule;

public class SFParsableProgramModule extends SFProgramModule implements SFBuilderElement{

	private List<SFProgramComponent> components=new ArrayList<SFProgramComponent>();
	
	public SFParsableProgramModule() {
		super();
	}
	
	public void addComponent(SFProgramComponent component){
		components.add(component);
	}
	
	@Override
	public void finalize() {
		
		SFProgramModule module=clone();
		
		SFProgramComponent[] modules=components.toArray(new SFProgramComponent[components.size()]);
		module.setComponents(modules);
		
		SFPipeline.loadShaderModule(getName(), module);
	}

	private static ArrayList<String> allCommands=new ArrayList<String>();
	static{
		allCommands.add("begin");
		allCommands.add("component");
		allCommands.add("end");
	}
	@Override
	public List<String> getAllCommands() {
		return allCommands;
	}
}
