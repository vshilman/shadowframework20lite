package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppDestructorHeaderDeclaration extends CodePattern{

	public CppDestructorHeaderDeclaration() {

		addCodePiece(new UniqueKeyword("~"),
					 new CppName(PieceType.NAME),
					 new UniqueKeyword("("),
					 new UniqueKeyword(")"));
				 	 addPatternType(PatternType.CONSTRUCTOR_DECLARATION);
	}


}
