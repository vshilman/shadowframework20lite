package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableAssignment;

public class Debug006JUnit {

	@Test
	public void test() {
		
		JavaVariableAssignment attributePattern=new JavaVariableAssignment();		
		String codeLine="v[0]=a";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
	}

	
}
