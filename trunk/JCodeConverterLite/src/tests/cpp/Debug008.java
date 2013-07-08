package tests.cpp;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.cpp.codelines.CppVariableDeclarationAndAssignment;
import codeconverter.java.codelines.JavaVariableDeclarationAndAssignment;

public class Debug008 {


		public static void main(String[] args) {

			CppVariableDeclarationAndAssignment attributePattern=new CppVariableDeclarationAndAssignment();
			String codeLine="std::string* symbol=generator->getOperator(token)";
			CodePattern pattern=attributePattern.match(codeLine);
			if(pattern==null)
				System.out.println("Failed");
			else
				System.out.println("Success");


			String codeLine2="Persona* persona =new Persona(new std::string(\"Ciao\"), new utils::Date(1,2,2012))";
			//String codeLine="this.v[0] = x";
			CodePattern pattern2=attributePattern.match(codeLine2);
			if(pattern2==null)
				System.out.println("Failed");
			else
				System.out.println("Success");

			String codeLine3="Persona persona (new std::string(\"Ciao\"), new utils::Date(1,2,2012))";
			//String codeLine="this.v[0] = x";
			CodePattern pattern3=attributePattern.match(codeLine3);
			if(pattern3==null)
				System.out.println("Failed");
			else
				System.out.println("Success");



		}




}
