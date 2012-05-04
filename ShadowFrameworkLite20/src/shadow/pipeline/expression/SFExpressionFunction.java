package shadow.pipeline.expression;

import shadow.math.SFValue1f;
import shadow.math.SFValuenf;


public class SFExpressionFunction extends SFExpressionOperator{

	public SFExpressionFunction(String operatorSymbol) {
		super(operatorSymbol, SIZE_ALL);
	}

	@Override
	public void evaluateType()  throws SFExpressionException{
		updateSubExpressions();
		
		this.setType(list.getFirst().getType());
	}

	
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionFunction(getOperatorSymbol());
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		return new SFValue1f(0);
	}
}
