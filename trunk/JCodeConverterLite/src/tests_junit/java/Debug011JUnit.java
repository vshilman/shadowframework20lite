package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableDeclarationAndAssignment;

public class Debug011JUnit {

	@Test
	public void test() {
		
		JavaVariableDeclarationAndAssignment codePattern=new JavaVariableDeclarationAndAssignment();	
		String codeLine="SFVertex3f v=new SFVertex3f(0,0,0)";
		CodePattern pattern=codePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
		
	}

}
