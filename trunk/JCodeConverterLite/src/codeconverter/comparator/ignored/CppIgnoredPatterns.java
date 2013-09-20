package codeconverter.comparator.ignored;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PatternType;

public class CppIgnoredPatterns implements IgnoredPatterns {

	private static final List<PatternType> cppIgnoredTypes=new ArrayList<PatternType>();

	static{
		cppIgnoredTypes.add(PatternType.LIBRARY_DECLARATION);
		cppIgnoredTypes.add(PatternType.DEALLOCATION);
		cppIgnoredTypes.add(PatternType.SUPER);
		cppIgnoredTypes.add(PatternType.NAMESPACE_USAGE);
		cppIgnoredTypes.add(PatternType.NAMESPACE_DECLARATION);
	}

	/* (non-Javadoc)
	 * @see tests.tmp.IgnoredPatterns#getJavaignoredtypes()
	 */
	@Override
	public  List<PatternType> getIgnoredtypes() {
		return cppIgnoredTypes;
	}

	/* (non-Javadoc)
	 * @see tests.tmp.IgnoredPatterns#javaIgnoredContainsAny(java.util.List)
	 */
	@Override
	public  boolean ignoredContainsAny(List<PatternType> types){
		for (int i = 0; i < types.size(); i++) {
			if(cppIgnoredTypes.contains(types.get(i)))
				return true;
		}
		return false;
	}
}

