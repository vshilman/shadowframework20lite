package shadow.pipeline.expression;

public class SFExpressionException extends Exception{

	public SFExpressionException(String arg0) {
		super("Bad SFExpression: "+arg0);
	}
}
