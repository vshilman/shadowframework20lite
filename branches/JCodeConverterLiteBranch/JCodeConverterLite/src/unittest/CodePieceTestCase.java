package unittest;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.Number;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import junit.framework.TestCase;

public class CodePieceTestCase extends TestCase {

	public void test1(){
		ICodePiece piece=new UniqueKeyword("class");
		String data="class";
		ICodePieceMatch match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
		
		//Test002 : testing KeywordSet. String must match exactly UniqueKeyword value
		piece=new KeywordSet("private","public","static","final");
		data="public";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
		data="static";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
	
		//Test003 : testing Number.
		piece=new Number();
		data="0.89f";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
		
		//Test004 : testing OptionalCode
		piece=new OptionalCode(new UniqueKeyword("import"));
		data="";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
		data="import";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);

		//Test004 : testing Alternative Code
		piece=new AlternativeCode(false,new UniqueKeyword("import"),new Number());
		data="";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
		data="import";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
		data="#ffffaa";
		match=piece.elementMatch(data,0);
		checkCodePieceMatch(data,match);
	}
	
	public static void checkCodePieceMatch(String data,ICodePieceMatch match){
		assertEquals(data,match.getDataPiece().toString());
	}
}
