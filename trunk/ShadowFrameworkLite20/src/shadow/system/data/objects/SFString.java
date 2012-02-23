package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFString extends SFPrimitiveType {

	private String label;
	
	public SFString(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		label=stream.readString();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(label);
	}
	

	@Override
	public SFString clone() {
		return new SFString(label);
	}
}
