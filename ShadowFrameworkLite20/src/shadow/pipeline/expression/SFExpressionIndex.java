package shadow.pipeline.expression;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionIndex extends SFExpressionElement {

	private int index;
	
	public SFExpressionIndex(int index) {
		super(""+index);
		this.index=index;
		setType(SFParameteri.GLOBAL_GENERIC);
	}

	@Override
	public void addSubExpression(SFExpressionElement element) throws ArrayIndexOutOfBoundsException {
		// Nothing to do
	}
	
	@Override
	public SFExpressionElement cloneAsIndexed(SFParameteri[] toBeIndexed) {
		return this;
	}

	@Override
	public void evaluateType() throws SFExpressionException {
		// Nothing to do..
	}

	@Override
	public SFValuenf evaluate(SFExpressionValuesList values) {
		return values.getValue(index);
	}

}
