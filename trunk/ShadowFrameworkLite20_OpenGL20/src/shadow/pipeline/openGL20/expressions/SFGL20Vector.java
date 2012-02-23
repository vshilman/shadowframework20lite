package shadow.pipeline.openGL20.expressions;

import shadow.pipeline.expression.SFExpressionVector;
import shadow.pipeline.openGL20.SFGLExpressionElementi;

public class SFGL20Vector extends SFExpressionVector implements SFGLExpressionElementi{
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
		return ",";
	}
}
