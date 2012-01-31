package tests.java;

import codeconverter.CodePattern;
import codeconverter.java.codelines.JavaFor;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug002 {

	public static void main(String[] args) {
		
		JavaFor attributePattern=new JavaFor();		
		String codeLine="for (int i = 0;i < data.length;i++)";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
		
	}
}
