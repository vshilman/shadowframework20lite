package tests.codepatterns;

import codeconverter.CodePattern;
import codeconverter.codepieces.BooleanExpression;
import codeconverter.codepieces.UniqueKeyword;

public class TestCodePatter001 {

	public static void main(String[] args) {
	
		CodePattern pattern=new CodePattern();
		pattern.addCodePiece(new UniqueKeyword("if"),new UniqueKeyword("("),
				new BooleanExpression(),new UniqueKeyword(")"));
		String data="if(a==1)";
		CodePattern matchedPattern=pattern.match(data);
		writeCodePieceMatch(matchedPattern);
	}
	
	public static void writeCodePieceMatch(CodePattern match){
		System.out.println("Matching Piece ["+match+"]");
	}
}
