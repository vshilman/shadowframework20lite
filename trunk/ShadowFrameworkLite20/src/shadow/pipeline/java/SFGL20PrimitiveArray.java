package shadow.pipeline.java;

import java.util.ArrayList;
import java.util.List;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.operational.SFExtruder;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveGrid;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public class SFGL20PrimitiveArray extends SFGL20ListData<SFPrimitiveIndices> implements SFPrimitiveArray{

	private SFPrimitive primitive;
	private SFGL20ValuenfArray primitiveData[];
	
	public SFGL20PrimitiveArray(SFPrimitive primitive) {
		super();
		this.primitive = primitive;
		
		SFPrimitiveGrid[] grids=primitive.getGridInstances();
		primitiveData=new SFGL20ValuenfArray[grids.length];
		for (int i = 0; i < grids.length; i++) {
			short type=grids[i].getType();
			switch(type){
				case SFParameteri.GLOBAL_FLOAT: primitiveData[i]=new SFGL20ValuenfArray(1); break;
				case SFParameteri.GLOBAL_FLOAT2: primitiveData[i]=new SFGL20ValuenfArray(2); break;
				case SFParameteri.GLOBAL_FLOAT3: primitiveData[i]=new SFGL20ValuenfArray(3); break;
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
			int gridIndex) throws SFArrayElementException {
		
		data.get(index).setData(element,primitive.getIndicesPositions()[gridIndex],
				primitive.getIndicesSizes()[gridIndex]);
	}
	
	@Override
	protected SFPrimitiveIndices generateGenericElement() {
		return new SFPrimitiveIndices(primitive);
	}
	
	@Override
	public int getPrimitiveDataCount(int index) {
		return 0;
	}
	
	@Override
	public SFArray<SFValuenf> getPrimitiveData(int gridIndex) {
		return (SFArray<SFValuenf>)(primitiveData[gridIndex]);
	}

	public SFGL20ValuenfArray[] getPrimitiveData() {
		return primitiveData;
	}
	
	@Override
	public void init() {
		
	}
	
	public SFPrimitive getPrimitive() {
		return primitive;
	}
	
	@Override
	public SFPrimitiveIndices generateSample() {
		return new SFPrimitiveIndices(primitive);
	}

	//TODO : to be completely reworked
	@Override
	public int[] extrude(int index, int size,SFVertex3f extrusionVector) {
		List<SFPrimitiveIndices> indices=new ArrayList<SFPrimitiveIndices>();
		indices.addAll(data.subList(index, index+size));
		return SFExtruder.extrudeModel(this,index, primitiveData, indices,extrusionVector);
	}
	
}
