package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFFloat extends SFPrimitiveType {

	private float floatValue;
	
	public SFFloat() {
		this.floatValue = 0;
	}
	
	public SFFloat(float floatValue) {
		this.floatValue = floatValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		floatValue=stream.readFloat();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeFloat(floatValue);
	}
	
	@Override
	public SFFloat clone() {
		return new SFFloat(floatValue);
	}
}
