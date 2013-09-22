package codeconverter.factories;

import codeconverter.templates.Structure;

/**
 *  Factory of the template changing for each language
 *
 * @author Nicola Pellicano'
 *
 */

public interface DataStructureTemplateFactory {

	public Structure getDataStructure(String lang);

}
