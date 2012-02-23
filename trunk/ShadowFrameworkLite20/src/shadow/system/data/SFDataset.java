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
 * A shadow Element which can be stored in or written from a BytesStream.
 * Any Dataset is even classified by its cardinality N; code and cardinality
 * are written before datasets as an header. Cardinality has different 
 * meanings, depending on the specific datasets, but usually it stays for 
 * 'number of sub-elements this element contains' 
 * @author Alessandro Martinelli
 */
public interface SFDataset {
	
	public String getType();
	
	public SFDataObject getSFDataObject();
	/** clone this Dataset
	 */
	public SFDataset generateNewDatasetInstance();
}