package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JsMethodDeclaration extends CodePattern{

	public JsMethodDeclaration() {
		addCodePiece(new OptionalCode(new UniqueKeyword(",")),new Name(PieceType.NAME),new UniqueKeyword(":"),
				new UniqueKeyword("function"),new UniqueKeyword("("),
				new CodeSequence(new JsName(), ","),new UniqueKeyword(")")
		);
		addCodePattern(PatternType.METHOD_DECLARATION);
	}

}
