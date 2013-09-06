package tests.tmp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PatternType;

public class JavaIgnoredPatterns implements IgnoredPatterns {

	private static final List<PatternType> javaIgnoredTypes=new ArrayList<PatternType>();

	static{
		javaIgnoredTypes.add(PatternType.LIBRARY_DECLARATION);
		javaIgnoredTypes.add(PatternType.CLASS_DECLARATION);
		javaIgnoredTypes.add(PatternType.SUPER);
	}

	/* (non-Javadoc)
	 * @see tests.tmp.IgnoredPatterns#getJavaignoredtypes()
	 */
	@Override
	public  List<PatternType> getIgnoredtypes() {
		return javaIgnoredTypes;
	}

	/* (non-Javadoc)
	 * @see tests.tmp.IgnoredPatterns#javaIgnoredContainsAny(java.util.List)
	 */
	@Override
	public  boolean ignoredContainsAny(List<PatternType> types){
		for (int i = 0; i < types.size(); i++) {
			if(javaIgnoredTypes.contains(types.get(i)))
				return true;
		}
		return false;
	}
}
