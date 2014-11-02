package shadow.system;


/**
 * This interface represent an indexed set of Elements.
 * 
 * @author Alessandro Martinelli 
 * @param <E> The Type of the elements in the array 
 */
public interface SFArray<E>{
	
	/** @return the number of elements in the array */
	public abstract int getElementsCount();

	/** writes a <E> with the content of the element at a given position
	 * @param index the index at which element is 
	 * read
	 * @param element the element where to store data
	 * */
	public abstract void getElement(int index, E element);
	
	/**
	 * Generate a valid instance of E
	 * @return
	 */
	public E generateSample();
	
	/** Writes the content of a given element at the given position
	 * @param index the index at which element is written
	 * @param element the element where data are read
	 */
	public void setElement(int index,E element);
	
	/** Generate a new Element in the Array 
	 * @return the index of the position in which the element has been added, or -1 if this array does not allow elements generation*/
	public abstract int generateElement();
	/** Generate a new Element in the Array
	 * @param count number of elements to be destroyed 
	 * @return the index of the position in which the element has been added, or -1 if this array does not allow elements generation*/
	public abstract int generateElements(int count);
	
	/**Destroy a set of consecutive elements
	 * @param index starting position at which elements will be destroyed
	 * @param elementsCount the number of elements which will be destroyed*/
	public abstract void eraseElements(int index,int elementsCount);

}