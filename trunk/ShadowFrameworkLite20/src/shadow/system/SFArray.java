/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.system;

import shadow.pipeline.SFArrayElementException;


/**
 * This interface represent an indexed set of Elements.
 * 
 * @author Alessandro Martinelli 
 * @param <E> The Type of the elements in the array 
 */
public interface SFArray<E>{
	/** @return the number of elements in the array */
	public int getElementsCount();
	/** writes a <E> with the content of the element at a given position
	 * @param index the index at which element is 
	 * read
	 * @param element the element where to store data
	 * */
	public void getElement(int index,E element) throws SFArrayElementException;
	/** Writes the content of a given element at the given position
	 * @param index the index at which element is written
	 * @param element the element where data are read
	 */
	public void setElement(int index,E element) throws SFArrayElementException;
	
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
	
	/**Compile this array; while compiled, generate and erase methods are ignored
	 * and access to Array data with get/setElement should be faster.*/
	public abstract void setCompiledState();

	/**Put this array in editing mode. While in editing mode, 
	 * every operation is allowed, but access to the array may slow down.*/
	public abstract void setEditingState();
	
}