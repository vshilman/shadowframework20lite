package tests_junit.codepieces;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaVariable;
import junit.framework.TestCase;

public class TestCodePiece001JUnit extends TestCase {

	public void test(){
	//Test001 : testing UniqueKeyword. String must match exactly UniqueKeyword value
	ICodePiece piece=new UniqueKeyword("class");
	String data="class";
	ICodePieceMatch match=piece.elementMatch(data,0);
	String s=writeCodePieceMatch(match);
	
	//Test002 : testing KeywordSet. String must match exactly UniqueKeyword value
	piece=new KeywordSet("private","public","static","final");
	data="public";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);
	data="static";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);

	//Test003 : testing Number.
	piece=new Number();
	data="0.89f";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);
	
	//Test004 : testing OptionalCode
	piece=new OptionalCode(new UniqueKeyword("import"));
	data="";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);
	data="import";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);

	//Test004 : testing Alternative Code
	piece=new AlternativeCode(false,new UniqueKeyword("import"),new Number());
	data="";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);
	data="import";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);
	data="#ffffaa";
	match=piece.elementMatch(data,0);
	s+=writeCodePieceMatch(match);
	
	assertEquals(s, "Matching Piece 5 [class]\nMatching Piece 6 [public]\nMatching Piece 6 [static]\nMatching Piece 5 [0.89f]\nMatching Piece 0 []\nMatching Piece 6 [import]\nMatching Piece 0 []\nMatching Piece 6 [import]\nMatching Piece 7 [#ffffaa]\n");
	
	}

	public static String writeCodePieceMatch(ICodePieceMatch match){
		return "Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]\n";
	}

}