package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class CppArrayContent extends CompositeCodePiece{

	public CppArrayContent() {
		setPieceType(PieceType.ARRAY_CONTENT);
		add(new UniqueKeyword("{"),
			new CodeSequence(new AlternativeCode(true, new CppAlgebraicExpression(),new CppBitwiseExpression()), ","),
			new UniqueKeyword("}"));
	}

}
