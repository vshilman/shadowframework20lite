package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;

public class CppMethodVariables extends CodeSequence{

	public CppMethodVariables() {
		super(new CppVariable(),", ");
		setPieceType(PieceType.METHOD_VARIABLES);
	}


}
