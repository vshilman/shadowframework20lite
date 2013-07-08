package tests.cpp;

import codeconverter.CodePattern;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.cpp.CppBooleanExpression;
import codeconverter.cpp.codelines.CppIf;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.codelines.JavaIf;

public class Debug009 {

public static void main(String[] args) {

		CppIf attributePattern=new CppIf();
		String codeLine="if(v!=lastV)";
		//String codeLine="this.v[0] = x";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");

		codeLine="v==lastV";
		CppBooleanExpression booleanExpression=new CppBooleanExpression();
		ICodePieceMatch match=booleanExpression.elementMatch(codeLine,0);
		if(match.getMatchPosition()==-1)
			System.out.println("Failed");
		else
			System.out.println("Success "+match.getMatchPosition());
	}
}
