package tests_junit.codepieces;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaName;

public class TestCodePiece011JUnit {

	@Test
	public void test() {
		
		ICodePiece piece=new JavaMethodEvaluation(".");
		String data="attribute.method()";
		ICodePieceMatch match=piece.elementMatch(data,0);
		String s=writeCodePieceMatch(match);

		data="attribute.attribute";//Should not be matched!
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="attribute.attribute.method()";//Should not be matched!
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);

		data="attribute.method().method()";//Should not be matched!
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		piece=new JavaName();
		data="attribute.attribute";//Should not be matched!
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		assertEquals("Matching Piece 18 [  attribute.method   (  )]\nMatching Piece -1 [null]\nMatching Piece 28 [  attribute.attribute.method   (  )]\nMatching Piece 27 [  attribute.method   (  ).method   (  )]\nMatching Piece 19 [attribute.attribute  ]\n", s);
		
	}

	public static String writeCodePieceMatch(ICodePieceMatch match){
		return "Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]\n";
	}
}
