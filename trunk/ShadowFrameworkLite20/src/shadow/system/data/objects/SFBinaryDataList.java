package shadow.system.data.objects;

import java.util.ArrayList;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

@SuppressWarnings("unchecked")
public class SFBinaryDataList<T extends SFBinaryValue> extends SFPrimitiveType {

	private ArrayList<T> dataObject = new ArrayList<T>();
	private T type;
	private int bitSize;

	public SFBinaryDataList(T type) {
		this.type=type;
		this.bitSize = type.getBitSize();
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
	public void readFromStream(SFInputStream stream) {
		int n=stream.readInt();
		int bitSize=stream.readInt();
		int[] data=stream.readBinaryData(n, bitSize);
		for (int i = 0; i < n; i++) {
			dataObject.add((T)(type.clone()));
			dataObject.get(i).setValue(data[i]);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(dataObject.size());
		stream.writeInt(getBitSize());
		int data[]=new int[dataObject.size()];
		for (int i = 0; i < dataObject.size(); i++) {
			data[i]=dataObject.get(i).getValue();
		}
		stream.writeBinaryData(data, bitSize);
	}

	@Override
	public SFPrimitiveType clone() {
		return new SFBinaryDataList<T>(type);
	}

}
