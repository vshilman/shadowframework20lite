package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;

public class CppAttributeDeclaration extends CodePattern{

	public CppAttributeDeclaration() {
		addCodePiece(new CppCompositeType(), new CodeSequence(new CompositeCodePiece(new CppName(PieceType.NAME)), ","));
		addPatternType(PatternType.ATTRIBUTE_DECLARATION);
	}

}
