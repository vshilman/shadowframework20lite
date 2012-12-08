package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaIf;

public class Debug005JUnit {

	@Test
	public void test() {
		JavaIf attributePattern=new JavaIf();		
		String codeLine="if(rName[0]=='N')";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Success", s);
		
	}

}
