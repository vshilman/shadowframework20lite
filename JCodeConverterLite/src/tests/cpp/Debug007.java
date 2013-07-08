package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.codelines.CppAttributeAssignmentPattern;

public class Debug007 {


	public static void main(String[] args) {
		CppAttributeAssignmentPattern attributePattern=new CppAttributeAssignmentPattern();
		String codeLine="this.v[0] = (float)x";
		//String codeLine="this.v[0] = x";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");

		String codeLine2="this->v[0] = (float)x";
		//String codeLine="this.v[0] = x";
		CodePattern pattern2=attributePattern.match(codeLine2);
		if(pattern2==null)
			System.out.println("Failed");
		else
			System.out.println("Success");






	}



}
