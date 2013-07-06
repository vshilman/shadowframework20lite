package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppNewStatement extends CompositeCodePiece{

	public CppNewStatement() {
		generate(new CppAlgebraicExpression(), new CppName(PieceType.TYPE));
	}

	public CppNewStatement(CppAlgebraicExpression algebraicExpression, CppName name) {
		generate(algebraicExpression, name);
	}

	public void generate(CppAlgebraicExpression algebraicExpression, CppName name){
		setPieceType(PieceType.NEW_STATEMENT);
		add(new UniqueKeyword("new "));
		add(name);
		add(new OptionalCode(new CompositeCodePiece(new UniqueKeyword("("),
													new CodeSequence(new OptionalCode(algebraicExpression), ","),
													new UniqueKeyword(")"))));
	}
}
