package shadow.system.data.objects;

import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFFloatArray implements SFCharsetObject,SFDataObject{

	private float[] floatValues;
	
	public SFFloatArray(int floatValuesSize) {
		super();
		this.floatValues = new float[floatValuesSize];
	}

	
	public float[] getFloatValues() {
		return floatValues;
	}

	public void setFloatValues(float[] FloatValues) {
		this.floatValues = FloatValues;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		floatValues=stream.readFloats(n);
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)floatValues.length);
		stream.writeFloats(floatValues);
	}
	
	@Override
	public SFFloatArray copyDataObject(){
		return new SFFloatArray(floatValues.length);
	}
	

	@Override
	public void setStringValue(String value) {
		SFCharsetObjectUtils.readFloats(floatValues, value, this.getClass().getSimpleName());
	}
	
	@Override
	public String toStringValue() {
		return SFCharsetObjectUtils.writeFloats(floatValues);
	}
}
