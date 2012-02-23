package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFIntArray extends SFPrimitiveType{

	private int[] intValues;
	
	
	public SFIntArray(int intValuesSize) {
		super();
		this.intValues = new int[intValuesSize];
	}

	public int[] getIntValues() {
		return intValues;
	}
	
	

	public void setIntValues(int[] intValues) {
		this.intValues = intValues;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		intValues=stream.readInts(n);
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)intValues.length);
		stream.writeInts(intValues);
	}

	@Override
	public SFIntArray clone(){
		return new SFIntArray(intValues.length);
	}
}
