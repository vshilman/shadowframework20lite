package shadow.pipeline.expression;

import java.util.List;

import shadow.pipeline.parameters.SFParameteri;

public interface SFExpressionGeneratori {

	public SFExpressionElement getExpressionElement(String value, List<SFParameteri> set);

	public SFExpressionOperator getOperator(String operatorSymbol);

	public SFExpressionTypeWrapper getWrapper(short type);
}