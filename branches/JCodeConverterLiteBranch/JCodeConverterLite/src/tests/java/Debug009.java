package tests.java;

import codeconverter.CodePattern;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.codelines.JavaIf;

/**
 * This class has been built only for debug issues.
 * It test 1 Java Code Pattern on 1 Java Line of Code.
 * To use it, 
 * @author Alessandro Martinelli 
 */
public class Debug009 {

	public static void main(String[] args) {
		
		JavaIf attributePattern=new JavaIf();		
		String codeLine="if(v!=lastV)";
		//String codeLine="this.v[0] = x";
		CodePattern pattern=attributePattern.match(codeLine);
		if(pattern==null)
			System.out.println("Failed");
		else
			System.out.println("Success");
	
		codeLine="v==lastV";
		JavaBooleanExpression booleanExpression=new JavaBooleanExpression();
		ICodePieceMatch match=booleanExpression.elementMatch(codeLine,0);
		if(match.getMatchPosition()==-1)
			System.out.println("Failed");
		else
			System.out.println("Success "+match.getMatchPosition());
	}
}
