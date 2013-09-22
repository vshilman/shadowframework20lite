package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppBooleanExpression;

public class CppElse extends CodePattern{

	public CppElse() {
		addCodePiece(new OptionalCode(new UniqueKeyword("}")),
									  new UniqueKeyword("else"),
									  new OptionalCode(new CompositeCodePiece(new UniqueKeyword("if"),
											  								  new UniqueKeyword("("),
											  								  new CppBooleanExpression(),
											  								  new UniqueKeyword(")"))
					 ));
		addPatternType(PatternType.ELSE, PatternType.ELSE_IF,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);
	}
}
