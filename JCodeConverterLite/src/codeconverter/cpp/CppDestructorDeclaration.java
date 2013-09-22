package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.UniqueKeyword;

public class CppDestructorDeclaration extends CodePattern{

	public CppDestructorDeclaration() {
		CppName name=new CppName(PieceType.NAME);
		addCodePiece(name,
					 new UniqueKeyword("::~"),
					 name,
					 new UniqueKeyword("("),
					 new UniqueKeyword(")"));
		addPatternType(PatternType.CONSTRUCTOR_DECLARATION);
	}


}
