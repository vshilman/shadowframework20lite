package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.java.JavaMethodEvaluation;

public class JavaMethodAccess  extends CodePattern{

	public JavaMethodAccess() {
		addCodePiece(new JavaMethodEvaluation("."));
				//new CodeSequence(code,", "),staticKeyword);
		addCodePattern(PatternType.LINE_OF_CODE);
	}
	
}
