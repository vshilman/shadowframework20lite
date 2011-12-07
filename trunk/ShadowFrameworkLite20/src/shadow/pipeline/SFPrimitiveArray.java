package shadow.pipeline;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFArray;

/**
 * @author Alessandro Martinelli
 */
//wait: this is not correct. SFPrimitiveArray is not ok. It's not an indexed array 
public interface SFPrimitiveArray extends SFArray<SFPrimitiveIndices>{
	
	public SFArray<SFValuenf> getPrimitiveData(int index);
	
	/**
	 */
	public void setElementData(int index,SFPrimitiveIndices element,int registerIndex) throws SFArrayElementException;
	
	public SFPipelineRegister[] getRegisters();
}