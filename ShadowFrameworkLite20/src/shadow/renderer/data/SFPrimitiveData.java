package shadow.renderer.data;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFProgramComponent;
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
		primitive.setAdaptingTessellator((SFProgramComponent)SFPipeline.getModule(primitiveData.get(index).getString()));
		index++;
		
		try {
			int size=(primitiveData.size()-1)>>1;
			SFProgramComponent[] components=new SFProgramComponent[size];
			PrimitiveBlock[] blocks=new PrimitiveBlock[size];
			for (int i = 0; i < size; i++) {
				blocks[i]=PrimitiveBlock.getBlock(new Integer(primitiveData.get(index).getString()));//SFPipelineRegister.getFromName(primitiveData.get(index).getLabel());
				index++;
				components[i]=(SFProgramComponent)SFPipeline.getModule(primitiveData.get(index).getString());
				index++;
			}
			primitive.setPrimitiveElements(blocks, components);
		} catch (SFException e) {
			e.printStackTrace();
		}
	}
	
	private void retrievePrimitiveData(){
		primitiveData.clear();
		primitiveData.add(new SFString(primitive.getTessellator().getName()));
		
		SFProgramComponent[] components=primitive.getComponents();
		PrimitiveBlock[] blocks=primitive.getBlocks();
		
		for (int i = 0; i < blocks.length; i++) {
			primitiveData.add(new SFString(""+blocks[i].getIndex()));
			primitiveData.add(new SFString(components[i].getName()));
		}
		
	}
}
