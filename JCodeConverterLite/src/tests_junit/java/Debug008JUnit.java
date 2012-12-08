package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableDeclarationAndAssignment;

public class Debug008JUnit {

	@Test
	public void test() {
		JavaVariableDeclarationAndAssignment attributePattern=new JavaVariableDeclarationAndAssignment();		
		String codeLine="SFExpressionOperator symbol=generator.getOperator(token)";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
	}

}
