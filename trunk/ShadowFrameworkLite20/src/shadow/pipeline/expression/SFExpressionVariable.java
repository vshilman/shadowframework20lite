package shadow.pipeline.expression;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFExpressionVariable extends SFExpressionElement {

	public SFExpressionVariable(String element, short type) {
		super(element);
		setType(type);
	}
	
	@Override
	public SFExpressionElement cloneAsIndexed(SFParameteri[] toBeIndexed) {
		for (int i = 0; i < toBeIndexed.length; i++) {
			if(toBeIndexed[i].getName().equalsIgnoreCase(getElement())){
				return new SFExpressionIndex(i);
			}
		}
		return this;
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
	public SFValuenf evaluate(SFExpressionValuesList values) {

		try {
			SFValuenf value = values.generateValue();
			double d = new Double(getElement());
			for (int i = 0; i < value.get().length; i++) {
				value.get()[i] = (float)d;
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("Cannot evaluate unindexed value "+getElement());
		}
	}

}
