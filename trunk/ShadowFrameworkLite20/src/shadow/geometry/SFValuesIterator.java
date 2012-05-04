package shadow.geometry;

import shadow.math.SFValuenf;

/**
 * An iterator over a set of Values
 * 
 * @author Alessandro Martinelli
 * 
 * @param <T>
 */
public interface SFValuesIterator<T extends SFValuenf> {
	/**
	 * @return true when there are still available values
	 */
	public boolean hasNext();
	/**
	 * Return a Value beholding to a Collection; 
	 * @return the next available value.
	 */
	public void getNext(T write);
}
