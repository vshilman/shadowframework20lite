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
package shadow.system.data;


/**
 * An Object which can be stored in a ShadowFramework stream.
 *  
 * @author Alessandro Martinelli
 */
public interface SFDataset {
	
	/**
	 * Return a String identifying the type of this SFDataset. Objects from the same class should return
	 * the same String.
	 * @return  the identifying String
	 */
	public String getType();
	
	/**
	 * Return the SFDataObject effectively containing data for this SFDataset
	 * @return
	 */
	public SFDataObject getSFDataObject();
	
	/** generate a new Instance of the same class as this Dataset
	 */
	public SFDataset generateNewDatasetInstance();

	public void invalidate();
}