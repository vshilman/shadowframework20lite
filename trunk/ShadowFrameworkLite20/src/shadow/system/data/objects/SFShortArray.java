package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFShortArray extends SFPrimitiveType{

	private short[] shortValues;
	
	public SFShortArray(int shortValuesSize) {
		super();
		this.shortValues = new short[shortValuesSize];
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
	public SFShortArray clone(){
		return new SFShortArray(shortValues.length);
	}
}
