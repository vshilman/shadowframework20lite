package codeconverter;

public class AlgebraicExpression extends Expression{

	public static String[] algebraicSymbols={
		"*","+","/","-"
	};
	
	public AlgebraicExpression(ICodePiece... piece) {
		//TODO : this is not working...
//		super(piece);
//		this.availablePieces.add(this);
	}

	@Override
	public char getClosedBracketSymbol() {
		return ')';
	}
	
	@Override
	public String[] getExpressionSeparators() {
		return algebraicSymbols;
	}
	
	@Override
	public char getOpenBracketSymbol() {
		return ')';
	}
	
}
