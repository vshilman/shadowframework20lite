package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;


public class JavaMethodEvaluation extends CompositeCodePiece{

	public JavaMethodEvaluation(String methodsSyntax) {
		super();
		generate(methodsSyntax,new JavaAlgebraicExpression(this));
	}
	
	JavaMethodEvaluation(String methodsSyntax,JavaAlgebraicExpression expression) {
		super();
		generate(methodsSyntax,expression);
	}

	public void generate(String methodsSyntax, JavaAlgebraicExpression expression) {
		add(	new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),
				new OptionalCode(//variable or class Name
						new CompositeCodePiece(
								new JavaName(PieceType.VALUE),
								new UniqueKeyword(methodsSyntax)
						)),
				new CodeSequence(true,new CompositeCodePiece(
					new JavaName(),new UniqueKeyword("("),
					new CodeSequence(false,new AlternativeCode(true,
							this,expression),", "),
					new UniqueKeyword(")")
				),methodsSyntax)	
				);
	}	
}

