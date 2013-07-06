package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppBooleanExpression;

public class CppIf extends CodePattern{

	public CppIf() {

		addCodePiece(new OptionalCode(new UniqueKeyword("else")),
				     new UniqueKeyword("if"),
				     new UniqueKeyword("("),
				     new CppBooleanExpression(),
				     new UniqueKeyword(")"));
		addPatternType(PatternType.IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}

}
