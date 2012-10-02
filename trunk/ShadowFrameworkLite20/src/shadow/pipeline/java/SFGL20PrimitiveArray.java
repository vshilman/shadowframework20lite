package shadow.pipeline.java;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public class SFGL20PrimitiveArray extends SFGL20ListData<SFPrimitiveIndices> implements SFPrimitiveArray{

	private SFPrimitive primitive;
	private SFGL20ValuenfArray primitiveData[];
	
	private SFGL20PrimitiveArray() {
	}
	
	public SFGL20PrimitiveArray(SFPrimitive primitive) {
		super();
		this.primitive = primitive;
		
		primitiveData=new SFGL20ValuenfArray[primitive.getGridsCount()];
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			short type=primitive.getType(gridIndex);
			switch(type){
				case SFParameteri.GLOBAL_FLOAT: primitiveData[gridIndex]=new SFGL20ValuenfArray(1); break;
				case SFParameteri.GLOBAL_FLOAT2: primitiveData[gridIndex]=new SFGL20ValuenfArray(2); break;
				case SFParameteri.GLOBAL_FLOAT3: primitiveData[gridIndex]=new SFGL20ValuenfArray(3); break;
			}
		}
	}
	
	public SFGL20PrimitiveArray getView(SFPrimitive primitive){
		SFGL20PrimitiveArray primitiveArray=new SFGL20PrimitiveArray();
		primitiveArray.primitive=primitive;
		primitiveArray.primitiveData=primitiveData;
		return primitiveArray;
	}
	
	public static SFGL20PrimitiveArray mixArrays(SFPrimitiveArray[] arrays,SFPrimitive mixPrimitive){

		int totalComponents=0;
		int totalGrids=0;
		for (int i = 0; i < arrays.length; i++) {
			totalComponents+=arrays[i].getPrimitive().getComponents().length;
			totalGrids+=arrays[i].getPrimitive().getGridsCount();
		}
		
		SFProgramComponent[] components=new SFProgramComponent[totalComponents];
		SFPrimitiveBlock[] blocks=new SFPrimitiveBlock[totalComponents];
		
		int primitiveIndex=0;
		int inPrimitiveIndex=0;
		for (int componentIndex = 0; componentIndex < totalComponents; componentIndex++) {
			components[componentIndex]=arrays[primitiveIndex].getPrimitive().getComponents()[inPrimitiveIndex];
			blocks[componentIndex]=arrays[primitiveIndex].getPrimitive().getBlocks()[inPrimitiveIndex];
			inPrimitiveIndex++;
			if(inPrimitiveIndex>=arrays[primitiveIndex].getPrimitive().getComponents().length){
				primitiveIndex++;
				inPrimitiveIndex=0;
			}
		}
		
		mixPrimitive.setPrimitiveElements(blocks, components);
		
		SFGL20PrimitiveArray array=new SFGL20PrimitiveArray();
		array.primitive=mixPrimitive;
		
		array.primitiveData=new SFGL20ValuenfArray[totalGrids];
		
		primitiveIndex=0;
		inPrimitiveIndex=0;
		for (int gridIndex = 0; gridIndex < totalGrids; gridIndex++) {

			array.primitiveData[gridIndex]=(SFGL20ValuenfArray)(arrays[primitiveIndex].getPrimitiveData(inPrimitiveIndex));
			inPrimitiveIndex++;
			if(inPrimitiveIndex>=arrays[primitiveIndex].getPrimitive().getComponents().length){
				primitiveIndex++;
				inPrimitiveIndex=0;
			}
		}
		
		for (int i = 0; i < arrays[0].getElementsCount(); i++) {
			int[]  indices=new int[mixPrimitive.getIndicesCount()];
			
			int indicesIndex=0;
			for (int j = 0; j < arrays.length; j++) {
				int[] oldIndices=((SFGL20PrimitiveArray)(arrays[j])).data.get(i).getPrimitiveIndices();
				for (int k = 0; k < oldIndices.length; k++) {
					indices[indicesIndex+k]=oldIndices[k];
				}
				indicesIndex+=oldIndices.length;
			}
				 
			SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
			prIndices.setPrimitiveIndices(indices);
			array.data.add(prIndices);
		}
		
		
		return array;
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
	public SFArray<SFValuenf> getPrimitiveData(int gridIndex) {
		return (SFArray<SFValuenf>)(primitiveData[gridIndex]);
	}

	public SFGL20ValuenfArray[] getPrimitiveData() {
		return primitiveData;
	}
	
	@Override
	public void init() {
		//init will become necessary when we will use Object Buffer to store values data
	}
	
	@Override
	public void destroy() {
		//destroy will become necessary when we will use Object Buffer to store values data
	}
	
	public SFPrimitive getPrimitive() {
		return primitive;
	}

}
