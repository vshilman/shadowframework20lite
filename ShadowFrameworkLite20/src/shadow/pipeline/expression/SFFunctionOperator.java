package shadow.pipeline.expression;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFFunctionOperator extends SFExpressionOperator{

	
	public SFFunctionOperator(String function) {
		super(function, SIZE_ALL);
	}

	@Override
	public void evaluateType()  throws SFExpressionException{
		updateSubExpressions();
		if(getElement().equalsIgnoreCase("sample"))
			setType(SFParameteri.GLOBAL_FLOAT4);
		else if(getElement().equalsIgnoreCase("clamp"))
			this.setType(list.get(0).getType());
		else if(getElement().equalsIgnoreCase("normalize"))
			this.setType(list.get(0).getType());
		else if(getElement().equalsIgnoreCase("cross"))
			this.setType(list.get(0).getType());
		else
			this.setType(SFParameteri.GLOBAL_FLOAT);
	}
	
	protected SFExpressionOperator cloneOperator() {
		return new SFFunctionOperator(getElement());
		
	}
	
//	@Override
//	public SFValuenf evaluate(SFValuesMap values) {
//		SFValuenf value1=getExpressionElement(0).evaluate(values);
//		SFValuenf value2=getExpressionElement(1).evaluate(values);
//		return new SFValue1f(value1.dot(value2));
//	}
	
	@Override
	public SFValuenf evaluate(SFExpressionValuesList values) {
		throw new SFException("Yet not implemented");
	}
}
