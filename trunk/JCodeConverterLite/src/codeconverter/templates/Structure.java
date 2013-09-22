package codeconverter.templates;

import java.util.List;


/**
 * A template container
 *
 * @author nicolapelicano
 *
 */

public interface Structure {

	/**
	 *
	 * @return the list of template supported
	 */

	public List<Template> getTemplates();

	/**
	 * It build the entire code of a class based on the language standards
	 *
	 * @param className
	 * @param convlist
	 * @return
	 */
	public String buildCode(String className,List<Template> convlist);
}
