package tests_junit.codepieces;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaMethodEvaluation;

public class TestCodePiece009JUnit {

	@Test
	public void test() {
		ICodePiece piece=new JavaMethodEvaluation(".");
		String data="Class.method()";
		ICodePieceMatch match=piece.elementMatch(data,0);
		String s=writeCodePieceMatch(match);

		data="method";//Should not be matched!
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="Class.method(a)";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);

		data="Class.method(a).method2().method3()";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);

		data="Class.method(a).method2().method3(a.method1(),a.method2())";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="Class.method(a+b)";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);

		data="Class.method(a,b,3+25*(c+d))";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="Class.method(obj1.method2(),a+34*(25.0f),ob2.method(2,e))";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		
		assertEquals("Matching Piece 14 [  Class.method   (  )]\nMatching Piece -1 [null]\nMatching Piece 15 [  Class.method   (  a   )]\nMatching Piece 35 [  Class.method   (  a   ).method2   (  ).method3   (  )]\nMatching Piece 58 [  Class.method   (  a   ).method2   (  ).method3   (   a.method1   (  ),   a.method2   (  ) )]\nMatching Piece 17 [  Class.method   (  a  + b   )]\nMatching Piece 28 [  Class.method   (  a  ,  b  , 3+25* (  c  + d   ) )]\nMatching Piece 57 [  Class.method   (   obj1.method2   (  ),  a  +34* ( 25.0f ),   ob2.method   ( 2,  e   ) )]\n", s);
	}
	
		public static String writeCodePieceMatch(ICodePieceMatch match){
			return "Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]\n";
		}

}
