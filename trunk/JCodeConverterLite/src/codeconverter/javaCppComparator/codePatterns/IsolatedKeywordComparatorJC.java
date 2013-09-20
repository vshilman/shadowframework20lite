package codeconverter.javaCppComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.comparator.CodePatternComparator;

public class IsolatedKeywordComparatorJC implements CodePatternComparator{

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern cppPattern) {

		System.out.println(javaPattern.toString()+ "  vs   "+cppPattern.toString());

		if (javaPattern.getPatternType().get(0) != PatternType.ISOLATED_KEYWORDS) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != cppPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!javaPattern.getPieces().get(0).toString().equals(cppPattern.getPieces().get(0).toString())) {
			return false;
		}

		return true;

	}



}
