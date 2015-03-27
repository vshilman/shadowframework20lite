package shadow.system.data.objects;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFString implements SFWritableDataObject{

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

	//static int a=0;
	@Override
	public void readFromStream(SFInputStream stream) {
		string=stream.readString();
		//System.err.print(" [SFString:"+string+"]");
		//if(a==1338)
			//	throw new SFException("SFString exception");
		//a++;
		//01100001
		//00011010
	}
	
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeString(string);
	}
	
	@Override
	public void setStringValue(String value) {
		this.string=value;
	}
	
	@Override
	public String toStringValue() {
		return string;
	}

	@Override
	public SFString copyDataObject() {
		return new SFString(string);
	}
}
