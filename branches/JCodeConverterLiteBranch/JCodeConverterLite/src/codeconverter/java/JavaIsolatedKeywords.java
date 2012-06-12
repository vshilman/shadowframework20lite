package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.KeywordSet;

public class JavaIsolatedKeywords extends CodePattern{

	private String[] isolatedKeyword={
		"@Override",
		"static",
		"try"
	};
	
	public JavaIsolatedKeywords() {
		addCodePiece(new KeywordSet(isolatedKeyword));
		addPatternType(PatternType.ISOLATED_KEYWORDS);
	}
}
