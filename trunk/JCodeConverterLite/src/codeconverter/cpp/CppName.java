package codeconverter.cpp;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.ConcreteValue;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class CppName extends CompositeCodePiece{


	public CppName() {
		generate2(new CppNamePart());
	}

	public CppName(PieceType type) {
		generate2(new CppNamePart(type));
		setPieceType(type);
	}

	public CppName(CppAlgebraicExpression algebraicExpression, CppBitwiseExpression bitwiseExpression) {
		generate(new CppNamePart(), algebraicExpression,bitwiseExpression);
	}

	public CppName(boolean notGenerate) {

	}

	public CppName(PieceType type,CppAlgebraicExpression algebraicExpression, CppBitwiseExpression bitwiseExpression) {
		generate(new CppNamePart(type),algebraicExpression,bitwiseExpression);
		setPieceType(type);
	}


	public void generate(CppNamePart part, CppAlgebraicExpression algebraicExpression, CppBitwiseExpression bitwiseExpression){
		if(part==null){
			part=new CppNamePart();
		}
		add(part,/*Cpp has template classes*/new OptionalCode(new CompositeCodePiece(new UniqueKeyword("<"),
														      new CodeSequence(new Name(PieceType.NAME), ","),
														      new UniqueKeyword(">"))),
			     new OptionalCode(new CompositeCodePiece(new UniqueKeyword("["),
			    		 								  new OptionalCode(new BestAlternativeCode(true, algebraicExpression,bitwiseExpression)),
			    		 								  new UniqueKeyword("]"))));
	}


	public void generate2(CppNamePart part){
		CppAlgebraicExpression algebraicExpression=new CppAlgebraicExpression(true);
		CppBitwiseExpression bitwiseExpression=new CppBitwiseExpression(true);
		CppMethodEvaluation cppMethodEvaluation=new CppMethodEvaluation("->",algebraicExpression,bitwiseExpression); /*-> most common used */
		generate(part,algebraicExpression,bitwiseExpression);
		CppNewStatement newStatement=new CppNewStatement(algebraicExpression,new CppCompositeType());
		algebraicExpression.generate(cppMethodEvaluation, this, new CppTernaryOperator(algebraicExpression), newStatement);
		bitwiseExpression.generate(cppMethodEvaluation, this, newStatement);
	}



	private static class CppNamePart extends ConcreteValue{


		public CppNamePart(PieceType type) {
			super();
			setIntervals();
			setPieceType(type);
		}


		public CppNamePart() {
			super();
			setIntervals();
			setPieceType(PieceType.NAME);
		}


		@Override
		public void setIntervals() {
			startingIntervals.clear();
			allIntervals.clear();
			startingIntervals.add(new CharInterval('a', 'z'));
			startingIntervals.add(new CharInterval('A', 'Z'));
			startingIntervals.add(new CharInterval('"', '"'));
			startingIntervals.add(new CharInterval('\'', '\''));
			startingIntervals.add(new CharInterval('*', '*')); //pointers?? [int *x]
			allIntervals.add(new CharInterval('a', 'z'));
			allIntervals.add(new CharInterval('A', 'Z'));
			allIntervals.add(new CharInterval('0', '9'));
			allIntervals.add(new CharInterval('.', '.'));
			allIntervals.add(new CharInterval('_', '_'));
			allIntervals.add(new CharInterval('"', '"'));
			allIntervals.add(new CharInterval('\'', '\''));

		}







	}


}
