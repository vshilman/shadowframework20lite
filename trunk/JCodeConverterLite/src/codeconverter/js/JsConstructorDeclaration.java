package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.UniqueKeyword;

public class JsConstructorDeclaration extends CodePattern {
	
	public JsConstructorDeclaration() {
		addCodePiece(new UniqueKeyword("function"),new JsName(PieceType.NAME),
				new UniqueKeyword("("),
				new CodeSequence(new JsName(), ","),new UniqueKeyword(")"));
		addCodePattern(PatternType.CONSTRUCTOR_DECLARATION);
	}
	
}
