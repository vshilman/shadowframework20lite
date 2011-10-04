package shadow.pipeline.expression;

public interface SFExpressionElementInterpreter {
	public void startElement(SFExpressionElement element);
	public void refreshElement(SFExpressionElement element);
	public void closeElement(SFExpressionElement element);
}
