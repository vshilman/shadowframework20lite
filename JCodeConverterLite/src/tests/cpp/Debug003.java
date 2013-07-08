package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.cpp.codelines.CppVariableAssignment;
import codeconverter.java.codelines.JavaVariableAssignment;

public class Debug003 {


	public static void main(String[] args) {
		CppVariableAssignment pat=new CppVariableAssignment();
		String code="x=funzione->getValue(xs-EPSILON)";
		CodePattern pattern=pat.match(code);
		if(pattern==null){
			System.out.println("Failed");
		} else {
			System.out.println("Success");
		}

		String codeLine="v[0]=a";
		CodePattern pattern2=pat.match(codeLine);
		if(pattern2==null)
			System.out.println("Failed");
		else
			System.out.println("Success");

		String codeLine2="v[2]*=lengthRec";
		//String codeLine="this.v[0] = x";
		CodePattern pattern3=pat.match(codeLine2);
		if(pattern3==null)
			System.out.println("Failed");
		else
			System.out.println("Success");




	}
}
