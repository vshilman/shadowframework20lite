package codeconverter.templates;

import java.util.HashMap;

public class EmptyTemplate implements Template{

	private static final int ID=10;


	@Override
	public boolean matchCode(String code) {
		return false;
	}

	@Override
	public String constructCode() {
		return "";
	}

	public Template clone(){
		return new EmptyTemplate();
	}

	@Override
	public HashMap<String, String> getProperties() {
		return new HashMap<String, String>();
	}

	@Override
	public void setProperty(String prop, String value) {

	}
	public int getId(){
		return ID;
	}



}
