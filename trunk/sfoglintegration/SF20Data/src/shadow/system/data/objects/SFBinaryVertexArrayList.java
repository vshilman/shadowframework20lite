package shadow.system.data.objects;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.math.SFValue;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFBinaryVertexArrayList<T extends SFGenericFixedFloat> implements
		SFCharsetObjectList,SFDataObject {

	private ArrayList<T> dataObject = new ArrayList<T>();
	private T type;
	private int[] vertexSize=new int[0];
	private int totalSize=-1;
	private int bitSize;

	public SFBinaryVertexArrayList(T type) {
		this.type = type;
		this.bitSize = type.getBitSize();
	}
	
	public int[] getVertexSize() {
		return vertexSize;
	}
	
	public void setType(T type) {
		this.type = type;
	}

	public int getBitSize() {
		return bitSize;
	}

	public int getVertexCount(){
		return dataObject.size()/totalSize;
	}
	
	public void setVertexSize(int[] vertexSize) {
		this.vertexSize = vertexSize;
	}

	public void addValue(SFValue[] value){
		float[][] values=new float[value.length][];
		for (int i = 0; i < values.length; i++) {
			values[i]=value[i].getV();
		}
		addVertex(values);
	}
	
	public void getValue(int index,int vertexIndex,float[] value){
		int pos=index*totalSize;
		for (int i = 0; i < vertexIndex; i++) {
			pos+=vertexSize[i];
		}
		for (int i = 0; i < vertexSize[vertexIndex]; i++) {
			value[i]=dataObject.get(pos+i).getFloat();
		}
	}
	
	public ArrayList<T> getDataObject() {
		return dataObject;
	}

	public T getType() {
		return type;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		int n = stream.readShort();
		vertexSize=new int[stream.readByte()];
		totalSize=0;
		for (int i = 0; i < vertexSize.length; i++) {
			vertexSize[i]=stream.readByte();
			totalSize+=vertexSize[i];
		}
		// int bitSize=stream.readInt();
		int[] data = stream.readBinaryData(n, bitSize);
		dataObject.clear();
		for (int i = 0; i < n; i++) {
			dataObject.add((T) (type.copyDataObject()));
			dataObject.get(i).setValue(data[i]);
		}
	}

	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)dataObject.size());
		stream.writeByte(vertexSize.length);
		for (int i = 0; i < vertexSize.length; i++) {
			stream.writeByte(vertexSize[i]);
		}
		int data[] = new int[dataObject.size()];
		for (int i = 0; i < dataObject.size(); i++) {
			data[i] = dataObject.get(i).getValue();
		}
		stream.writeBinaryData(data, bitSize);
	}

	@Override
	public SFBinaryVertexArrayList<T> copyDataObject() {
		return new SFBinaryVertexArrayList<T>(type);
	}
	
	@Override
	public void setStringValues(String[] values_) {
		for (int j = 0; j < values_.length; j++) {

			StringTokenizer tokenizer=new StringTokenizer(values_[j],"() \n",false);
			ArrayList<String> values=new ArrayList<String>();
			while (tokenizer.hasMoreTokens()) {
				values.add(tokenizer.nextToken());
			}
			float[][] fs=new float[values.size()][];
			for (int i = 0; i < fs.length; i++) {
				fs[i]=SFCharsetObjectUtils.readFloats(values.get(i), this.getClass().getSimpleName());
				//System.err.println("addCharSetObjects "+Arrays.toString(fs[i])+" "+value+" i:"+i);
			}
			
			addVertex(fs);
		}	
	}
	
	@Override
	public String[] toStringValues() {
		String[] values=new String[getSize()];
		for (int k = 0; k < values.length; k++) {
			values[k]="";
			int pos=k*totalSize;
			for (int i = 0; i < vertexSize.length; i++) {
				float[] f=new float[vertexSize[i]];
				for (int j = 0; j < f.length; j++) {
					f[j]=dataObject.get(pos+j).getFloat();
				}
				//System.err.println("getCharSetObjectString "+Arrays.toString(f));
				values[k]+=SFCharsetObjectUtils.writeFloats(f);
				pos+=vertexSize[i];
			}
		}
		return values;
	}
	
	public int getSize(){
		return dataObject.size()/totalSize;
	}

	@SuppressWarnings("unchecked")
	private void addVertex(float[][] fs) {
		for (int i = 0; i < fs.length; i++) {
			for (int j = 0; j < fs[i].length; j++) {
				T t=(T)type.copyDataObject();
				t.setFloat(fs[i][j]);
				dataObject.add(t);
			}
		}
		if(vertexSize.length==0){
			vertexSize=new int[fs.length];
			totalSize=0;
			for (int i = 0; i < fs.length; i++) {
				vertexSize[i]=fs[i].length;
				totalSize+=vertexSize[i];
			}
		}
	}
}
