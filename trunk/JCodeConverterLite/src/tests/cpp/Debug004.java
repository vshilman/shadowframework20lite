package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.codelines.CppMethodAccess;

public class Debug004 {

	public static void main(String[] args) {

		CppMethodAccess attributePattern=new CppMethodAccess();
		String codeLine="tmpV->addMult(-0.5f, data[index00])"; //ok also with "."
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
	}
}
