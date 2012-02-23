package shadow.renderer.data;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFString;

public class SFPrimitiveData extends SFCompositeDataArray{

	private SFPrimitive primitive;
	private SFDataList<SFString> primitiveData;
	
	@Override
	public void generateData() {
		primitiveData=new SFDataList<SFString>(new SFString(""));
		addDataObject(primitiveData);
	}
	
	@Override
	public SFCompositeDataArray clone() {
		return new SFPrimitiveData();
	}

	public SFPrimitive getPrimitive() {
		if(primitive==null)
			setupPrimitive();
		return primitive;
	}

	public void setPrimitive(SFPrimitive primitive) {
		this.primitive = primitive;
		retrievePrimitiveData();
	}

	private void setupPrimitive(){
		int index=0;
		primitive=new SFPrimitive();
		primitive.setAdaptingTessellator((SFProgramComponent)SFPipeline.getModule(primitiveData.get(index).getLabel()));
		index++;
		List<Entry<SFPipelineRegister, SFProgramComponent>> primitiveMap=primitive.getPrimitiveMap();
		primitiveMap.clear();
		try {
			while(index<primitiveData.size()){
				SFPipelineRegister register=SFPipelineRegister.getFromName(primitiveData.get(index).getLabel());
				index++;
				SFProgramComponent programComponent=(SFProgramComponent)SFPipeline.getModule(primitiveData.get(index).getLabel());
				index++;
				Entry<SFPipelineRegister, SFProgramComponent> entry=
						new AbstractMap.SimpleEntry<SFPipelineRegister,SFProgramComponent>(register,programComponent);
				primitiveMap.add(entry);
			}
		} catch (SFException e) {
			e.printStackTrace();
		}
	}
	
	private void retrievePrimitiveData(){
		primitiveData.clear();
		primitiveData.add(new SFString(primitive.getTessellator().getName()));
		
		List<Entry<SFPipelineRegister, SFProgramComponent>> primitiveMap=primitive.getPrimitiveMap();
		for (Entry<SFPipelineRegister, SFProgramComponent> entry : primitiveMap) {
			primitiveData.add(new SFString(entry.getKey().getName()));
			primitiveData.add(new SFString(entry.getValue().getName()));
		}
	}
}
