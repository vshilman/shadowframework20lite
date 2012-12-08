package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaMethodAccess;

public class Debug004JUnit {

	@Test
	public void test() {

		JavaMethodAccess attributePattern=new JavaMethodAccess();		
		String codeLine="tmpV.addMult(-0.5f, data[index00])";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
	}
		
}
