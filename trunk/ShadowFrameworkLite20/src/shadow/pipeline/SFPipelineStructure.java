package shadow.pipeline;

import java.util.Collection;
import java.util.LinkedList;

import shadow.pipeline.parameters.SFParameteri;

/**
 * @author Alessandro Martinelli
 */
public class SFPipelineStructure extends SFPipelineElement{

	private LinkedList<SFParameteri> parameters=new LinkedList<SFParameteri>();
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int size(){
		return parameters.size();
	}
	
	public Collection<SFParameteri> getAllParameters(){
		return parameters;
	}
	
	public void addParameter(SFParameteri param){
		parameters.add(param);
	}
	
	public void addParameters(Collection<SFParameteri> parameters){
		this.parameters.addAll(parameters);
	}
	
	public int floatSize(){
		int floatSize=0;
		for (int i=0; i < parameters.size(); i++) {
			SFParameteri parameter=parameters.get(i);
			switch (parameter.getType()) {
				case SFParameteri.GLOBAL_FLOAT:
					floatSize++;break;
				case SFParameteri.GLOBAL_FLOAT2:
					floatSize+=2;break;
				case SFParameteri.GLOBAL_FLOAT3:
					floatSize+=3;break;
				case SFParameteri.GLOBAL_FLOAT4:
					floatSize+=4;break;
				case SFParameteri.GLOBAL_TEXTURE:
					floatSize++;break;
			}
		}
		return floatSize;
	}
}