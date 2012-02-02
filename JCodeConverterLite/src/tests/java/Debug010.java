package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaAssignment;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug010 {

	public static void main(String[] args) {
		
		JavaAssignment codePattern=new JavaAssignment();	
		String codeLine="v[2]*=lengthRec";
		//String codeLine="this.v[0] = x";
		CodePattern pattern=codePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
	
	}
}
