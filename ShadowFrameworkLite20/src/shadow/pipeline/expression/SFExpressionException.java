package shadow.pipeline.expression;

public class SFExpressionException extends Exception{

	private static final long serialVersionUID=0;
	
	public SFExpressionException(String arg0) {
		super("Bad SFExpression: "+arg0);
	}
}
