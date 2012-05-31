package tests.codepieces;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaName;

public class TestCodePiece011 {

	public static void main(String[] args) {
		 
		ICodePiece piece=new JavaMethodEvaluation(".");
		String data="attribute.method()";
		ICodePieceMatch match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);

		data="attribute.attribute";//Should not be matched!
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		data="attribute.attribute.method()";//Should not be matched!
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);

		data="attribute.method().method()";//Should not be matched!
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		piece=new JavaName();
		data="attribute.attribute";//Should not be matched!
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
	}
	
	public static void writeCodePieceMatch(ICodePieceMatch match){
		System.out.println("Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]");
	}
}
