package jcodecomparator.test;

import java.util.HashMap;

import jcodecomparator.core.CompareItemGenerator;
import jcodecomparator.core.ICompareItem;

/**
 *
 * @author Nicola Pellicano'
 *
 */


public class ConcreteCompareItemGenerator implements CompareItemGenerator{


	protected HashMap<String,ICompareItem> map=new HashMap<>();
	protected Object element;

	@Override
	public ICompareItem getCompareItem(Object element, String type) {



		if(map.containsKey(type)){
			ICompareItem ci= map.get(type);
			ci.setInfo(element);
			return ci;
		}

		return null;
	}





}

