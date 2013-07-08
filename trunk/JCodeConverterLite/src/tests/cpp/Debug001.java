package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.CppAttributeDeclaration;

public class Debug001 {


	public static void main(String[] args) {
		CppAttributeDeclaration attr=new CppAttributeDeclaration();
		String code="short[][] paths";
		CodePattern pattern=attr.match(code);
		if(pattern==null){
			System.out.println("Failed");
		} else {
			System.out.println("Success");
		}
		String code2="int* x,y";
		CodePattern pattern2=attr.match(code2);
		if(pattern2==null){
			System.out.println("Failed");
		} else {
			System.out.println("Success");
		}

	}
}
