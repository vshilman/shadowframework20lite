package shadow.pipeline.expression;

import java.util.LinkedList;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionSum extends SFExpressionOperator {

	public SFExpressionSum() {
		super("+", SIZE_ALL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateType() throws SFExpressionException {
		updateSubExpressions();

		// Get a list of all Elements which have a different type from previous
		// one
		LinkedList<SFExpressionElement> cElements = getTypesSeparatorList();

		if (cElements.size() > 1) {

			// Tells if there are only vectors.
			boolean onlyVectors = hasOnlyVectors(cElements);
			if (onlyVectors) {
				short maxElement = separateAndWrap(cElements);
				this.setType(maxElement);
				return;
			} else {
				throwBadOperandsType(cElements);
			}
		}

		this.setType(list.getFirst().getType());
	}

	protected SFExpressionOperator cloneOperator() {
		return new SFExpressionSum();
	}
	
	@Override
	public SFValuenf evaluate(SFValuesMap values) {
		SFValuenf sum=getExpressionElement(0).evaluate(values);
		for (int i = 1; i < getElementSize(); i++) {
			sum.addMult(1, getExpressionElement(i).evaluate(values));
		}
		return sum;
	}
}