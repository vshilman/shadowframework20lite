package shadow.pipeline.loader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.parameters.SFParameteri;

public class SFParsableStructure extends SFPipelineStructure implements SFParsableElement{

	private static LinkedList<SFParameteri> loadingParameters=new LinkedList<SFParameteri>();

	public void addParameter(SFParameteri parameter){
		loadingParameters.add(parameter);
	}
	
	@Override
	public void finalize() {
		addParameters(loadingParameters);
		loadingParameters.clear();
		SFPipeline.loadStructure(getName(), this);
	}

	private static ArrayList<String> allCommands=new ArrayList<String>();
	static{
		allCommands.add("begin");
		allCommands.add("float");
		allCommands.add("float2");
		allCommands.add("float3");
		allCommands.add("float4");
		allCommands.add("matrix2");
		allCommands.add("matrix3");
		allCommands.add("matrix4");
		allCommands.add("transform2");
		allCommands.add("transform3");
		allCommands.add("end");
	}
	
	@Override
	public List<String> getAllCommands() {
		return allCommands;
	}
}
