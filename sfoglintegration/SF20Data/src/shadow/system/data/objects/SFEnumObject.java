package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFEnumObject<T> implements SFWritableDataObject{

	private T[] elements;
	private String[] names;
	private int index=0;
	
	public SFEnumObject(T[] elements, String[] names) {
		super();
		this.elements = elements;
		this.names = names;
	}
	public SFEnumObject(T[] elements, String[] names,int defaultIndex) {
		super();
		this.elements = elements;
		this.names = names;
		this.index=defaultIndex;
	}
	
	
	@Override
	public SFEnumObject<T> copyDataObject() {
		return new SFEnumObject<T>(elements, names,index);
	}
	
	public void setValue(T element){
		for (int i = 0; i < elements.length; i++) {
			if(elements[i]==element){
				index=i;
				i=elements.length;
			}
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		index=stream.readShort();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)index);
	}
	
	public T getElement(){
		return elements[index];
	}
	
	@Override
	public void setStringValue(String value) {
		for (int i = 0; i < names.length; i++) {
			if(value.equalsIgnoreCase(names[i])){
				index=i;
			}
		}
	}
	
	@Override
	public String toStringValue() {
		return names[index];
	}
}
