package jcodecomparator.test;

import java.util.ArrayList;
import java.util.List;

import jcodecomparator.core.AdmittedTypesKeeper;

public class ConcreteAdmittedTypesKeeper implements AdmittedTypesKeeper{

		private List<String> list=new ArrayList<>();

		public ConcreteAdmittedTypesKeeper() {
			list.add("java");
			list.add("js");
		}


	@Override
	public List<String> getAmmittedTypes() {
		return list;
	}





}
