package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFVectorData implements SFWritableDataObject{

	protected float[] floatValues;
	
	protected SFVectorData(int floatValuesSize) {
		this.floatValues = new float[floatValuesSize];
	}
	
	protected SFVectorData() {
	}

	public float[] getFloatValues() {
		return floatValues;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		float[] values=stream.readFloats(floatValues.length);
		for (int i = 0; i < values.length; i++) {
			floatValues[i]=values[i];
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeFloats(floatValues);
	}

	@Override
	public SFVectorData copyDataObject(){
		return new SFVectorData(floatValues.length);
	}
	
	@Override
	public void setStringValue(String value) {
		SFCharsetObjectUtils.readFloats(floatValues, value, getClass().getSimpleName());
	}
	
	@Override
	public String toStringValue() {
		return SFCharsetObjectUtils.writeFloats(floatValues);
	}
}
