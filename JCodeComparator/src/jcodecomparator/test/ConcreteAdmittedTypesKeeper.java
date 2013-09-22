package jcodecomparator.test;

import java.util.ArrayList;
import java.util.List;

import jcodecomparator.core.AdmittedTypesKeeper;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class ConcreteAdmittedTypesKeeper implements AdmittedTypesKeeper{

		private List<String> list=new ArrayList<>();

		public ConcreteAdmittedTypesKeeper() {
			list.add("java");
			list.add("js");
			list.add("cpp");
			list.add("h");
		}


	@Override
	public List<String> getAmmittedTypes() {
		return list;
	}





}
