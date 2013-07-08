package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.codelines.CppIf;
import codeconverter.java.codelines.JavaIf;

public class Debug005{

	public static void main(String[] args) {

		CppIf attributePattern=new CppIf();
		String codeLine="if(rName[0]=='N')";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");

	}
	}

