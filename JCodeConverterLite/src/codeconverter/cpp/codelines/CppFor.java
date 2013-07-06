package codeconverter.cpp.codelines;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.AlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.KeywordSet;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.cpp.CppAlgebraicExpression;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppVariable;
import codeconverter.java.JavaVariable;

public class CppFor extends CodePattern{

	public CppFor() {

		CompositeCodePiece initialization=new CompositeCodePiece(new AlternativeCode(true, new CppVariable(),
																						   new CppName(PieceType.NAME)),
																 new UniqueKeyword("="),
																 new CppAlgebraicExpression());

		CodeSequence update=new CodeSequence(new CompositeCodePiece(new CppName(PieceType.VARIABLE),
											 						new AlternativeCode(true, new UniqueKeyword("++"),
											 												  new UniqueKeyword("--"),
											 												  new UniqueKeyword("-="),
											 												  new UniqueKeyword("+=")),
											 						new OptionalCode(new CppAlgebraicExpression())), ", ");

		addCodePiece(new UniqueKeyword("for"),
					 new UniqueKeyword("("),
					 initialization,
					 new UniqueKeyword(";"),
					 new CppName(PieceType.VALUE),
					 new KeywordSet("<=",">=",">","<"),
					 new CppAlgebraicExpression(),
					 new UniqueKeyword(";"),
					 update,
					 new UniqueKeyword(")"));

		addPatternType(PatternType.FOR,PatternType.ASSIGNMENT,PatternType.LINE_OF_CODE);

	}


}
