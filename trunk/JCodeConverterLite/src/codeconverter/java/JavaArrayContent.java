package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;

public class JavaArrayContent extends CompositeCodePiece {

	public JavaArrayContent() {
		setPieceType(PieceType.ARRAY_CONTENT);
		add(new UniqueKeyword("{"), new CodeSequence(new AlternativeCode(true, new JavaAlgebraicExpression(),
				new JavaBitwiseExpression()), ","), new UniqueKeyword("}"));
	}
}