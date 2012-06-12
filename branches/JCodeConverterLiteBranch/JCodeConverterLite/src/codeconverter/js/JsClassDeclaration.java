package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class JsClassDeclaration extends CodePattern{

	public JsClassDeclaration() {
		addCodePiece(new Name(PieceType.NAME),new UniqueKeyword("."),new UniqueKeyword("prototype"),
				new UniqueKeyword("=")
				);
		addPatternType(PatternType.CLASS_DECLARATION);
	}

}
