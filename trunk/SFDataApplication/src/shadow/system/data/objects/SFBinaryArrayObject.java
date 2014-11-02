package shadow.system.data.objects;

import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFBinaryArrayObject implements SFCharsetObject,SFDataObject{

	private int[] values;
	private int size;
	
	public SFBinaryArrayObject(int size) {
		super();
		this.size = size;
	}

	public int[] getBytes() {
		return values;
	}

	public void setBytes(int[] bytes) {
		this.values = bytes;
	}

	@Override
	public SFDataObject copyDataObject() {
		return new SFBinaryArrayObject(size);
	}
	
	public int[] getValues() {
		return values;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		short length=stream.readShort();
		this.values=stream.readBinaryData(length, size);
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)values.length);
		stream.writeBinaryData(values, size);
	}
	
	@Override
	public void setStringValue(String value) {
		this.values=SFCharsetObjectUtils.readInts( value, SFBinaryArrayObject.class.getSimpleName());
	}
	
	@Override
	public String toStringValue() {
		return SFCharsetObjectUtils.writeInts(values);
	}
	
}
