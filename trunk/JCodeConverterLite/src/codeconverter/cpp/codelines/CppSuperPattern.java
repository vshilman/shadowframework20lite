package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppBitwiseExpression;

public class CppSuperPattern extends CodePattern{

public CppSuperPattern() {
	addCodePiece(new UniqueKeyword("super"),new UniqueKeyword("("),
			new OptionalCode(new CodeSequence(new AlternativeCode(true,new CppAlgebraicExpression(),new CppBitwiseExpression()),", ")),
			new UniqueKeyword(")"));
	addPatternType(PatternType.SUPER,PatternType.LINE_OF_CODE);
}

}
