package shadow.pipeline.openGL20;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArrayElementException;

public class SFGL20PrimitiveArray extends SFGL20ListData<SFPrimitiveIndices> implements SFPrimitiveArray{

	private SFPrimitive primitive;
	private SFGL20ListData primitiveData[];
	private SFPipelineRegister registers[];
	
	public SFGL20PrimitiveArray(SFPrimitive primitive) {
		super();
		this.primitive = primitive;
		
		List<Entry<SFPipelineRegister, SFProgramComponent>> wroteRegisters=primitive.getPrimitiveMap();
		primitiveData=new SFGL20ListData[wroteRegisters.size()];
		registers=new SFPipelineRegister[wroteRegisters.size()];
		int index=0;
		for (Iterator<Entry<SFPipelineRegister, SFProgramComponent>> iterator = wroteRegisters.iterator(); iterator.hasNext();index++) {
			Entry<SFPipelineRegister, SFProgramComponent> entry=iterator.next();
			SFPipelineRegister sfPipelineRegister = entry.getKey();
			registers[index]=sfPipelineRegister;
			switch(sfPipelineRegister.getType()){
				case SFParameteri.GLOBAL_FLOAT3: primitiveData[index]=new SFGL20Vertex3fArray(); break;
				case SFParameteri.GLOBAL_FLOAT2: primitiveData[index]=new SFGL20Vertex2fArray(); break;
			}
		}
	}

	@Override
	protected void assignValues(SFPrimitiveIndices writing,
			SFPrimitiveIndices reading) throws SFArrayElementException {
		try {
			writing.set(reading);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFArrayElementException(writing, "Malstructured Primitive data");
		}
	}
	
	@Override
	public void setElementData(int index, SFPrimitiveIndices element,
			int registerIndex) throws SFArrayElementException {
		data.get(index).setData(element, registerIndex);
	}
	
	@Override
	protected SFPrimitiveIndices generateGenericElement() {
		return new SFPrimitiveIndices(primitive);
	}
	
	@Override
	public SFGL20ListData<SFValuenf> getPrimitiveData(int index) {
		return primitiveData[index];
	}
	
	@Override
	public SFPipelineRegister[] getRegisters() {
		return registers;
	}
	
	public SFGL20ListData<SFValuenf>[] getPrimitiveData() {
		return primitiveData;
	}
}
