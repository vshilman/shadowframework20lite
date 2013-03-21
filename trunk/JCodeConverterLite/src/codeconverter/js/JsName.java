package codeconverter.js;

import codeconverter.PieceType;
import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.ConcreteValue;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
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
		JsBitwiseExpression bitwiseExpression = new JsBitwiseExpression(true);
		JsMethodEvaluation javaMethodEvaluation = new JsMethodEvaluation(".", algebraicExpression,
				bitwiseExpression);
		WebGlMethodEvaluation joglMethodEvaluation = new WebGlMethodEvaluation(".", algebraicExpression,
				bitwiseExpression);
		generate(namePart, algebraicExpression, bitwiseExpression);
		JsNewStatement newStatement = new JsNewStatement(algebraicExpression, this, new JsArrayContent(
				algebraicExpression, bitwiseExpression));
		algebraicExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this, new JsTernaryOperator(
				algebraicExpression), newStatement);
		bitwiseExpression.generate(javaMethodEvaluation, joglMethodEvaluation, this,newStatement);
	}

	public void generate(JsNamePart part, JsAlgebraicExpression algebraicExpression,
			JsBitwiseExpression bitwiseExpression) {
		if (part==null){
			part=new JsNamePart();
		}
		add(part,
				new OptionalCode(new CompositeCodePiece(
						new UniqueKeyword("["),new OptionalCode(new BestAlternativeCode(false, algebraicExpression,bitwiseExpression)),new UniqueKeyword("]"))
					)
		);
	}

	
	private static class JsNamePart extends ConcreteValue{
	
		
		static{
			
		}
	
		public JsNamePart(PieceType piecetype) {
			super();
			setIntervals();
			setPieceType(piecetype);
		}
		
		public JsNamePart() {
			super();
			setIntervals();
			setPieceType(PieceType.NAME);
		}

		@Override
		public void setIntervals() {
			startingIntervals.clear();
			allIntervals.clear();
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

	}
}
