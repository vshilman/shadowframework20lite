package codeconverter.java;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.codepieces.Value;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaName extends CompositeCodePiece{

	public JavaName(){
		JavaAlgebraicExpression algebraicExpression =new JavaAlgebraicExpression(true);
		JavaBitwiseExpression bitwiseExpression=new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation=new JavaMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation=new JoglMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		generate(new JavaNamePart(),algebraicExpression,bitwiseExpression);
		algebraicExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this);
		bitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this);
	}	
	
	public JavaName(PieceType type){
		JavaAlgebraicExpression algebraicExpression =new JavaAlgebraicExpression(true);
		JavaBitwiseExpression bitwiseExpression=new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation=new JavaMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation=new JoglMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		algebraicExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this);
		bitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this);
		generate(new JavaNamePart(type),algebraicExpression,bitwiseExpression);
		setPieceType(type);
	}
	
	public JavaName(JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression){
		generate(new JavaNamePart(),algebraicExpression,bitwiseExpression);
	}
	
	public JavaName(boolean notGenerate) {
	}

	public JavaName(PieceType type,JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression){
		generate(new JavaNamePart(type),algebraicExpression,bitwiseExpression);
		setPieceType(type);
	}

	public void generate(JavaNamePart part,JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression) {
		if(part==null){
			part=new JavaNamePart();
		}
		add(part,new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("<"),new CodeSequence(new Name(),","),new UniqueKeyword(">"))
				),
				new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("["),new OptionalCode(new BestAlternativeCode(false, algebraicExpression,bitwiseExpression)),new UniqueKeyword("]"))
					)
		);
	}

	
	private static class JavaNamePart extends Value{
	
		private static ArrayList<CharInterval> startingIntervals=new ArrayList<Value.CharInterval>();
		private static ArrayList<CharInterval> allIntervals=new ArrayList<Value.CharInterval>();
		private static ArrayList<CharInterval> endingIntervals=new ArrayList<Value.CharInterval>();
		
		static{
			startingIntervals.add(new CharInterval('a','z'));
			startingIntervals.add(new CharInterval('A','Z'));
			startingIntervals.add(new CharInterval('"','"'));
			startingIntervals.add(new CharInterval('\'','\''));
			allIntervals.add(new CharInterval('a','z'));
			allIntervals.add(new CharInterval('A','Z'));
			allIntervals.add(new CharInterval('0','9'));
			allIntervals.add(new CharInterval('.','.'));
			allIntervals.add(new CharInterval('_','_'));
			//allIntervals.add(new CharInterval('[',']'));
			allIntervals.add(new CharInterval('"','"'));
			allIntervals.add(new CharInterval('\'','\''));
		}
	
		public JavaNamePart(PieceType piecetype) {
			super();
			setPieceType(piecetype);
		}
		
		public JavaNamePart() {
			super();
		}
	
		@Override
		public List<CharInterval> getAvailableIntervals(int position) {
			if(position==0)
				return startingIntervals;
			return allIntervals;
		}
		
		@Override
		public List<CharInterval> getEndCharacter() {
			return endingIntervals;
		}

	}
}
