package shadow.pipeline.expression;

import shadow.math.SFValuenf;

public interface SFExpressionValuesList {
	public SFValuenf getValue(int index);
	public SFValuenf generateValue();
}