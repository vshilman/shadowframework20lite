package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableAssignment;

public class Debug010JUnit {

	@Test
	public void test() {
		
		JavaVariableAssignment codePattern=new JavaVariableAssignment();	
		String codeLine="v[2]*=lengthRec";
		CodePattern pattern=codePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
	}

}
