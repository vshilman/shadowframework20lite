package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFFloat implements SFWritableDataObject{

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
	public SFFloat copyDataObject() {
		return new SFFloat(floatValue);
	}
	
	@Override
	public void setStringValue(String value) {
		floatValue=new Float(value);
	}
	
	@Override
	public String toStringValue() {
		return floatValue+"";
	}
}
