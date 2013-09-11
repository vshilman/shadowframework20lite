package codeconverter.javaJsComparator.codePatterns;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.comparator.CodePatternComparator;

public class IsolatedKeywordsComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
//			int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.ISOLATED_KEYWORDS) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!javaPattern.getPieces().get(0).toString().equals(jsPattern.getPieces().get(0).toString())) {
			return false;
		}

		return true;
	}

}
