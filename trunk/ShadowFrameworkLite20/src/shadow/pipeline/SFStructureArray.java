package shadow.pipeline;

import shadow.math.SFValuenf;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public interface SFStructureArray extends SFArray<SFStructureData>{

	
	/** writes a <E> with the content of the element at a given position
	 * @param index the index at which element is 
	 * read
	 * @param element the element where to store data
	 * */
	public void getParameterValue(int index,int parametersIndex,SFValuenf element) throws SFArrayElementException;
	
	/** Writes the content of a given element at the given position
	 * @param index the index at which element is written
	 * @param element the element where data are read
	 */
	public void setParameterValue(int index,int parametersIndex,SFValuenf element) throws SFArrayElementException;
	
	
	public SFPipelineStructure getPipelineStructure();
}
