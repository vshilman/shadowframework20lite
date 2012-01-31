package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableAssignment;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug006 {

	public static void main(String[] args) {
		
		JavaVariableAssignment attributePattern=new JavaVariableAssignment();		
		String codeLine="v[0]=a";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
		
	}
}
