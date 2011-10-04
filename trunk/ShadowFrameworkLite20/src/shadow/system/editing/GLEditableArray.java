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
package shadow.system.editing;

import java.util.ArrayList;

import shadow.system.SFArray;

public abstract class GLEditableArray<E> implements SFArray<E>{

	private ArrayList<GLEditableArrayCallback> callBacks=new ArrayList<GLEditableArrayCallback>();
	
	/**Destroy an element
	 * @param index position at which the element will be destroy*/
	public void deleteElement(int index) {
		eraseElements(index,1);
		for(GLEditableArrayCallback call: callBacks){
	       call.onDelete(index, 1);
	    }
	}
	/**Destroy a set of consecutive elements
	 * @param index starting position at which elements will be destroyed
	 * @param elementsCount the number of elements which will be destroyed*/
	public void deleteElements(int index,int elementsCount){
		eraseElements(index,elementsCount);
		for(GLEditableArrayCallback call: callBacks){
	       call.onDelete(index, elementsCount);
	    }
	}
	
	public void addCallBack(GLEditableArrayCallback callBack){
		callBacks.add(callBack);
	}
	
	public void removeCallBack(GLEditableArrayCallback callBack){
		callBacks.remove(callBack);
	}
}