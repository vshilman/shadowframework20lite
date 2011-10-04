package shadow.pipeline.loader;

import java.util.ArrayList;
import java.util.Collection;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgramComponent;

public class SFParsableProgramComponent extends SFProgramComponent implements SFParsableElement{

	@Override
	public void finalize() {
		SFPipeline.loadShaderComponent(getName(), this);
	}

	private static ArrayList<String> allCommands=new ArrayList<String>();
	static{
		allCommands.add("begin");
		allCommands.add("use");
		allCommands.add("write");
		allCommands.add("param");
		allCommands.add("grid");
		allCommands.add("define");
		allCommands.add("end");
	}
	@Override
	public Collection<String> getAllCommands() {
		return allCommands;
	}
}
