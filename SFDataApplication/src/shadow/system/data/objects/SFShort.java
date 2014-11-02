package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFShort implements SFWritableDataObject{

	protected short shortValue;
	
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
	public SFShort copyDataObject(){
		return new SFShort(shortValue);
	}
	
	@Override
	public void setStringValue(String value) {
		shortValue=new Short(value);
	}
	
	@Override
	public String toStringValue() {
		return shortValue+"";
	}
}
