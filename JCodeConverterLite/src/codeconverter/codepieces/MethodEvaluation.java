package codeconverter.codepieces;


public class MethodEvaluation extends CompositeCodePiece{

	public MethodEvaluation(String methodsSyntax) {
		super();
	
		add(new Name(),new UniqueKeyword(methodsSyntax),new Name(),new UniqueKeyword("("),
				new CodeSequence(new AlternativeCode(true,
						this,new AlgebraicExpression(this)),", "),
				new UniqueKeyword(")"));
	}
	
	MethodEvaluation(String methodsSyntax,AlgebraicExpression expression) {
		super();
	
		add(new Name(),new UniqueKeyword(methodsSyntax),new Name(),new UniqueKeyword("("),
				new CodeSequence(new AlternativeCode(true,
						this,expression),", "),
				new UniqueKeyword(")"));
	}	
}

