package codeconverter.factories.test;

import java.util.HashMap;

import codeconverter.factories.DataStructureTemplateFactory;
import codeconverter.templates.Structure;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class ConcreteDataStructureTemplateFactory implements DataStructureTemplateFactory{

	protected HashMap<String, Structure> map=new HashMap<String, Structure>();

	@Override
	public Structure getDataStructure(String lang) {

		if(map.containsKey(lang)){
			return map.get(lang);
		}
		return null;
	}




}
