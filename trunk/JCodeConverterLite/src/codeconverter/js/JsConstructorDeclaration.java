package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.UniqueKeyword;

public class JsConstructorDeclaration extends CodePattern {

	public JsConstructorDeclaration() {
		addCodePiece(new UniqueKeyword("function"), new JsName(PieceType.NAME), new UniqueKeyword("("),
				new JsMethodVariables(), new UniqueKeyword(")"));
		addPatternType(PatternType.CONSTRUCTOR_DECLARATION);
	}

}
