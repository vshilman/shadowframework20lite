package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppMethodDeclaration extends CodePattern{

	public CppMethodDeclaration() {
		addCodePiece(new CppCompositeType(),
					 new CppName(),
					 new UniqueKeyword("::"),
					 new CppName(PieceType.NAME),
					 new UniqueKeyword("("),
					 new CppMethodVariables(),
					 new UniqueKeyword(")"),
					 new OptionalCode(new CppThrowsDeclaration()));
		addPatternType(PatternType.METHOD_DECLARATION);
	}

}
