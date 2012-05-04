package shadow.pipeline;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;
import shadow.system.SFInitiable;

/**
 * @author Alessandro Martinelli
 */
//wait: this is not correct. SFPrimitiveArray is not ok. It's not an indexed array 
public interface SFPrimitiveArray extends SFArray<SFPrimitiveIndices>,SFInitiable{
	
	public SFArray<SFValuenf> getPrimitiveData(int gridIndex);
	
	public int getPrimitiveDataCount(int index);
	
	public SFPrimitive getPrimitive();
	
	/**
	 */
	public void setElementData(int index,SFPrimitiveIndices element,int registerIndex) throws SFArrayElementException;
	
	//TODO : to be completely reworked
	public int[] extrude(int index,int size,SFVertex3f extrusionVector);
}