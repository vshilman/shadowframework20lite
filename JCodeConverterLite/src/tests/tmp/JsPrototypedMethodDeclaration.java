package tests.tmp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.js.JsMethodVariables;

public class JsPrototypedMethodDeclaration  extends CodePattern {

	public JsPrototypedMethodDeclaration() {
		Name methodName=new Name();
		methodName.setPieceType(PieceType.NAME);
		addCodePiece(new Name(),new UniqueKeyword(".prototype"),new UniqueKeyword("["),new UniqueKeyword("\""),methodName,new UniqueKeyword("\""),
				new UniqueKeyword("]"),new UniqueKeyword("="));
		addCodePiece(new UniqueKeyword("function"), new UniqueKeyword("("), new JsMethodVariables(),
				new UniqueKeyword(")"));
		addCodePattern(PatternType.METHOD_DECLARATION);
	}
	
}
