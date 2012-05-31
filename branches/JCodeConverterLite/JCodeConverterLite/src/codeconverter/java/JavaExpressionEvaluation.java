package codeconverter.java;

import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class JavaExpressionEvaluation extends BestAlternativeCode{

	public JavaExpressionEvaluation(){
		super(true,new JoglMethodEvaluation("."),new JavaMethodEvaluation("."),new JavaName(),new JavaExpressionEvaluation());
	}
	
}
