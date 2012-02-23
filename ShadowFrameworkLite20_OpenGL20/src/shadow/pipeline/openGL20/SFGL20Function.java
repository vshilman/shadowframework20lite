package shadow.pipeline.openGL20;

import java.util.List;

import shadow.pipeline.SFFunction;
import shadow.pipeline.expression.SFExpressionParser;
import shadow.pipeline.parameters.SFParameteri;

public class SFGL20Function extends SFFunction{

	public SFGL20Function(SFParameteri globalV, String function, List<SFParameteri> set) {
			super(globalV, SFExpressionParser.getParser().parseString(function,set), set);
	}
}
