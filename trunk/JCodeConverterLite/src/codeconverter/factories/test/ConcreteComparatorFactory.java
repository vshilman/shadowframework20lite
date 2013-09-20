package codeconverter.factories.test;


import java.util.ArrayList;
import java.util.List;


import codeconverter.ComparatorsHolder;
import codeconverter.comparator.PatternComparator;
import codeconverter.comparator.ignored.IgnoredHolder;
import codeconverter.comparator.ignored.IgnoredPatterns;
import codeconverter.factories.ComparatorFactory;

public class ConcreteComparatorFactory implements ComparatorFactory{

	protected List<ComparatorsHolder> list=new ArrayList<ComparatorsHolder>();
	protected List<IgnoredHolder> list2=new ArrayList<IgnoredHolder>();


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


	@Override
	public IgnoredHolder getIgnoreds(String lang1, String lang2) {

		for (int i = 0; i < list2.size(); i++) {
			IgnoredHolder h2=list2.get(i);
			if(h2.isOk(lang1, lang2)){
				return h2;
			}
		}
		return null;
	}




}
