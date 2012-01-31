package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaNewStatement extends CompositeCodePiece{

	public JavaNewStatement() {
		add(new UniqueKeyword("new"));
		add(new JavaName(PieceType.TYPE));
		add(new CodeSequence(
				new CompositeCodePiece(
						new UniqueKeyword("["),
						new OptionalCode(new JavaAlgebraicExpression()),
						new UniqueKeyword("]")
				),""));
		add(new CodeSequence(
				new CompositeCodePiece(
						new UniqueKeyword("("),
						new OptionalCode(new JavaAlgebraicExpression()),
						new UniqueKeyword(")")
				),""));
	}
}
