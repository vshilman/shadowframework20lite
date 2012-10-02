package shadow.system.data.objects;

import java.util.ArrayList;

import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFBinaryDataList<T extends SFBinaryValue> extends SFPrimitiveType implements
		SFCharsetObjectList {

	private ArrayList<T> dataObject = new ArrayList<T>();
	private T type;
	private int bitSize;

	public SFBinaryDataList(T type) {
		this.type = type;
		this.bitSize = type.getBitSize();
	}
	
	public void setType(T type) {
		this.type = type;
	}

	public int getBitSize() {
		return bitSize;
	}

	public ArrayList<T> getDataObject() {
		return dataObject;
	}

	@Override
	public int elementsSize() {
		return dataObject.size();
	}

	public T getType() {
		return type;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		int n = stream.readInt();
		// int bitSize=stream.readInt();
		int[] data = stream.readBinaryData(n, bitSize);
		for (int i = 0; i < n; i++) {
			dataObject.add((T) (type.clone()));
			dataObject.get(i).setValue(data[i]);
		}
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(dataObject.size());
		// stream.writeInt(getBitSize());
		int data[] = new int[dataObject.size()];
		for (int i = 0; i < dataObject.size(); i++) {
			data[i] = dataObject.get(i).getValue();
		}
		stream.writeBinaryData(data, bitSize);
	}

	@Override
	public SFPrimitiveType clone() {
		return new SFBinaryDataList<T>(type);
	}

//	// @Override
//	public void setStringValue(String value) {
//		SFStringTokenizerInputStream stream = new SFStringTokenizerInputStream(value);
//		int size = stream.readInt();
//		SFBinaryValue[] objects = new SFBinaryValue[size];
//		for (int i = 0; i < objects.length; i++) {
//			objects[i] = type.clone();
//		}
//		SFCharsetObjectUtils.readCharsetObjects(objects, stream.readString(), getClass()
//				.getSimpleName());
//		for (int i = 0; i < objects.length; i++) {
//			this.dataObject.add((T) (objects[i]));
//		}
//	}

//
// @Override
// public String toStringValue() {
// SFStringWriterStream stream = new SFStringWriterStream();
// stream.writeInt(dataObject.size());
//
// SFCharsetObject data[] = new SFCharsetObject[dataObject.size()];
// for (int i = 0; i < dataObject.size(); i++) {
// data[i] = dataObject.get(i);
// }
// stream.writeString(SFCharsetObjectUtils.writeCharsetObjects(data));
// return stream.getString();
// }

	@Override
	public int getSize() {
		return dataObject.size();
	}

	@Override
	public String getCharSetObjectString(int index) {
		return dataObject.get(index).toStringValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addCharSetObjects(String value) {
		T object = (T) type.clone();
		object.setStringValue(value);
		this.dataObject.add(object);
	}
}
