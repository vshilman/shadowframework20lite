package tests.tmp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PatternType;

public class JavaToJsIgnoredPatterns {

	private static final List<PatternType> javaIgnoredTypes=new ArrayList<PatternType>();
	
	static{
		javaIgnoredTypes.add(PatternType.LIBRARY_DECLARATION);
		javaIgnoredTypes.add(PatternType.CLASS_DECLARATION);
		javaIgnoredTypes.add(PatternType.SUPER);
	}

	public static List<PatternType> getJavaignoredtypes() {
		return javaIgnoredTypes;
	}
	
	public static boolean javaIgnoredContainsAny(List<PatternType> types){
		for (int i = 0; i < types.size(); i++) {
			if(javaIgnoredTypes.contains(types.get(i)))
				return true;
		}
		return false;
	}
}
