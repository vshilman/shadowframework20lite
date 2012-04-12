package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.javaJsComparator.CodePatternComparator;

public class IsolatedKeywordsComparator implements CodePatternComparator {

	@Override
	public int[] compare(List<CodePattern> javaCodePatterns, int javaIndex, List<CodePattern> jsCodePatterns,
			int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.ISOLATED_KEYWORDS) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (!javaPattern.getPieces().get(0).toString().equals(jsPattern.getPieces().get(0).toString())) {
			return null;
		}

		return new int[] { javaIndex + 1, jsIndex + 1 };
	}

}
