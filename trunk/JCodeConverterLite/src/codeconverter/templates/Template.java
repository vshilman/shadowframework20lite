package codeconverter.templates;

import java.util.HashMap;

/**
 *
 * A common code module skeleton
 *
 * @author nicolapelicano
 *
 */

public interface Template {

	/**
	 * IDs must be synchronized between different language versions
	 *
	 * @return the template type
	 */

	public int getId();

	/**
	 *
	 * @param code
	 * @return true if code match this template
	 */

	public boolean matchCode(String code);

	/**
	 *
	 * @return the code builded with template standards
	 */

	public String constructCode();

	/**
	 *
	 * @return template informations
	 */

	public HashMap<String, String> getProperties();

	/**
	 * Set the specified property
	 *
	 * @param prop
	 * @param value
	 */

	public void setProperty(String prop, String value);

	/**
	 * Clone
	 */

	public Template clone();

}
