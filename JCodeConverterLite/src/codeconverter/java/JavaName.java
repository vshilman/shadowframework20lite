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
		generate2(new JavaNamePart());
	}	
	
	public JavaName(PieceType type){
		generate2(new JavaNamePart(type));
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
	
	private void generate2(JavaNamePart namePart) {
		JavaAlgebraicExpression algebraicExpression =new JavaAlgebraicExpression(true);
		JavaBitwiseExpression bitwiseExpression=new JavaBitwiseExpression(true);
		JavaMethodEvaluation javaMethodEvaluation=new JavaMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		JoglMethodEvaluation joglMethodEvaluation=new JoglMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		generate(namePart,algebraicExpression,bitwiseExpression);
		JavaNewStatement newStatement=new JavaNewStatement(algebraicExpression, this);
		algebraicExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this,new JavaTernaryOperator(algebraicExpression),newStatement);
		bitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this,newStatement);
	}

	public void generate(JavaNamePart part,JavaAlgebraicExpression algebraicExpression, JavaBitwiseExpression bitwiseExpression) {
		if(part==null){
			part=new JavaNamePart();
		}
		add(part,new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("<"),new CodeSequence(new Name(PieceType.NAME),","),new UniqueKeyword(">"))
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
			//allIntervals.add(new CharInterval('.','.'));  //TODO verify
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
			setPieceType(PieceType.NAME);
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
