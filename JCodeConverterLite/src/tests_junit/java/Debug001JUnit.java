package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.java.JavaAttributeDeclarationAndAssignemnt;

public class Debug001JUnit {

	@Test
	public void test() {
		JavaAttributeDeclarationAndAssignemnt attributePattern=new JavaAttributeDeclarationAndAssignemnt();		
		String codeLine="protected short[][] paths=new short[0][]";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed";
		else
			s+="Success";
		
		assertEquals("Failed", s);
	}

}
