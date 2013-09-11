package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppBitwiseExpression;
import codeconverter.cpp.CppBooleanExpression;

public class CppCoutPattern extends CodePattern{


	public CppCoutPattern() {
		addCodePiece(new OptionalCode(new UniqueKeyword("std::")), new UniqueKeyword("cout"), new UniqueKeyword("<<"), new CodeSequence(new AlternativeCode(true, new CppAlgebraicExpression(),new CppBitwiseExpression(),new CppBooleanExpression()),"<<"),new UniqueKeyword("<<"),new UniqueKeyword("endl"));
		addPatternType(PatternType.METHOD_DECLARATION);
	}


}
