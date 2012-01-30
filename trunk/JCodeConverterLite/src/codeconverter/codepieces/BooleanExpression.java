package codeconverter.codepieces;

import java.util.Collections;
import java.util.List;

import codeconverter.ICodePiece;

public class BooleanExpression extends Expression{

	public static String[] booleanSymbols={
		"||","&&","=="
	};
	
	public BooleanExpression() {
		super();
		Collections.addAll(this.pieces,new Name(),new Number());//
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("("),this,new UniqueKeyword(")")));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"),new Name()));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"),
				new UniqueKeyword("("),this,new UniqueKeyword(")")));
	}

	@Override
	public String[] getExpressionSeparators() {
		return booleanSymbols;
	}
	
	@Override
	public List<ICodePiece> getPieces() {
		throw new RuntimeException("Not yet implemented, missing also clone method");
	}

}
