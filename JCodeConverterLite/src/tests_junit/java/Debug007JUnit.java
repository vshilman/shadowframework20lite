package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaAttributeAssignmentPattern;

public class Debug007JUnit {

	@Test
	public void test() {
		JavaAttributeAssignmentPattern attributePattern=new JavaAttributeAssignmentPattern();		
		String codeLine="this.v[0] = (float)x";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
	}

}
