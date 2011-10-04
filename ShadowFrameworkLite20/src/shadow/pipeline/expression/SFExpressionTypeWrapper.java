package shadow.pipeline.expression;

public class SFExpressionTypeWrapper extends SFExpressionElement{

	public SFExpressionTypeWrapper(short type) {
		super("wr"+type);
		setType(type);
	}
	
	public SFExpressionTypeWrapper(short type,SFExpressionElement wrappedExpression) {
		super("wr"+type);
		setType(type);
		addSubExpression(wrappedExpression);
	}

	@Override
	public void addSubExpression(SFExpressionElement element)
			throws ArrayIndexOutOfBoundsException {
		list.add(element);
	}
	
	@Override
	public void evaluateType() {
		//nothing to do.
	}
}
