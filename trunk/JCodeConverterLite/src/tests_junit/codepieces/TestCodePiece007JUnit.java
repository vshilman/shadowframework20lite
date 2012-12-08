package tests_junit.codepieces;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.JavaTernaryOperator;

public class TestCodePiece007JUnit {

	@Test
	public void test() {
		ICodePiece piece=new JavaAlgebraicExpression();
		String data="a+b*c";
		ICodePieceMatch match=piece.elementMatch(data,0);
		String s=writeCodePieceMatch(match);
		
		data="a";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="a+3*c";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="a+(2*f)";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="a+(2*(3*d))+g";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="a+\"Ciao\"+b";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		//Test007 : testing BooleanExpression (1).
		piece=new JavaBooleanExpression();
		data="a==1";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		piece=new JavaBooleanExpression();
		data="a==1 && c==2";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="a==1 && (d || f==1)";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="a==1 && !(!d || f==1)";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);

		data="d>1";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		piece=new JavaTernaryOperator();
		data="filter == 2 ? 0 : (filter + 1)";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		data="(s <= 2) ? a : -b";
		match=piece.elementMatch(data,0);
		s+=writeCodePieceMatch(match);
		
		assertEquals(s, "Matching Piece 5 [ a  + b  * c  ]\nMatching Piece 1 [ a  ]\nMatching Piece 5 [ a  +3* c  ]\nMatching Piece 7 [ a  + ( 2* f   )]\nMatching Piece 13 [ a  + ( 2* ( 3* d   ) )+ g  ]\nMatching Piece 10 [ a  + \"Ciao\"  + b  ]\nMatching Piece 4 [a  ==1]\nMatching Piece 12 [a  ==1&&c  ==2]\nMatching Piece 19 [a  ==1&&( d  ||f  ==1 )]\nMatching Piece 21 [a  ==1&&! ( ! d  ||f  ==1 )]\nMatching Piece 3 [d  >1]\nMatching Piece 30 [filter  ==2 ? 0 :  (  filter  +1 )]\nMatching Piece 17 [( s  <=2 ) ?  a   : -b]\n");
	}
	
	public static String writeCodePieceMatch(ICodePieceMatch match){
		return "Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]\n";
	}

}
