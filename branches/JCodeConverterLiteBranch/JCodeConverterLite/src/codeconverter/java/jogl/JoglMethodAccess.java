package codeconverter.java.jogl;

import codeconverter.CodePattern;
import codeconverter.PatternType;

public class JoglMethodAccess  extends CodePattern{

	public JoglMethodAccess() {
		addCodePiece(new JoglMethodEvaluation("."));
				//new CodeSequence(code,", "),staticKeyword);
		addPatternType(PatternType.OPENGL_CALL,PatternType.LINE_OF_CODE);
	}
	
}
