package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppMethodHeaderDeclaration extends CodePattern{

	public CppMethodHeaderDeclaration() {

		addCodePiece(new OptionalCode(new UniqueKeyword("virtual")),
					 new CppVariable(),
					 new UniqueKeyword("("),
					 new CppMethodVariables(),
					 new UniqueKeyword(")"),
					 new OptionalCode(new CompositeCodePiece(new UniqueKeyword("="), new Number())),
					 new OptionalCode(new CppThrowsDeclaration()));
		addPatternType(PatternType.METHOD_DECLARATION);
	}


}
