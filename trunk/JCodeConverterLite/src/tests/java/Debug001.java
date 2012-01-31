package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.JavaAttributeDeclarationAndAssignemnt;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug001 {

	public static void main(String[] args) {
		
		JavaAttributeDeclarationAndAssignemnt attributePattern=new JavaAttributeDeclarationAndAssignemnt();		
		String codeLine="protected short[][] paths=new short[0][]";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
		
	}
}
