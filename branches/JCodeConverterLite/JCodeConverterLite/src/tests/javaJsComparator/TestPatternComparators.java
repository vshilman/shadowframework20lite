package tests.javaJsComparator;

import java.util.ArrayList;

import codeconverter.CodePattern;
import codeconverter.java.JavaAttributeDeclaration;
import codeconverter.java.JavaAttributeDeclarationAndAssignemnt;
import codeconverter.java.JavaClassDeclaration;
import codeconverter.java.JavaConstructorDeclaration;
import codeconverter.java.JavaIsolatedKeywords;
import codeconverter.java.JavaMethodDeclaration;
import codeconverter.java.codelines.JavaAttributeAssignmentPattern;
import codeconverter.java.codelines.JavaElse;
import codeconverter.java.codelines.JavaFor;
import codeconverter.java.codelines.JavaIf;
import codeconverter.java.codelines.JavaMethodAccess;
import codeconverter.java.codelines.JavaReturnPattern;
import codeconverter.java.codelines.JavaVariableAssignment;
import codeconverter.java.codelines.JavaVariableDeclaration;
import codeconverter.java.codelines.JavaVariableDeclarationAndAssignment;
import codeconverter.java.jogl.JoglMethodAccess;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePatterns.AttributeAndVariableDeclarationAndAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.AttributeAndVariableDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.AttributeAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.ClassDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.ConstructorDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.ElseComparator;
import codeconverter.javaJsComparator.codePatterns.ForComparator;
import codeconverter.javaJsComparator.codePatterns.IfComparator;
import codeconverter.javaJsComparator.codePatterns.IsolatedKeywordsComparator;
import codeconverter.javaJsComparator.codePatterns.MethodAccessComparator;
import codeconverter.javaJsComparator.codePatterns.MethodDeclarationComparator;
import codeconverter.javaJsComparator.codePatterns.OpenGlGenBuffersMethodComparator;
import codeconverter.javaJsComparator.codePatterns.OpenGlMethodAccessComparator;
import codeconverter.javaJsComparator.codePatterns.ReturnComparator;
import codeconverter.javaJsComparator.codePatterns.VariableAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.VariableDeclarationAndAssignmentComparator;
import codeconverter.javaJsComparator.codePatterns.VariableDeclarationComparator;
import codeconverter.js.JsClassDeclaration;
import codeconverter.js.JsConstructorDeclaration;
import codeconverter.js.JsIsolatedKeywords;
import codeconverter.js.JsMethodDeclaration;
import codeconverter.js.codelines.JsAttributeAssignmentPattern;
import codeconverter.js.codelines.JsElse;
import codeconverter.js.codelines.JsFor;
import codeconverter.js.codelines.JsIf;
import codeconverter.js.codelines.JsMethodAccess;
import codeconverter.js.codelines.JsReturnPattern;
import codeconverter.js.codelines.JsVariableAssignment;
import codeconverter.js.codelines.JsVariableDeclaration;
import codeconverter.js.codelines.JsVariableDeclarationAndAssignment;
import codeconverter.js.webgl.WebGlMethodAccess;

public class TestPatternComparators {

	public static void main(String[] args) {

		compare(new ClassDeclarationComparator(), "public class Test_sv1Drawer",
				"Test_sv1Drawer.prototype =", new JavaClassDeclaration(), new JsClassDeclaration());

		compare(new MethodDeclarationComparator(), "public void drawScene(GL2 gl)",
				"drawScene : function(gl)", new JavaMethodDeclaration(), new JsMethodDeclaration());

		compare(new ElseComparator(), "else if(a==b)", "else if(a==b)", new JavaElse(), new JsElse());

		compare(new ForComparator(), "for(int i=0; i<10 ;i++)", "for(var i=0; i<10 ;i++)", new JavaFor(),
				new JsFor());

		compare(new IfComparator(), "if(a==c)", "if(a==c)", new JavaIf(), new JsIf());

		compare(new ConstructorDeclarationComparator(), "public Test_sv1Drawer(int a)",
				"function Test_sv1Drawer(a)", new JavaConstructorDeclaration(),
				new JsConstructorDeclaration());

		compare(new ReturnComparator(), "return a*b+5", "return a*b+5", new JavaReturnPattern(),
				new JsReturnPattern());

		compare(new AttributeAssignmentComparator(), "this.ciao=(int)arc+2*r", "this.ciao=arc+2*r",
				new JavaAttributeAssignmentPattern(), new JsAttributeAssignmentPattern());

		compare(new VariableDeclarationComparator(), "int ciao", "var ciao", new JavaVariableDeclaration(),
				new JsVariableDeclaration());

		compare(new VariableDeclarationAndAssignmentComparator(), "float[] colors2 = new float[16]",
				"var colors2 = new Array()", new JavaVariableDeclarationAndAssignment(),
				new JsVariableDeclarationAndAssignment());

		compare(new VariableAssignmentComparator(), "colors2[c] = 1", "colors2[c] = 1",
				new JavaVariableAssignment(), new JsVariableAssignment());

		compare(new IsolatedKeywordsComparator(), "try", "try", new JavaIsolatedKeywords(),
				new JsIsolatedKeywords());

		compare(new OpenGlMethodAccessComparator(),
				"gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])",
				"gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])", new JoglMethodAccess(),
				new WebGlMethodAccess());

		compare(new MethodAccessComparator(), "ciao.miao(4*f,     new Bau(3*2), miao)",
				"ciao.miao(4*f,new  Bau(3*2), miao)", new JavaMethodAccess(), new JsMethodAccess());

		compare(new AttributeAndVariableDeclarationComparator(), "private int vertexPositionAttribute",
				"var vertexPositionAttribute", new JavaAttributeDeclaration(), new JsVariableDeclaration());

		compare(new AttributeAndVariableDeclarationAndAssignmentComparator(),
				"private int[] triangleVertexPositionBuffer = new int[3]",
				"var triangleVertexPositionBuffer = new Array()",
				new JavaAttributeDeclarationAndAssignemnt(), new JsVariableDeclarationAndAssignment());
		
		compare(new OpenGlGenBuffersMethodComparator(),
				"gl.glGenBuffers(1, triangleVertexPositionBuffer, 0)",
				"triangleVertexPositionBuffer[0] = gl.createBuffer()",
				new JoglMethodAccess(), new JsVariableAssignment());

	}

	private static void compare(CodePatternComparator comparator, String javaLine, String jsLine,
			CodePattern javaPattern, CodePattern jsPattern) {

		ArrayList<CodePattern> javaMatch = new ArrayList<CodePattern>();
		javaMatch.add(javaPattern.match(javaLine));
		ArrayList<CodePattern> jsMatch = new ArrayList<CodePattern>();
		jsMatch.add(jsPattern.match(jsLine));

		if (javaMatch.get(0) != null && jsMatch.get(0) != null) {
			int[][] result = comparator.compare(javaMatch, 0, jsMatch, 0);
			if (result != null) {
				System.out.print("true");
			} else {
				System.out.print("false");
			}
			System.out.println(" --> {" + javaLine + "} , {" + jsLine + "}");
		} else {
			System.out.println("didn't match");
		}
	}

}
