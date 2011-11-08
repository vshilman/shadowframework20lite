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
    aString with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package shadow.system.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * @author Alessandro Martinelli
 * 
 * @param <E>
 */
public class SFList implements SFDataset {

	public HashMap<String, SFDataset> elements = new HashMap<String, SFDataset>();
	public String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getN() {
		return elements.size();
	}

	public void removeElement(String code) {
		this.elements.remove(code);
	}

	public void addElement(String code, SFDataset element) {
		this.elements.put(code, element);
	}

	public SFDataset getElement(String code) {
		return elements.get(code);
	}

	public Map<String, SFDataset> getElements() {
		return elements;
	}

	public String[] getAllKeys() {

		Set<String> keys = elements.keySet();

		String[] data = new String[keys.size()];

		Iterator<String> it = keys.iterator();

		for (int i = 0; i < data.length; i++) {
			String l = it.next();
			data[i] = l;
		}

		return data;
	}

	public Set<String> getKeys() {
		return elements.keySet();
	}

	public boolean hasId(String id) {
		boolean found = false;
		String[] codes = getAllKeys();
		for (int i = 0; i < codes.length; i++) {
			if (codes[i] == id) {
				found = true;
			}
		}
		return found;
	}

	/*
	 * public static SFDataset loadDataset(String idList, String idObject)
	 * { return Lists.get(idList).getElement(idObject); }
	 * 
	 * public static SFDataset loadDataset(String idList, String idObject)
	 * { return Lists.get(idList).getElement(idObject); }
	 * 
	 * public static void loadList(SFList<SFDataset> List) {
	 * Lists.put(List.getId(), List); }
	 * 
	 * public static SFList<SFDataset> loadList(String id) { return
	 * Lists.get(id); }
	 */

	public void readFromStream(SFInputStream stream) {

		int N = stream.readInt();
		String name = stream.readString();
		SFMemory.getMemory().loadList(name, this);

		for (int i = 0; i < N; i++) {
			String elementName = stream.readString();
			SFDataset dataset = SFStreamer.getStreamer().loadData(stream);
			addElement(elementName, dataset);
		}

	}

	@Override
	public void writeOnStream(SFOutputStream stream) {

		stream.writeInt(elements.size());
		stream.writeString(getId());

		Set<Entry<String, SFDataset>> entries = elements.entrySet();
		for (Iterator<Entry<String, SFDataset>> iterator = entries.iterator(); iterator
				.hasNext();) {
			Entry<String, SFDataset> entry = (Entry<String, SFDataset>) iterator
					.next();
			stream.writeString(entry.getKey());
			entry.getValue().writeOnStream(stream);
		}
	}

	@Override
	public String getCode() {
		return "List";
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFList();
	}

}
