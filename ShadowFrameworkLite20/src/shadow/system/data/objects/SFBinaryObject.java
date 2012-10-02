package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFBinaryObject<T extends SFBinaryValue> extends SFPrimitiveType{
	
	private T binaryValue;

	public SFBinaryObject(T binaryValue) {
		super();
		this.binaryValue = binaryValue;
	}

	public T getBinaryValue() {
		return binaryValue;
	}
	
	@Override
	
	public SFPrimitiveType clone() {
		return new SFBinaryObject<SFBinaryValue>(binaryValue.clone());
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		this.binaryValue.value=stream.readBinaryData(binaryValue.getBitSize());
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeBinaryData(binaryValue.getValue(), binaryValue.getBitSize());
	}
	
}
