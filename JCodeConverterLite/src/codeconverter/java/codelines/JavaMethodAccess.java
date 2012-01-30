package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.codepieces.MethodEvaluation;

public class JavaMethodAccess  extends CodePattern{

	public JavaMethodAccess() {
		addCodePiece(new MethodEvaluation("."));
				//new CodeSequence(code,", "),staticKeyword);
		addCodePattern(PatternType.LINE_OF_CODE);
	}
	
}
