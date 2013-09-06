package codeconverter.factories.test;


import java.util.ArrayList;
import java.util.List;

import tests.tmp.IgnoredPatterns;

import codeconverter.ComparatorsHolder;
import codeconverter.comparator.PatternComparator;
import codeconverter.factories.ComparatorFactory;

public class ConcreteComparatorFactory implements ComparatorFactory{

	protected List<ComparatorsHolder> list=new ArrayList<ComparatorsHolder>();


	@Override
	public ComparatorsHolder getComparators(String lang1, String lang2) {

		for (int i = 0; i < list.size(); i++) {
			ComparatorsHolder h=list.get(i);
			if(h.isOk(lang1, lang2)){
				return h;
			}
		}
		return null;
	}




}
