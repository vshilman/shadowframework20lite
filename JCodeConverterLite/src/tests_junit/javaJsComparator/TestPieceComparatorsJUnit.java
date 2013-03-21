package tests_junit.javaJsComparator;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.Number;
import codeconverter.java.JavaAlgebraicExpression;
import codeconverter.java.JavaArrayContent;
import codeconverter.java.JavaArrayDeclaration;
import codeconverter.java.JavaBitwiseExpression;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.JavaMethodEvaluation;
import codeconverter.java.JavaMethodVariables;
import codeconverter.java.JavaName;
import codeconverter.java.JavaNewStatement;
import codeconverter.java.JavaTernaryOperator;
import codeconverter.java.JavaVariable;
import codeconverter.java.jogl.JoglConstant;
import codeconverter.java.jogl.JoglMethodEvaluation;
import codeconverter.javaJsComparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.ArrayContentComparator;
import codeconverter.javaJsComparator.codePieces.ArrayDeclarationComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.MethodVariablesComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.NumberComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;
import codeconverter.js.JsAlgebraicExpression;
import codeconverter.js.JsArrayContent;
import codeconverter.js.JsArrayDeclaration;
import codeconverter.js.JsBitwiseExpression;
import codeconverter.js.JsBooleanExpression;
import codeconverter.js.JsMethodEvaluation;
import codeconverter.js.JsMethodVariables;
import codeconverter.js.JsName;
import codeconverter.js.JsNewStatement;
import codeconverter.js.JsTernaryOperator;
import codeconverter.js.JsVariable;
import codeconverter.js.webgl.WebGlConstant;
import codeconverter.js.webgl.WebGlMethodEvaluation;

public class TestPieceComparatorsJUnit {

	@Test
	public void test() {
		
		String s="";
		
		s+=compare(new BooleanExpressionComparator(), "a==d*(6+2)", "a == d*(6+2)", new JavaBooleanExpression(),
				new JsBooleanExpression());

		s+=compare(new ExpressionComparator(), "(a|5)<<(d&(6>>2))", "(a|5)<<(d&(6>>2))",
				new JavaBitwiseExpression(), new JsBitwiseExpression());

		s+=compare(new ExpressionComparator(), "(a/ 5.2f) -(d*(6+ 2))", "(a/5.2)-(d*(6+2))",
				new JavaAlgebraicExpression(), new JsAlgebraicExpression());

		s+=compare(new MethodComparator(), "ciao.miao(4*f,     new Bau(3*2), miao)",
				"ciao.miao(4*f,new  Bau(3*2), miao)", new JavaMethodEvaluation("."), new JsMethodEvaluation(
						"."));

		s+=compare(new NameComparator(), "ciao<Ciao>[(pippo+2)+2]", "ciao[(pippo+2)+2]", new JavaName(),
				new JsName());

		s+=compare(new NewStatementComparator(), "new  Bau(g*5)", "new Bau(g*5)", new JavaNewStatement(),
				new JsNewStatement());

		s+=compare(new OpenGlConstantComparator(), "GL2.GL_ELEMENT_ARRAY_BUFFER", "gl.ELEMENT_ARRAY_BUFFER",
				new JoglConstant(), new WebGlConstant());

		s+=compare(new OpenGlMethodComparator(),
				"gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])",
				"gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])", new JoglMethodEvaluation(
						"."), new WebGlMethodEvaluation("."));

		s+=compare(new TernaryOperatorComparator(), "filter == 2 ? 0 : (filter + 1)",
				"filter == 2 ? 0 : (filter + 1)", new JavaTernaryOperator(), new JsTernaryOperator());

		s+=compare(new MethodVariablesComparator(), "GL2 gl, int miao, String bau", "gl,miao,bau",
				new JavaMethodVariables(), new JsMethodVariables());

		s+=compare(new VariableComparator(), "int ciao", "var ciao", new JavaVariable(), new JsVariable());

		s+=compare(new NumberComparator(), "1.0f", "1.0", new Number(), new Number());

		s+=compare(new ArrayContentComparator(), "{0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f}",
				"[0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0]", new JavaArrayContent(),
				new JsArrayContent());

		s+=compare(new ArrayDeclarationComparator(), "new float[] { 1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,4 }",
				"new Float32Array([ 1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,4 ])", new JavaArrayDeclaration(),
				new JsArrayDeclaration());
		
		
		assertEquals("true --> {a==d*(6+2)} , {a == d*(6+2)}\ntrue --> {(a|5)<<(d&(6>>2))} , {(a|5)<<(d&(6>>2))}\ntrue --> {(a/ 5.2f) -(d*(6+ 2))} , {(a/5.2)-(d*(6+2))}\ntrue --> {ciao.miao(4*f,     new Bau(3*2), miao)} , {ciao.miao(4*f,new  Bau(3*2), miao)}\ntrue --> {ciao<Ciao>[(pippo+2)+2]} , {ciao[(pippo+2)+2]}\ntrue --> {new  Bau(g*5)} , {new Bau(g*5)}\ntrue --> {GL2.GL_ELEMENT_ARRAY_BUFFER} , {gl.ELEMENT_ARRAY_BUFFER}\ntrue --> {gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])} , {gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])}\ntrue --> {filter == 2 ? 0 : (filter + 1)} , {filter == 2 ? 0 : (filter + 1)}\ntrue --> {GL2 gl, int miao, String bau} , {gl,miao,bau}\ntrue --> {int ciao} , {var ciao}\ntrue --> {1.0f} , {1.0}\ntrue --> {{0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f}} , {[0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0]}\ntrue --> {new float[] { 1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,4 }} , {new Float32Array([ 1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,4 ])}\n", s);
		
	}
	
	private static String compare(CodePieceComparator comparator, String javaLine, String jsLine,
			ICodePiece javaPiece, ICodePiece jsPiece) {
		ICodePieceMatch javaMatch = javaPiece.elementMatch(javaLine, 0);
		ICodePieceMatch jsMatch = jsPiece.elementMatch(jsLine, 0);
		String s="";
		// System.out.println(jsMatch.getDataPiece().printTypes(0));

		if (javaMatch.getMatchPosition() != -1 && jsMatch.getMatchPosition() != -1) {
			s+=comparator.compare(javaMatch.getDataPiece(), jsMatch.getDataPiece())
					+ " --> {" + javaLine + "} , {" + jsLine + "}";
		} else {
			s+="didn't match";
		}
		
		return s+"\n";
	}

}
