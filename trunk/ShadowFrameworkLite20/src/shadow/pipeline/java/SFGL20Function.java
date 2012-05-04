package shadow.pipeline.java;

import java.util.List;

import shadow.pipeline.SFFunction;
import shadow.pipeline.builder.SFExpressionBuilder;
import shadow.pipeline.expression.data.SFExpressionParser;
import shadow.pipeline.parameters.SFParameteri;

public class SFGL20Function extends SFFunction{

	public SFGL20Function(SFParameteri globalV, String function, List<SFParameteri> set) {
		super(globalV, null, set);
		SFExpressionBuilder builder=new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function,set,builder);
		this.setFunction(builder.getBuiltExpression());
	}
}
