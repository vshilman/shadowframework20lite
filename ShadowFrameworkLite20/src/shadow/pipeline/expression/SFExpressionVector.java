package shadow.pipeline.expression;

import shadow.math.SFValuenf;

public class SFExpressionVector extends SFExpressionOperator{

	public SFExpressionVector() {
		super(",", SIZE_ALL);
	}

	@Override
	public void evaluateType()  throws SFExpressionException{
		updateSubExpressions();
		
		this.setType(list.getFirst().getType());
	}
	
	@Override
	public SFValuenf evaluate(SFExpressionValuesList values) {
		SFValuenf subValues=new SFValuenf(getElementSize());
		for (int i = 0; i < getElementSize(); i++) {
			subValues.get()[i]=getExpressionElement(i).evaluate(values).get()[0];
		}
		return subValues;
	}
	
	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionVector();
	}
}