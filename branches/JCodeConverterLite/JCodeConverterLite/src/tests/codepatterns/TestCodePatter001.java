package tests.codepatterns;

import codeconverter.CodePattern;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaBooleanExpression;

public class TestCodePatter001 {

	public static void main(String[] args) {
	
		CodePattern pattern=new CodePattern();
		pattern.addCodePiece(new UniqueKeyword("if"),new UniqueKeyword("("),
				new JavaBooleanExpression(),new UniqueKeyword(")"));
		String data="if(a==1)";
		CodePattern matchedPattern=pattern.match(data);
		writeCodePieceMatch(matchedPattern);
	}
	
	public static void writeCodePieceMatch(CodePattern match){
		System.out.println("Matching Piece ["+match+"]");
	}
}
