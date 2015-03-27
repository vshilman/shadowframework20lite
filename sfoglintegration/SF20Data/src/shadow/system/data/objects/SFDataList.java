package shadow.system.data.objects;

import java.util.ArrayList;

import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

@SuppressWarnings("unchecked")
public class SFDataList<T extends SFWritableDataObject> implements SFDataObject,SFWritableDataObject{

	private ArrayList<T> dataObject=new ArrayList<T>();
	private T type;
	
	public SFDataList(T type) {
		super();
		this.type = type;
	}

	public ArrayList<T> getDataObject() {
		return dataObject;
	}

	public int elementsSize() {
		return dataObject.size();
	}
	
	public int size(){
		return dataObject.size();
	}
	
	public T get(int index){
		return dataObject.get(index);
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		//System.err.println("Data List n="+n);
		for (int i = 0; i < n; i++) {
			dataObject.add((T)type.copyDataObject());
			dataObject.get(i).readFromStream(stream);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)(dataObject.size()));
		for (int i = 0; i < dataObject.size(); i++) {
			dataObject.get(i).writeOnStream(stream);
		}
	}
	
	@Override
	public SFDataList<T> copyDataObject(){
		return new SFDataList<T>(type);
	}
	
	public void add(int index, T element) {
		dataObject.add(index,element);
	}
	
	public boolean add(T e) {
		return dataObject.add(e);
	}
	
	@Override
	public void setStringValue(String value) {
		String[] splits=value.split(" ");
		for (int i = 0; i < splits.length; i++) {
			T element=(T)type.copyDataObject();
			element.setStringValue(splits[i]);
			dataObject.add(element);
		}
	}
	
	@Override
	public String toStringValue() {
		if(dataObject.size()>0){
			String message=dataObject.get(0).toStringValue();
			for (int i = 1; i < dataObject.size(); i++) {
				message+=" "+dataObject.get(i).toStringValue();
			}
			return message;
		}
		return "";
	}
	
	public <U extends Object> U[] toArray(U[] a) {
		return dataObject.toArray(a);
	}

}
