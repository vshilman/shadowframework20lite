package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.java.JavaMethodEvaluation;

public class JavaMethodAccess  extends CodePattern{

	public JavaMethodAccess() {
		addCodePiece(new JavaMethodEvaluation("."));
				//new CodeSequence(code,", "),staticKeyword);
		addPatternType(PatternType.CALL,PatternType.LINE_OF_CODE);
	}
	
}
