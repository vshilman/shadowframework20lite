package shadow.pipeline.expression;

import shadow.pipeline.parameters.SFParameter;

public class SFExpressionNumber extends SFExpressionElement{

	public SFExpressionNumber(String element) {
		super(element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		//doing nothing
	}
	
	@Override
	public void evaluateType() {
		//Number is unidentified.
		setType(SFParameter.GLOBAL_FLOAT);
	}
}
