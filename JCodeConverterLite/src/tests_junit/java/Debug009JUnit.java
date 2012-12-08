package tests_junit.java;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaBooleanExpression;
import codeconverter.java.codelines.JavaIf;

public class Debug009JUnit {

	@Test
	public void test() {
		
		JavaIf attributePattern=new JavaIf();		
		String codeLine="if(v!=lastV)";
		CodePattern pattern=attributePattern.match(codeLine);
		String s="";
		if(pattern==null)
			s+="Failed\n";
		else
			s+="Success\n";
	
		codeLine="v==lastV";
		JavaBooleanExpression booleanExpression=new JavaBooleanExpression();
		ICodePieceMatch match=booleanExpression.elementMatch(codeLine,0);
		if(match.getMatchPosition()==-1)
			s+="Failed";
		else
			s+="Success "+match.getMatchPosition();
		
		
		assertEquals("Success\nSuccess 8", s);	
	}

}
