package tests.codepieces;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.JavaTernaryOperator;

public class TestCodePiece007 {
	
	public static void main(String[] args) {
		
		//Test007 : testing AlgebraicExpression (1). 
		ICodePiece piece=new JavaAlgebraicExpression();
		String data="a+b*c";
		ICodePieceMatch match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a+3*c";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a+(2*f)";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a+(2*(3*d))+g";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a+\"Ciao\"+b";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		//Test007 : testing BooleanExpression (1).
		piece=new JavaBooleanExpression();
		data="a==1";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		piece=new JavaBooleanExpression();
		data="a==1 && c==2";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a==1 && (d || f==1)";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="a==1 && !(!d || f==1)";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);

		data="d>1";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		piece=new JavaTernaryOperator();
		data="filter == 2 ? 0 : (filter + 1)";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="(s <= 2) ? a : -b";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
	}
	
	public static void writeCodePieceMatch(ICodePieceMatch match){
		System.out.println("Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]");
	}
}
