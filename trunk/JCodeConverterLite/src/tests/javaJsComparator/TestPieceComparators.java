package tests.javaJsComparator;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaBitwiseExpression;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;
import codeconverter.java.JavaTernaryOperator;
import codeconverter.java.jogl.JoglConstant;
import codeconverter.java.jogl.JoglMethodEvaluation;
import codeconverter.javaJsComparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsBooleanExpression;
import codeconverter.js.JsMethodEvaluation;
import codeconverter.js.JsName;
import codeconverter.js.JsNewStatement;
import codeconverter.js.JsTernaryOperator;
import codeconverter.js.webgl.WebGlConstant;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class TestPieceComparators {

	public static void main(String[] args) {

		compare(new BooleanExpressionComparator(), "a==d*(6+2)", "a == d*(6+2)", new JavaBooleanExpression(),
				new JsBooleanExpression());

		compare(new ExpressionComparator(), "(a|5)<<(d&(6>>2))", "(a|5)<<(d&(6>>2))",
				new JavaBitwiseExpression(), new JsBitwiseExpression());

		compare(new ExpressionComparator(), "(a/ 5) -(d*(6+ 2))", "(a/5)-(d*(6+2))",
				new JavaAlgebraicExpression(), new JsAlgebraicExpression());

		compare(new MethodComparator(), "ciao.miao(4*f,     new Bau(3*2), miao)",
				"ciao.miao(4*f,new  Bau(3*2), miao)", new JavaMethodEvaluation("."), new JsMethodEvaluation(
						"."));

		compare(new NameComparator(), "ciao<Ciao>[(pippo+2)+2]", "ciao[(pippo+2)+2]", new JavaName(),
				new JsName());

		compare(new NewStatementComparator(), "new  Bau(g*5)", "new Bau(g*5)", new JavaNewStatement(),
				new JsNewStatement());

		compare(new OpenGlConstantComparator(), "GL2.GL_ELEMENT_ARRAY_BUFFER", "gl.ELEMENT_ARRAY_BUFFER",
				new JoglConstant(), new WebGlConstant());

		compare(new OpenGlMethodComparator(),
				"gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])",
				"gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])", new JoglMethodEvaluation(
						"."), new WebGlMethodEvaluation("."));

		compare(new TernaryOperatorComparator(), "filter == 2 ? 0 : (filter + 1)",
				"filter == 2 ? 0 : (filter + 1)", new JavaTernaryOperator(), new JsTernaryOperator());

	}

	private static void compare(CodePieceComparator comparator, String javaLine, String jsLine,
			ICodePiece javaPiece, ICodePiece jsPiece) {
		ICodePieceMatch javaMatch = javaPiece.elementMatch(javaLine, 0);
		ICodePieceMatch jsMatch = jsPiece.elementMatch(jsLine, 0);

		// System.out.println(jsMatch.getDataPiece().printTypes(0));

		if (javaMatch.getMatchPosition() != -1 && jsMatch.getMatchPosition() != -1) {
			System.out.println(comparator.compare(javaMatch.getDataPiece(), jsMatch.getDataPiece())
					+ " --> {" + javaLine + "} , {" + jsLine + "}");
		} else {
			System.out.println("didn't match");
		}
	}

}
