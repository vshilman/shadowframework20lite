package codeconverter.java.jogl;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaBitwiseExpression;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;
import codeconverter.java.JavaTernaryOperator;
import codeconverter.java.JavaType;

public class JoglMethodEvaluation extends CompositeCodePiece {

	public JoglMethodEvaluation(String methodsSyntax) {
		super();
		JavaMethodEvaluation methodEvaluation=new JavaMethodEvaluation(methodsSyntax,true);
		JavaName name=new JavaName(true);
		JavaTernaryOperator ternaryOperator=new JavaTernaryOperator(true);
		JavaAlgebraicExpression algebraicExpression=new JavaAlgebraicExpression(true);
		JavaNewStatement newStatement=new JavaNewStatement(algebraicExpression, name);
		algebraicExpression.generate(methodEvaluation,this,name,ternaryOperator,newStatement);
		JavaBitwiseExpression bitwiseExpression=new JavaBitwiseExpression(methodEvaluation,this,name,newStatement);
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
		methodEvaluation.generate(methodsSyntax, algebraicExpression, bitwiseExpression);
		name.generate(null, algebraicExpression, bitwiseExpression);
		ternaryOperator.generate(algebraicExpression);
	}
	
	public JoglMethodEvaluation(String methodsSyntax,boolean notGenerate) {
		super();
	}
	
	public JoglMethodEvaluation(String methodsSyntax, JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression) {
		super();
		generate(methodsSyntax,algebraicExpression,bitwiseExpression);
	}

	public void generate(String methodsSyntax, JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression) {
		add(	new OptionalCode(//casting
					new CompositeCodePiece(new UniqueKeyword("("),new JavaType(),
							new UniqueKeyword(")"))
				),
				new CompositeCodePiece(
						new Name(PieceType.VALUE),
						new UniqueKeyword(methodsSyntax)
				),				
					new UniqueKeyword("gl"),new Name(PieceType.NAME),new UniqueKeyword("("),
					new CodeSequence(false,new BestAlternativeCode(true,
							algebraicExpression,bitwiseExpression),", "),
					new UniqueKeyword(")")	
				);
		setPieceType(PieceType.OPENGL_CALL);
	}
}
