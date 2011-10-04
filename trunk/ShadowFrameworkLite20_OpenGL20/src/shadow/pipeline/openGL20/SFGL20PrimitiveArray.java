package shadow.pipeline.openGL20;

import java.util.Collection;
import java.util.Iterator;

import shadow.math.SFValuenf;
import shadow.pipeline.SFArrayElementException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;

public class SFGL20PrimitiveArray extends SFGL20ListData<SFPrimitiveIndices> implements SFPrimitiveArray{

	private SFPrimitive primitive;
	private SFGL20ListData primitiveData[];
	
	public SFGL20PrimitiveArray(SFPrimitive primitive) {
		super();
		this.primitive = primitive;
		
		Collection<SFPipelineRegister> wroteRegisters=primitive.getPrimitiveMap().keySet();
		primitiveData=new SFGL20ListData[wroteRegisters.size()];	
		int index=0;
		for (Iterator<SFPipelineRegister> iterator = wroteRegisters.iterator(); iterator.hasNext();index++) {
			SFPipelineRegister sfPipelineRegister = iterator.next();
			if(sfPipelineRegister.getType()==SFParameteri.GLOBAL_FLOAT3){
				primitiveData[index]=new SFGL20Vertex3fArray();
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
	protected SFPrimitiveIndices generateGenericElement() {
		return new SFPrimitiveIndices(primitive);
	}
	
	@Override
	public SFGL20ListData<SFValuenf> getPrimitiveData(int index) {
		return primitiveData[index];
	}
}
