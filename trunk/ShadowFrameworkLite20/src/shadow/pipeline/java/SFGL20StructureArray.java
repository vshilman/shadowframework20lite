package shadow.pipeline.java;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.system.SFArrayElementException;

public class SFGL20StructureArray extends SFGL20ListData<SFStructureData> implements SFStructureArray{

	private SFPipelineStructure structure;
	
	public SFGL20StructureArray(SFPipelineStructure structure) {
		this.structure=structure;
	}
	
	@Override
	protected void assignValues(SFStructureData writing, SFStructureData reading)  throws SFArrayElementException{
		SFValuenf[] writingValues=writing.getValues();
		SFValuenf[] readingValues=reading.getValues();
		
		if(writingValues.length!=readingValues.length)
			throw new SFArrayElementException(writing, "Different Structure Size");
		
		for (int i = 0; i < readingValues.length; i++) {
			if(readingValues[i].getSize()!=writingValues[i].getSize()){
				throw new SFArrayElementException(writing, "Different Value Size");
			}
			writingValues[i].set(readingValues[i].get());
		}
	}
	
	@Override
	protected SFStructureData generateGenericElement() {
		//TODO should use a prototype
		return new SFStructureData(structure);
	}
	
	@Override
	public SFPipelineStructure getPipelineStructure() {
		return structure;
	}
	
	
	/** writes a <E> with the content of the element at a given position
	 * @param index the index at which element is 
	 * read
	 * @param element the element where to store data
	 * */
	public void getParameterValue(int index,int parametersIndex,SFValuenf element) throws SFArrayElementException{
		
		SFValuenf value = findValue(index, parametersIndex, element);
		element.set(value.get());
	}

	private SFValuenf findValue(int index, int parametersIndex,
			SFValuenf element) throws SFArrayElementException {
		SFStructureData strData=data.get(index);
		if(strData.size()<=parametersIndex)
			throw new SFArrayElementException(strData, "Different Structure Size");
		SFValuenf value=strData.getValue(parametersIndex);
		if(value.getSize()!=element.getSize())
			throw new SFArrayElementException(strData, "Different Value Size");
		return value;
	}
	
	/** Writes the content of a given element at the given position
	 * @param index the index at which element is written
	 * @param element the element where data are read
	 */
	public void setParameterValue(int index,int parametersIndex,SFValuenf element) throws SFArrayElementException{

		SFValuenf value = findValue(index, parametersIndex, element);
		value.set(element.get());
	}
	
	@Override
	public void init() {
		//init will become necessary when we will use Object Buffer to store values data
	}
	
	@Override
	public void destroy() {
		//destroy will become necessary when we will use Object Buffer to store values data
	}
}
