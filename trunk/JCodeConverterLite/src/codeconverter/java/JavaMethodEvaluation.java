package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.jogl.JoglMethodEvaluation;


public class JavaMethodEvaluation extends CompositeCodePiece{

	public JavaMethodEvaluation(String methodsSyntax) {
		super();
		JoglMethodEvaluation methodEvaluation=new JoglMethodEvaluation(methodsSyntax,true);
		JavaName name=new JavaName(true);
		JavaAlgebraicExpression algebraicExpression=new JavaAlgebraicExpression(this,methodEvaluation,name);
		JavaBitwiseExpression bitwiseExpression=new JavaBitwiseExpression(this,methodEvaluation,name);
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
		methodEvaluation.generate(methodsSyntax, algebraicExpression, bitwiseExpression);
		name.generate(null, algebraicExpression, bitwiseExpression);
	}
	
	public JavaMethodEvaluation(String methodsSyntax,boolean notGenerate) {
		super();
	}
	
	public JavaMethodEvaluation(String methodsSyntax, JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression) {
		super();
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
	}

	public void generate(String methodsSyntax, JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression) {
		add(	new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),
				new OptionalCode(//variable or class Name
						new CompositeCodePiece(
								new JavaName(PieceType.VALUE,algebraicExpression,bitwiseExpression),
								new UniqueKeyword(methodsSyntax)
						)),
				new CodeSequence(true,new CompositeCodePiece(
					new JavaName(algebraicExpression,bitwiseExpression),new UniqueKeyword("("),
					new CodeSequence(false,new BestAlternativeCode(true,
							algebraicExpression,bitwiseExpression),", "),
					new UniqueKeyword(")")
				),methodsSyntax)	
				);
		setPieceType(PieceType.CALL);
	}	
}