package shadow.pipeline.openGL20.expressions;

import shadow.pipeline.expression.SFExpressionSum;
import shadow.pipeline.openGL20.SFGLExpressionElementi;

public class SFGL20Sum extends SFExpressionSum implements SFGLExpressionElementi{

	@Override
	public String close() {
		return "";
	}
	@Override
	public String setup() {
		return "";
	}
	@Override
	public String update() {
		return "+";
	}
}
