package shadow.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import shadow.pipeline.SFGridModel;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFProgramComponent;

public class SFParsablePrimitive extends SFPrimitive implements SFBuilderElement{

	private List<SFProgramComponent> components=new ArrayList<SFProgramComponent>();
	private List<SFPrimitiveBlock> blocks=new ArrayList<SFPrimitiveBlock>();
	private  SFGridModel gridModel;
	
	public SFParsablePrimitive() {
		super();
	}
	
	@Override
	public void finalize() {
		SFPrimitive primitive=new SFPrimitive("",gridModel);
		
		SFProgramComponent[] modules=components.toArray(new SFProgramComponent[components.size()]);
		SFPrimitiveBlock[] registers=blocks.toArray(new SFPrimitiveBlock[blocks.size()]);
		primitive.setPrimitiveElements(registers, modules);
		
		primitive.setName(getName());
		SFPipeline.loadPrimitive(getName(), primitive);
	}
	
	public void addComponent(SFPrimitiveBlock block,SFProgramComponent component){
		components.add(component);
		blocks.add(block);
	}
	
	public void setGridModel(SFGridModel gridModel) {
		this.gridModel = gridModel;
	}


	private static ArrayList<String> allCommands=new ArrayList<String>();
	static{
		allCommands.add("domain");
		allCommands.add("block");
		allCommands.add("begin");
		allCommands.add("end");
	}
	@Override
	public List<String> getAllCommands() {
		return allCommands;
	}
}
