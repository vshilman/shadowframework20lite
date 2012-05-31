package tests.codepieces;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class TestCodePiece001 {

	public static void main(String[] args) {
		
		//Test001 : testing UniqueKeyword. String must match exactly UniqueKeyword value
		ICodePiece piece=new UniqueKeyword("class");
		String data="class";
		ICodePieceMatch match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		//Test002 : testing KeywordSet. String must match exactly UniqueKeyword value
		piece=new KeywordSet("private","public","static","final");
		data="public";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		data="static";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
	
		//Test003 : testing Number.
		piece=new Number();
		data="0.89f";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
		//Test004 : testing OptionalCode
		piece=new OptionalCode(new UniqueKeyword("import"));
		data="";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		data="import";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);

		//Test004 : testing Alternative Code
		piece=new AlternativeCode(false,new UniqueKeyword("import"),new Number());
		data="";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		data="import";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		data="#ffffaa";
		match=piece.elementMatch(data,0);
		writeCodePieceMatch(match);
		
	}
	
	public static void writeCodePieceMatch(ICodePieceMatch match){
		System.out.println("Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]");
	}
}
