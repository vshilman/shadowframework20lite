package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.KeywordSet;

public class JsIsolatedKeywords extends CodePattern{

	private String[] isolatedKeyword={
		"try"
	};
	
	public JsIsolatedKeywords() {
		addCodePiece(new KeywordSet(isolatedKeyword));
		addPatternType(PatternType.ISOLATED_KEYWORDS);
	}
}
