package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableDeclaration;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug011 {

	public static void main(String[] args) {
		
		JavaVariableDeclaration codePattern=new JavaVariableDeclaration();	
		String codeLine="SFVertex3f v=new SFVertex3f(0,0,0)";
		//String codeLine="this.v[0] = x";
		CodePattern pattern=codePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
	
	}
}
