package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;

public class JavaMethodVariables extends CodeSequence{

	public JavaMethodVariables() {
		super(new JavaVariable(),", ");
		setPieceType(PieceType.METHOD_VARIABLES);
	}
}
