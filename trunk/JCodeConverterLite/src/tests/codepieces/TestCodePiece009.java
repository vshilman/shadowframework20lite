package tests.codepieces;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.MethodEvaluation;

public class TestCodePiece009 {

	public static void main(String[] args) {
		 
		ICodePiece piece=new MethodEvaluation(".");
		String data="Class.method()";
		ICodePieceMatch match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="Class.method(a)";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="Class.method(a,b,3+25*(c+d))";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="Class.method(obj1.method2(),a+34*(25.0f),ob2.method(2,e))";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
	}
	
	public static void writeCodePieceMatch(ICodePieceMatch match){
		System.out.println("Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]");
	}
}
