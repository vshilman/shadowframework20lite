package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFVectorData extends SFPrimitiveType{

	private float[] floatValues;
	
	protected SFVectorData(int floatValuesSize) {
		this.floatValues = new float[floatValuesSize];
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
	public SFVectorData clone(){
		return new SFVectorData(floatValues.length);
	}
}
