package shadow.geometry;

import shadow.math.SFValuenf;
import shadow.system.SFInitiable;

/**
 * An indexed List of Values.
 * Optionally, you can use a {@link SFValuesIterator} to efficiently iterate
 * on all Collection of values. 
 * 
 * Implementation should provide more efficient ways to extract values
 * through an iterator 
 * 
 * @author Alessandro Martinelli
 *
 * @param <T>
 */
public interface SFValuesList<T extends SFValuenf> extends SFInitiable{

	public int getSize();
	
	public void getValue(int index,T write);
	
	public void setValue(int index,T read);
	
	public int addValue(T write);
	
	public SFValuesIterator<T> getIterator();
	
}
