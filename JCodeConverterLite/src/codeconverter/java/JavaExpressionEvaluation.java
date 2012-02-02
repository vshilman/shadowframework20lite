package codeconverter.java;

import codeconverter.codepieces.BestAlternativeCode;

public class JavaExpressionEvaluation extends BestAlternativeCode{

	public JavaExpressionEvaluation(){
		super(true,new JavaMethodEvaluation("."),new JavaName(),new JavaExpressionEvaluation());
	}
	
}
