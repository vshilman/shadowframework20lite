package codeconverter.templates;

import java.util.HashMap;

public class CannotConvertTemplate implements Template{

	private static final int ID=-1;

	private String notConv;


	public CannotConvertTemplate(String notConv) {
		super();
		this.notConv = notConv;
	}


	@Override
	public boolean matchCode(String code) {
		return false;
	}

	@Override
	public String constructCode() {
		return notConv;
	}

	public Template clone(){
		return new CannotConvertTemplate(notConv);
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
