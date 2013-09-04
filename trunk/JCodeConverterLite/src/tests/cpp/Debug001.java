package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.CppAttributeDeclaration;
import codeconverter.cpp.CppConstrutorDeclaration;

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

		CppConstrutorDeclaration dec=new CppConstrutorDeclaration();
		String code3="SFValue1f::SFValue1f(int n,float data[]):SFValuenf(n,data)";
		CodePattern pattern3=dec.match(code3);
		if(pattern3==null){
			System.out.println("Failed");
		}else{
			System.out.println("Success");
		}


	}
}
