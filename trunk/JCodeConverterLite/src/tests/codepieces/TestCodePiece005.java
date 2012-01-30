package tests.codepieces;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;

public class TestCodePiece005 {

	public static void main(String[] args) {
		
		//Test005 : testing Code Sequence (1). 
		ICodePiece piece=new CodeSequence(new Name(),", ");
		String data="word1";
		ICodePieceMatch match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		data="word1, word2, word3";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		//Test006 : testing Code Sequence (1).
		piece=new CompositeCodePiece(new Name(),new Name());
		data="int data";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		piece=new CodeSequence(new CompositeCodePiece(new Name(),new Name()),", ");
		data="int data, char word, int data2";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
	}
	
	public static void writeCodePieceMatch(ICodePieceMatch match){
		System.out.println("Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]");
	}
}
