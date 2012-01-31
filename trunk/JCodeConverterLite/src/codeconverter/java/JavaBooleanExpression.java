package codeconverter.java;

import java.util.Collections;
import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Expression;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.UniqueKeyword;

public class JavaBooleanExpression extends Expression{

	public static String[] booleanSymbols={
		"||","&&","=="
	};
	
	public JavaBooleanExpression() {
		super();
		Collections.addAll(this.pieces,new JavaName(),new Number());//
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("("),this,new UniqueKeyword(")")));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"),new JavaName()));
		this.pieces.add(new CompositeCodePiece(new UniqueKeyword("!"),
				new UniqueKeyword("("),this,new UniqueKeyword(")")));
//		this.pieces.add(new CompositeCodePiece(new AlgebraicExpression(),
//				new AlternativeCode(true,
//						new KeywordSet(">",">=","<","<=","==")
//				),
//				new AlgebraicExpression()));
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
