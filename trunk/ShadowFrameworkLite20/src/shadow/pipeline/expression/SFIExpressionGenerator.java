package shadow.pipeline.expression;


public interface SFIExpressionGenerator {

	public SFExpressionElement getExpressionElement(String value, short type);

	public SFExpressionOperator getOperator(String operatorSymbol);

	public SFExpressionTypeWrapper getWrapper(short type);
}