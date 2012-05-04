package shadow.pipeline.expression;

import shadow.math.SFValuenf;

public interface SFValuesMap {
	public SFValuenf getValue(String name);
	public SFValuenf generateValue();
}