package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaMethodAccess;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug004 {

	public static void main(String[] args) {
		
		JavaMethodAccess attributePattern=new JavaMethodAccess();		
		String codeLine="tmpV.addMult(-0.5f, data[index00])";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
		
	}
}
