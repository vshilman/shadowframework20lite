package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppIncludeDirectiveLine extends CodePattern{


	public CppIncludeDirectiveLine() {
		BestAlternativeCode bcc=new BestAlternativeCode(true, new UniqueKeyword("<"),new UniqueKeyword("\""));
		BestAlternativeCode bcc2=new BestAlternativeCode(true, new UniqueKeyword(">"),new UniqueKeyword("\""));
		addCodePiece(new UniqueKeyword("#include "),
					bcc,
					new CppName(),
					bcc2);
		addPatternType(PatternType.LIBRARY_DECLARATION);
	}


}
