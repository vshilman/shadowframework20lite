package shadow.pipeline.expression.data;

import java.util.List;
import java.util.StringTokenizer;

import shadow.pipeline.builder.SFIExpressionBuilder;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionParser {

	private static SFExpressionParser parser = new SFExpressionParser();

	private String brackets = "()";

	private SFExpressionParser() {

	}

	public void parseString(String operation,
			SFParameteri[] set, SFIExpressionBuilder builder) {

		builder.setup();
		String operatorsSum = builder.getOperatorsSum();
		List<String> functions = builder.getAvailableFunctions();

		StringTokenizer tok = new StringTokenizer(operation, brackets, true);

		while (tok.hasMoreTokens()) {
			String token = tok.nextToken();
			int indexB = brackets.indexOf(token);

			if (indexB == 0) {
				builder.openExpression();
			} else if (indexB == 1) {
				builder.closeExpression();
			} else {

				StringTokenizer tok2 = new StringTokenizer(token, operatorsSum,
						true);

				while (tok2.hasMoreTokens()) {
					token = tok2.nextToken();

					if (!operatorsSum.contains(token)) {

						if (!functions.contains(token)) {

							builder.generateValue(token, set);
						} else {

							builder.dispatchFunction(token);
						}

					} else {
						builder.dispatchOperator(token);
					}
				}
			}
		}

	}

	public static SFExpressionParser getParser() {
		return parser;
	}
}
