package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.KeywordSet;

public class CppisolatedkeyWords extends CodePattern{

	private String[] isolatedKeywords={"try", "public:", "private:","protected:"}; //public: ??

	public CppisolatedkeyWords(){
		addCodePiece(new KeywordSet(isolatedKeywords));
		addPatternType(PatternType.ISOLATED_KEYWORDS);
	}

}
