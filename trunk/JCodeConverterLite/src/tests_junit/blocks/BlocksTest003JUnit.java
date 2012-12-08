package tests_junit.blocks;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import tests.blocks.BlockUtilities;

import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.javatojs.JSCodeTranslator;
import codeconverter.utility.FileStringUtility;
import junit.framework.TestCase;

public class BlocksTest003JUnit extends TestCase {

	public void test(){
		
		List<String> list=FileStringUtility.loadTextFile("src/testPackage/Expressions.java");
		
		StringWriter writer=new StringWriter();
		for (String string : list) {
			writer.write(string);
		}
		
		String totalString=writer.toString();
		int beginof=totalString.indexOf("/*");
		int endof=totalString.indexOf("*/");
		while(beginof!=-1 && endof!=-1){
			String tmp=totalString.substring(0,beginof);
			tmp+=totalString.substring(endof+2);
			totalString=tmp;
			beginof=totalString.indexOf("/*");
			endof=totalString.indexOf("*/");
		}
		
		String s=totalString+"\n";
		
		char[] totalStringChars=totalString.toCharArray();

		Block fileBlock=BlockUtilities.generateBlocks(totalStringChars);
		
		BlockInterpreter interpreter=new BlockInterpreter(new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation=interpreter.getInterpretation(fileBlock);
		
		JSCodeTranslator translator=new JSCodeTranslator();
		
		String translation=translator.translateCode(fileBlock, interpretation);
		
		s+="Something to write?\n";
		
		s+=translation;
		
		assertEquals(s,"package testPackage;public class Expressions {		public String a=\"Ciao\";		public static int methodTest(int a,int b,int c){				for (int i=0; i < c; i++) {			a+=c;		}				a = b + c;				return a+3;	}			public static int methodTest1(int a,int b,int c){				a = b * c;				return a+b;	}	public static int methodTest2(int a,int b,int c){				a = b + ( c * a );				return a;	}}\nSomething to write?\n\nfunction Expressions(){\n}\n\nExpressions.prototype = {\n\n\tmethodTest  :function(a  , b  , c  ){\n\t\tfor ( int i   = 0 ; i   <  c   ; i   ++  ){\n\t\ta   + =  c  ;\n\t}\n\t\ta    =  b  + c  ;\n\t\treturn   a  +3;\n\t},\n\n\tmethodTest1  :function(a  , b  , c  ){\n\t\ta    =  b  * c  ;\n\t\treturn   a  + b  ;\n\t},\n\n\tmethodTest2  :function(a  , b  , c  ){\n\t\ta    =  b  + (  c  * a   );\n\t\treturn   a  ;\n\t}\n\n};" );
	}
	
	
	
}
