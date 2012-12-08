package tests_junit.codepatterns;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.CodePattern;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaBooleanExpression;

public class TestCodePattern001JUnit {

	@Test
	public void test() {
		CodePattern pattern=new CodePattern();
		pattern.addCodePiece(new UniqueKeyword("if"),new UniqueKeyword("("),
				new JavaBooleanExpression(),new UniqueKeyword(")"));
		String data="if(a==1)";
		CodePattern matchedPattern=pattern.match(data);
		
		String s="Matching Piece ["+matchedPattern+"]";
		assertEquals(s, "Matching Piece [if ( a  ==1 )]");
	}

}
