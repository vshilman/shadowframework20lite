package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFShort extends SFPrimitiveType {

	private short shortValue;
	
	public SFShort(short shortValue) {
		super();
		this.shortValue = shortValue;
	}

	public short getShortValue() {
		return shortValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		shortValue=stream.readShort();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort(shortValue);
	}
	

	@Override
	public SFShort clone(){
		return new SFShort(shortValue);
	}
}
