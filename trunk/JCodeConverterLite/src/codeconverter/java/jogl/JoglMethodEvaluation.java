package codeconverter.java.jogl;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaType;

public class JoglMethodEvaluation extends CompositeCodePiece {

	public JoglMethodEvaluation(String methodsSyntax) {
		super();
		generate(methodsSyntax,new JavaAlgebraicExpression());
	}
	
	JoglMethodEvaluation(String methodsSyntax,JavaAlgebraicExpression expression) {
		super();
		generate(methodsSyntax,expression);
	}

	public void generate(String methodsSyntax, JavaAlgebraicExpression expression) {
		add(	new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),
				new CompositeCodePiece(
						new JoglName(PieceType.VALUE),
						new UniqueKeyword(methodsSyntax)
				),
				new CodeSequence(true,new CompositeCodePiece(
						new UniqueKeyword("gl"),new JoglName(),new UniqueKeyword("("),
					new CodeSequence(false,new BestAlternativeCode(true,
							this,expression),", "),
					new UniqueKeyword(")")
				),methodsSyntax)	
				);
	}
}
