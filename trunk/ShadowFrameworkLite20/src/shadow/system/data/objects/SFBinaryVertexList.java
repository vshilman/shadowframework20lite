package shadow.system.data.objects;

import java.util.ArrayList;

import shadow.math.SFValuenf;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFBinaryVertexList<T extends SFGenericFixedFloat> extends SFPrimitiveType implements
		SFCharsetObjectList {

	//TODO i would appreciate if this was an array of integers instead.. or shorts..
	private ArrayList<T> dataObject = new ArrayList<T>();
	private T type;
	private int vertexSize=-1;
	private int bitSize;

	public SFBinaryVertexList(T type) {
		this.type = type;
		this.bitSize = type.getBitSize();
	}
	
	public void setType(T type) {
		this.type = type;
	}

	public int getBitSize() {
		return bitSize;
	}

	public int getVertexCount(){
		return dataObject.size()/vertexSize;
	}
	
	public void setVertexSize(int vertexSize) {
		this.vertexSize = vertexSize;
	}

	public void addValue(SFValuenf value){
		addVertex(value.get());
	}
	
	public SFValuenf getValue(int index,SFValuenf value){
		for (int i = 0; i < vertexSize && i<value.get().length; i++) {
			value.get()[i]=dataObject.get(index*vertexSize+i).getFloat();
		}
		return value;
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
		int n = stream.readShort();
		vertexSize=stream.readByte();
		// int bitSize=stream.readInt();
		int[] data = stream.readBinaryData(n, bitSize);
		for (int i = 0; i < n; i++) {
			dataObject.add((T) (type.clone()));
			dataObject.get(i).setValue(data[i]);
		}
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)dataObject.size());
		stream.writeByte(vertexSize);
		int data[] = new int[dataObject.size()];
		for (int i = 0; i < dataObject.size(); i++) {
			data[i] = dataObject.get(i).getValue();
		}
		stream.writeBinaryData(data, bitSize);
	}

	@Override
	public SFPrimitiveType clone() {
		return new SFBinaryVertexList<T>(type);
	}

	@Override
	public int getSize() {
		return dataObject.size()/vertexSize;
	}

	@Override
	public String getCharSetObjectString(int index) {
		float[] f=new float[vertexSize];
		for (int i = 0; i < f.length; i++) {
			f[i]=dataObject.get(index*vertexSize+i).getFloat();
		}
		return SFCharsetObjectUtils.writeFloats(f);
	}

	@Override
	public void addCharSetObjects(String value) {
		float[] fs=SFCharsetObjectUtils.readFloats(value, this.getClass().getSimpleName());
		addVertex(fs);
	}

	@SuppressWarnings("unchecked")
	public void addVertex(float[] fs) {
		for (int i = 0; i < fs.length; i++) {
			T t=(T)type.clone();
			t.setFloat(fs[i]);
			dataObject.add(t);
		}
		if(vertexSize<=0){
			vertexSize=fs.length;
		}
		
	}
}
