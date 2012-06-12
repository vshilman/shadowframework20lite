package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.js.JsVariable;

public class JsVariableDeclaration extends CodePattern{

	public JsVariableDeclaration() {
		addCodePiece(new JsVariable());
		addPatternType(PatternType.VARIABLE_DECLARATION,PatternType.LINE_OF_CODE);
	}
}
