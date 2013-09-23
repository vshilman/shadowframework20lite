package codeconverter.comparator.ignored;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PatternType;

public class JavaIgnoredPatternsH implements IgnoredPatterns {

	private static final List<PatternType> javaIgnoredTypes=new ArrayList<PatternType>();

	static{
		javaIgnoredTypes.add(PatternType.LIBRARY_DECLARATION);
		javaIgnoredTypes.add(PatternType.SUPER);
		javaIgnoredTypes.add(PatternType.ATTRIBUTE_ASSIGNMENT);

		javaIgnoredTypes.add(PatternType.IF);
		javaIgnoredTypes.add(PatternType.ELSE);
		javaIgnoredTypes.add(PatternType.FOR);
		javaIgnoredTypes.add(PatternType.RETURN);
		javaIgnoredTypes.add(PatternType.VARIABLE_ASSIGNMENT);
		javaIgnoredTypes.add(PatternType.VARIABLE_DECLARATION);
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
		boolean attdec=types.contains(PatternType.ATTRIBUTE_DECLARATION);
		boolean attass=types.contains(PatternType.ATTRIBUTE_ASSIGNMENT);
		if(attdec==true && attass==true){
			return false;
		}

		for (int i = 0; i < types.size(); i++) {
			if(javaIgnoredTypes.contains(types.get(i)))
				return true;
		}
		return false;
	}

}
