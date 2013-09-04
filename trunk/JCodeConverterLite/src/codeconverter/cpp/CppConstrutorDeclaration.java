package codeconverter.cpp;

import codeconverter.CodePattern;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppConstrutorDeclaration extends CodePattern{

	public CppConstrutorDeclaration() {
		CppName name=new CppName(PieceType.NAME);
		addCodePiece(name,
					 new UniqueKeyword("::"),
					 name,
					 new UniqueKeyword("("),
					 new CppMethodVariables(),
					 new UniqueKeyword(")"),
					 new OptionalCode(new CompositeCodePiece(new UniqueKeyword(":"),
							          						 new CodeSequence(  new CompositeCodePiece(  new CppName(PieceType.NAME),
							        		  									                         new UniqueKeyword("("),
							        		  									                         new CodeSequence(new BestAlternativeCode(true,new CppAlgebraicExpression(),new CppBitwiseExpression(),new CppBooleanExpression()), ","),
							        		  									                         new UniqueKeyword(")")),"," ))),
					 new OptionalCode(new CppThrowsDeclaration()));
		addPatternType(PatternType.CONSTRUCTOR_DECLARATION);

	}

	//Implementation of the the "initialization list"
}
