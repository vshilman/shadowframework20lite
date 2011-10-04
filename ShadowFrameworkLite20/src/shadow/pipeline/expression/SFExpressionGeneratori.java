package shadow.pipeline.expression;

import java.util.Collection;

import shadow.pipeline.parameters.SFParameteri;

public interface SFExpressionGeneratori {


	public SFExpressionElement getExpressionElement(String value, Collection<SFParameteri> set);

	public SFExpressionOperator getOperator(String operatorSymbol);

	public SFExpressionTypeWrapper getWrapper(short type);
}