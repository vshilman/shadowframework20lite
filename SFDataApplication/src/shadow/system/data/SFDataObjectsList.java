package shadow.system.data;

import java.util.LinkedList;

//TODO don't like this using a LinkedList
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
		clear();
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			add((T)type.copyDataObject());
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
	public SFDataObjectsList<T> copyDataObject(){
		return new SFDataObjectsList<T>(type);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addCharSetObjects(String value) {
		T element = (T) type.copyDataObject();
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
