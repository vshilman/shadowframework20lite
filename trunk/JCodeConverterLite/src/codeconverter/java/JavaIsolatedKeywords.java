package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.codepieces.KeywordSet;

public class JavaIsolatedKeywords extends CodePattern{

	private String[] isolatedKeyword={
		"@Override",
		"static",
		"else",
		"try"
	};
	
	public JavaIsolatedKeywords() {
		addCodePiece(new KeywordSet(isolatedKeyword));
	}
}