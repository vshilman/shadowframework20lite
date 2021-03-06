package jcodecomparator.test;

import java.util.HashMap;


import jcodecomparator.core.CompareItemConstructor;
import jcodecomparator.core.CompareItemConstructorFactory;

/**
 *
 * @author Nicola Pellicano'
 *
 */


public class ConcreteCompareItemConstructorFactory implements CompareItemConstructorFactory{


	protected HashMap<String,CompareItemConstructor> map=new HashMap<>();

	@Override
	public CompareItemConstructor generateCompareItemConstructor(String canonicalName) {

		if(map.containsKey(canonicalName)){
			return map.get(canonicalName).clone();
		}

		return null;
	}

}
