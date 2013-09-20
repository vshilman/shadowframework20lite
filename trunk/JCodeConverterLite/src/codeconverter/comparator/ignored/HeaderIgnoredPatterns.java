package codeconverter.comparator.ignored;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PatternType;

public class HeaderIgnoredPatterns implements IgnoredPatterns {

	private static final List<PatternType> hIgnoredTypes=new ArrayList<PatternType>();

	static{
		hIgnoredTypes.add(PatternType.LIBRARY_DECLARATION);
		hIgnoredTypes.add(PatternType.FRIENDSHIP);
		hIgnoredTypes.add(PatternType.NAMESPACE_DECLARATION);
		hIgnoredTypes.add(PatternType.NAMESPACE_USAGE);
	}

	/* (non-Javadoc)
	 * @see tests.tmp.IgnoredPatterns#getJavaignoredtypes()
	 */
	@Override
	public  List<PatternType> getIgnoredtypes() {
		return hIgnoredTypes;
	}

	/* (non-Javadoc)
	 * @see tests.tmp.IgnoredPatterns#javaIgnoredContainsAny(java.util.List)
	 */
	@Override
	public  boolean ignoredContainsAny(List<PatternType> types){
		for (int i = 0; i < types.size(); i++) {
			if(hIgnoredTypes.contains(types.get(i)))
				return true;
		}
		return false;
	}

}
