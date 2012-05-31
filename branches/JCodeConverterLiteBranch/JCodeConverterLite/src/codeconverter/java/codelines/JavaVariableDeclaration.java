package codeconverter.java.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.java.JavaVariable;

public class JavaVariableDeclaration extends CodePattern{

	public JavaVariableDeclaration() {
		addCodePiece(new JavaVariable());
		addCodePattern(PatternType.VARIABLE_DECLARATION,PatternType.LINE_OF_CODE);
	}
}
