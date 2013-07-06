package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppConstructorHeaderDeclaration extends CodePattern{

	public CppConstructorHeaderDeclaration() {
		addCodePiece(new CppName(PieceType.NAME),
				 	 new UniqueKeyword("("),
				 	 new CppMethodVariables(),
				 	 new UniqueKeyword(")"),
				 	 new OptionalCode(new CppThrowsDeclaration()));
					 addPatternType(PatternType.CONSTRUCTOR_DECLARATION);
	}



	}

