package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.codelines.CppFor;

public class Debug002 {


	public static void main(String[] args) {
		CppFor pat=new CppFor();
		String code="for (int i=0;i< 5;i++)";
		CodePattern cp=pat.match(code);
		if(cp==null){
			System.out.println("Failed");
		} else {
			System.out.println("Success");
		}
		String code2="for (iterator=animals->begin();iterator<animals->end();iterator++)";
		CodePattern cp2=pat.match(code2);
		if(cp2==null){
			System.out.println("Failed");
		} else {
			System.out.println("Success");
		}


	}
}
