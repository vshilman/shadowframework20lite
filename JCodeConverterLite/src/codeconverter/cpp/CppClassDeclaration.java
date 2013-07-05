package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppClassDeclaration extends CodePattern{

	public CppClassDeclaration() {
		addCodePiece(new UniqueKeyword("class"),
				     new Name(PieceType.NAME),
				     new OptionalCode(new CppExtendsDeclaration()));
		addPatternType(PatternType.CLASS_DECLARATION);
	}


}
