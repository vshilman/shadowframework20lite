package shadow.renderer.data;

import java.util.LinkedList;

import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFDataObjectsList<T extends SFWritableDataObject> extends LinkedList<T> implements SFDataObject,SFCharsetObjectList{

	private static final long serialVersionUID=0;
	
	private T type;
	
	public SFDataObjectsList(T type) {
		super();
		this.type = type;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			add((T)type.clone());
			get(i).readFromStream(stream);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)(size()));
		for (int i = 0; i < size(); i++) {
			get(i).writeOnStream(stream);
		}
	}
	
	@Override
	public SFDataObjectsList<T> clone(){
		return new SFDataObjectsList<T>(type);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addCharSetObjects(String value) {
		T element = (T) type.clone();
		element.setStringValue(value);
		add(element);
	}
	
	@Override
	public String getCharSetObjectString(int index) {
		return get(index).toStringValue();
	}
	
	@Override
	public int getSize() {
		return size();
	}
}
