package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppConstrutorDeclaration extends CodePattern{

	public CppConstrutorDeclaration() {
		CppName name=new CppName(PieceType.NAME);
		addCodePiece(name,
					 new UniqueKeyword("::"),
					 name,
					 new UniqueKeyword("("),
					 new CppMethodVariables(),
					 new UniqueKeyword(")"),
					 new OptionalCode(new CppThrowsDeclaration()));
		adddPatternType(PatternType.CONSTRUCTOR_DECLARATION);

	}

}
