package shadow.pipeline.data;

import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFIExpressionGenerator;
import shadow.pipeline.expression.SFExpressionOperator;
import shadow.pipeline.expression.SFExpressionTypeWrapper;

public class SFDataExpressionGenerator implements SFIExpressionGenerator{

	@Override
	public SFExpressionElement getExpressionElement(String value,short type) {
		return new SFDataExpressionElement(value,type);
	}
	
	@Override
	public SFExpressionTypeWrapper getWrapper(short type) {
		return new SFDataExpressionTypeWrapper(type);
	}
	
	@Override
	public SFExpressionOperator getOperator(String operatorSymbol) {
		return new SFDataExpressionOperator(operatorSymbol);
	}
}
