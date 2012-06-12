package codeconverter.js.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.js.JsMethodEvaluation;

public class JsMethodAccess  extends CodePattern{

	public JsMethodAccess() {
		addCodePiece(new JsMethodEvaluation("."));
				//new CodeSequence(code,", "),staticKeyword);
		addPatternType(PatternType.CALL,PatternType.LINE_OF_CODE);
	}
	
}
