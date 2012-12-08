package tests_junit.codepieces;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;

public class TestCodePiece005JUnit {

	@Test
	public void test() {
		//Test005 : testing Code Sequence (1). 
		ICodePiece piece=new CodeSequence(true,new Name(),", ");
		String data="word1";
		ICodePieceMatch match=piece.elementMatch(data,0);
		String s=writeCodePieceMatch(match);
		data="word1, word2, word3";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		//Test006 : testing Code Sequence (1).
		piece=new CompositeCodePiece(new Name(),new Name());
		data="int data";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		piece=new CodeSequence(true,new CompositeCodePiece(new Name(),new Name()),", ");
		data="int data, char word, int data2";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		assertEquals(s, "Matching Piece 5 [word1]\nMatching Piece 19 [word1, word2, word3]\nMatching Piece 8 [int data]\nMatching Piece 30 [int data, char word, int data2]\n");
	}
	
	public static String writeCodePieceMatch(ICodePieceMatch match){
		return "Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]\n";
	}

}
