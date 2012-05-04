package shadow.pipeline.expression.data;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import shadow.pipeline.builder.SFExpressionBuilder;
import shadow.pipeline.builder.SFIExpressionBuilder;
import shadow.pipeline.expression.SFBasicExpressionGenerator;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.expression.SFExpressionException;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;

public class SFExpressionParser {

	private static SFExpressionParser parser = new SFExpressionParser();

	private String brackets = "()";

	private SFExpressionParser() {

	}

	public void parseString(String operation,
			List<SFParameteri> set, SFIExpressionBuilder builder) {

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

	public static void main(String[] args) {

		// String operation="u*u*u*A+v*v*v*A";

		String operation = "projection*modelview*A";

		// String operation="a+c:0:1";
		// String operation="(_a+_b)/_c*(_d*_a/(_b+_e))";
		// String operation="NA°NB*F+C";//Still no troubles would say

		SFExpressionGeneratorKeeper.getKeeper().setGenerator(
				new SFBasicExpressionGenerator());

		SFParameter A = new SFParameter("A", SFParameter.GLOBAL_FLOAT3);
		SFParameter B = new SFParameter("B", SFParameter.GLOBAL_FLOAT3);
		SFParameter C = new SFParameter("C", SFParameter.GLOBAL_FLOAT3);
		SFParameter modelview = new SFParameter("modelview",
				SFParameter.GLOBAL_MATRIX4);
		SFParameter projection = new SFParameter("projection",
				SFParameter.GLOBAL_MATRIX4);
		List<SFParameteri> set = new LinkedList<SFParameteri>();
		set.add(A);
		set.add(B);
		set.add(C);
		set.add(projection);
		set.add(modelview);

		SFExpressionBuilder builder=new SFExpressionBuilder();
		parser.parseString(operation, set,builder);
		SFExpressionElement element = builder.getBuiltExpression();

		SFExpressionElementInterpreter interpreter = new SFExpressionElementInterpreter() {
			@Override
			public void closeElement(SFExpressionElement element) {
				System.out.print(")");
			}

			@Override
			public void refreshElement(SFExpressionElement element) {
				System.out.print(",");
			}

			@Override
			public void startElement(SFExpressionElement element) {
				System.out.print("(" + element.getElement() + "T"
						+ element.getType() + ",");

			}
		};
		element.traverse(interpreter);
		System.out.println();
		try {
			element.evaluateType();
		} catch (SFExpressionException e) {
			e.printStackTrace();
		}
		element.traverse(interpreter);
	}
}
