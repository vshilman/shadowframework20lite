package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFShortArray implements SFWritableDataObject{

	private short[] shortValues;
	
	public SFShortArray(short[] shortValues) {
		super();
		this.shortValues = shortValues;
	}

	public short[] getShortValues() {
		return shortValues;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readInt();
		shortValues=stream.readShorts(n);
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeInt(shortValues.length);
		stream.writeShorts(shortValues);
	}
	
	@Override
	public SFShortArray copyDataObject(){
		return new SFShortArray(new short[shortValues.length]);
	}
	

	@Override
	public void setStringValue(String value) {
		shortValues=SFCharsetObjectUtils.readShorts(value, getClass().getSimpleName());
	}
	
	@Override
	public String toStringValue() {
		return SFCharsetObjectUtils.writeShorts(shortValues);
	}
}
