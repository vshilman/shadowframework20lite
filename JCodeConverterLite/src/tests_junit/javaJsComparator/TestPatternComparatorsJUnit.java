package tests_junit.javaJsComparator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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

public class TestPatternComparatorsJUnit {

	@Test
	public void test() {
		
		String s="";
		
		s+=compare(new ClassDeclarationComparator(), "public class Test_sv1Drawer",
				"Test_sv1Drawer.prototype =", new JavaClassDeclaration(), new JsClassDeclaration());

		s+=compare(new MethodDeclarationComparator(), "public void drawScene(GL2 gl)",
				"drawScene : function(gl)", new JavaMethodDeclaration(), new JsMethodDeclaration());

		s+=compare(new ElseComparator(), "else if(a==b)", "else if(a==b)", new JavaElse(), new JsElse());

		s+=compare(new ForComparator(), "for(int i=0; i<10 ;i++)", "for(var i=0; i<10 ;i++)", new JavaFor(),
				new JsFor());

		s+=compare(new IfComparator(), "if(a==c)", "if(a==c)", new JavaIf(), new JsIf());

		s+=compare(new ConstructorDeclarationComparator(), "public Test_sv1Drawer(int a)",
				"function Test_sv1Drawer(a)", new JavaConstructorDeclaration(),
				new JsConstructorDeclaration());

		s+=compare(new ReturnComparator(), "return a*b+5", "return a*b+5", new JavaReturnPattern(),
				new JsReturnPattern());

		s+=compare(new AttributeAssignmentComparator(), "this.ciao=(int)arc+2*r", "this.ciao=arc+2*r",
				new JavaAttributeAssignmentPattern(), new JsAttributeAssignmentPattern());

		s+=compare(new VariableDeclarationComparator(), "int ciao", "var ciao", new JavaVariableDeclaration(),
				new JsVariableDeclaration());

		s+=compare(new VariableDeclarationAndAssignmentComparator(), "float[] colors2 = new float[16]",
				"var colors2 = new Array()", new JavaVariableDeclarationAndAssignment(),
				new JsVariableDeclarationAndAssignment());

		s+=compare(new VariableAssignmentComparator(), "colors2[c] = 1", "colors2[c] = 1",
				new JavaVariableAssignment(), new JsVariableAssignment());

		s+=compare(new IsolatedKeywordsComparator(), "try", "try", new JavaIsolatedKeywords(),
				new JsIsolatedKeywords());

		s+=compare(new OpenGlMethodAccessComparator(),
				"gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])",
				"gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])", new JoglMethodAccess(),
				new WebGlMethodAccess());

		s+=compare(new MethodAccessComparator(), "ciao.miao(4*f,     new Bau(3*2), miao)",
				"ciao.miao(4*f,new  Bau(3*2), miao)", new JavaMethodAccess(), new JsMethodAccess());

		s+=compare(new AttributeAndVariableDeclarationComparator(), "private int vertexPositionAttribute",
				"var vertexPositionAttribute", new JavaAttributeDeclaration(), new JsVariableDeclaration());

		s+=compare(new AttributeAndVariableDeclarationAndAssignmentComparator(),
				"private int[] triangleVertexPositionBuffer = new int[3]",
				"var triangleVertexPositionBuffer = new Array()",
				new JavaAttributeDeclarationAndAssignemnt(), new JsVariableDeclarationAndAssignment());

		s+=compare(new OpenGlGenBuffersMethodComparator(),
				"gl.glGenBuffers(1, triangleVertexPositionBuffer, 0)",
				"triangleVertexPositionBuffer[0] = gl.createBuffer()", new JoglMethodAccess(),
				new JsVariableAssignment());
		
		s+=compare(new VariableDeclarationAndAssignmentComparator(),
				"float[] vertices ={0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f}",
				"var vertices = [ 0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0 ]", new JavaVariableDeclarationAndAssignment(),
				new JsVariableDeclarationAndAssignment());
		
		assertEquals(s, "true --> {public class Test_sv1Drawer} , {Test_sv1Drawer.prototype =}\ntrue --> {public void drawScene(GL2 gl)} , {drawScene : function(gl)}\ntrue --> {else if(a==b)} , {else if(a==b)}\ntrue --> {for(int i=0; i<10 ;i++)} , {for(var i=0; i<10 ;i++)}\ntrue --> {if(a==c)} , {if(a==c)}\ntrue --> {public Test_sv1Drawer(int a)} , {function Test_sv1Drawer(a)}\ntrue --> {return a*b+5} , {return a*b+5}\ntrue --> {this.ciao=(int)arc+2*r} , {this.ciao=arc+2*r}\ntrue --> {int ciao} , {var ciao}\ntrue --> {float[] colors2 = new float[16]} , {var colors2 = new Array()}\ntrue --> {colors2[c] = 1} , {colors2[c] = 1}\ntrue --> {try} , {try}\ntrue --> {gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])} , {gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, cubeVertexIndexBuffer[0])}\ntrue --> {ciao.miao(4*f,     new Bau(3*2), miao)} , {ciao.miao(4*f,new  Bau(3*2), miao)}\ntrue --> {private int vertexPositionAttribute} , {var vertexPositionAttribute}\ntrue --> {private int[] triangleVertexPositionBuffer = new int[3]} , {var triangleVertexPositionBuffer = new Array()}\ntrue --> {gl.glGenBuffers(1, triangleVertexPositionBuffer, 0)} , {triangleVertexPositionBuffer[0] = gl.createBuffer()}\ntrue --> {float[] vertices ={0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f}} , {var vertices = [ 0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0 ]}\n");
	}
	
	
	private static String compare(CodePatternComparator comparator, String javaLine, String jsLine,
			CodePattern javaPattern, CodePattern jsPattern) {

		ArrayList<CodePattern> javaMatch = new ArrayList<CodePattern>();
		javaMatch.add(javaPattern.match(javaLine));
		ArrayList<CodePattern> jsMatch = new ArrayList<CodePattern>();
		jsMatch.add(jsPattern.match(jsLine));
		String s="";
		
		if (javaMatch.get(0) != null && jsMatch.get(0) != null) {
			// System.out.println(javaMatch.get(0).printTypes());
			// System.out.println(jsMatch.get(0).printTypes());
			int[][] result = comparator.compare(javaMatch, 0, jsMatch, 0);
			if (result != null) {
				s+="true";
			} else {
				s+="false";
			}
			s+=" --> {" + javaLine + "} , {" + jsLine + "}";
		} else {
			s+="didn't match";
		}
		return s+"\n";
	}
}
