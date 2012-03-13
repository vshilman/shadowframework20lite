package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaVariableDeclaration;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug008 {

	public static void main(String[] args) {
		
		JavaVariableDeclaration attributePattern=new JavaVariableDeclaration();		
		String codeLine="SFExpressionOperator symbol=generator.getOperator(token)";
		//String codeLine="this.v[0] = x";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
		
	}
}