package codeconverter.js.webgl;

import codeconverter.CodePattern;
import codeconverter.PatternType;

public class WebGlMethodAccess  extends CodePattern{

	public WebGlMethodAccess() {
		addCodePiece(new WebGlMethodEvaluation("."));
				//new CodeSequence(code,", "),staticKeyword);
		addPatternType(PatternType.OPENGL_CALL,PatternType.LINE_OF_CODE);
	}
	
}
