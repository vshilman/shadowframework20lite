package codeconverter.js;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.codepieces.Value;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class JsName extends CompositeCodePiece{

	public JsName(){
		generate2(new JsNamePart());
	}	
	
	public JsName(PieceType type){
		generate2(new JsNamePart(type));
		setPieceType(type);
	}
	
	public JsName(JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression){
		generate(new JsNamePart(),algebraicExpression,bitwiseExpression);
	}
	
	public JsName(boolean notGenerate) {
	}

	public JsName(PieceType type,JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression){
		generate(new JsNamePart(type),algebraicExpression,bitwiseExpression);
		setPieceType(type);
	}
	
	private void generate2(JsNamePart namePart) {
		JsAlgebraicExpression algebraicExpression =new JsAlgebraicExpression(true);
		JsBitwiseExpression bitwiseExpression=new JsBitwiseExpression(true);
		JsMethodEvaluation javaMethodEvaluation=new JsMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		WebGlMethodEvaluation joglMethodEvaluation=new WebGlMethodEvaluation(".", algebraicExpression, bitwiseExpression);
		generate(namePart,algebraicExpression,bitwiseExpression);
		algebraicExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this,new JsTernaryOperator(algebraicExpression));
		bitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this);
	}

	public void generate(JsNamePart part,JsAlgebraicExpression algebraicExpression, JsBitwiseExpression bitwiseExpression) {
		if(part==null){
			part=new JsNamePart();
		}
		add(part,
				new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("["),new OptionalCode(new BestAlternativeCode(false, algebraicExpression,bitwiseExpression)),new UniqueKeyword("]"))
					)
		);
	}

	
	private static class JsNamePart extends Value{
	
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
	
		public JsNamePart(PieceType piecetype) {
			super();
			setPieceType(piecetype);
		}
		
		public JsNamePart() {
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
