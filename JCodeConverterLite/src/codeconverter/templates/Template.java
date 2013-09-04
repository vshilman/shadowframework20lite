package codeconverter.templates;

import java.util.HashMap;

public interface Template {

	public int getId();

	public boolean matchCode(String code);

	public String constructCode();

	public HashMap<String, String> getProperties();

	public void setProperty(String prop, String value);

	public Template clone();

}
