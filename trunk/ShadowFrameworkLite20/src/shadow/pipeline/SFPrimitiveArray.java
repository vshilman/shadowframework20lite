package shadow.pipeline;

import shadow.math.SFValuenf;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;
import shadow.system.SFInitiable;

/**
 * @author Alessandro Martinelli
 */
//wait: this is not correct. SFPrimitiveArray is not ok. It's not an indexed array 
public interface SFPrimitiveArray extends SFArray<SFPrimitiveIndices>,SFInitiable{
	
	public SFArray<SFValuenf> getPrimitiveData(int gridIndex);
	
	public SFPrimitive getPrimitive();
	
	/**
	 */
	public void setElementData(int index,SFPrimitiveIndices element,int registerIndex) throws SFArrayElementException;

}