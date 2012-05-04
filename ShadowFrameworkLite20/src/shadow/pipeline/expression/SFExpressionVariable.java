package shadow.pipeline.expression;

import shadow.math.SFValuenf;

public class SFExpressionVariable extends SFExpressionElement {

	public SFExpressionVariable(String element, short type) {
		super(element);
		setType(type);
	}

	@Override
	public void addSubExpression(SFExpressionElement element) throws ArrayIndexOutOfBoundsException {
		// Nothing to do
	}

	@Override
	public void evaluateType() throws SFExpressionException {
		// Nothing to do..
	}

	@Override
	public SFValuenf evaluate(SFValuesMap values) {

		try {
			return values.getValue(getElement()).cloneValue();
		} catch (ArrayIndexOutOfBoundsException e) {
			
			SFValuenf value = values.generateValue();
			float d = new Float(getElement());
			for (int i = 0; i < value.get().length; i++) {
				value.get()[i] = d;
			}
			return value;
		}
	}

}
