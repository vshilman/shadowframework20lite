package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFString extends SFPrimitiveType {

	private String string;
	
	public SFString() {
		super();
		this.string = "";
	}
	
	public SFString(String label) {
		super();
		this.string = label;
	}

	public String getString() {
		return string;
	}

	public void setString(String label) {
		this.string = label;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		string=stream.readString();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(string);
	}
	

	@Override
	public SFString clone() {
		return new SFString(string);
	}
}
