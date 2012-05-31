package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JavaNewStatement extends CompositeCodePiece{

	public JavaNewStatement() {
		generate(new JavaAlgebraicExpression(),new JavaName(PieceType.TYPE));
	}

	public JavaNewStatement(JavaAlgebraicExpression algebraicExpression, JavaName name) {
		generate(algebraicExpression,name);
	}
	
	private void generate(JavaAlgebraicExpression algebraicExpression, JavaName name) {
		setPieceType(PieceType.NEW_STATEMENT);
		add(new UniqueKeyword("new "));
		add(name);
//		add(new OptionalCode(
//				new CompositeCodePiece(
//						new UniqueKeyword("["),
//						new OptionalCode(new JavaAlgebraicExpression()),
//						new UniqueKeyword("]")
//				)));
		add(new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("("),
						new CodeSequence(new OptionalCode(algebraicExpression),","),
						new UniqueKeyword(")")
				)));
	}
}
