package jcodecomparator.test;

import jcodecomparator.core.DefaultScanner;

public class JavaScanner extends DefaultScanner{

	public JavaScanner() {
		super();
		fgKeywords=new String[]{"abstract", "boolean", "break", "byte",
				"case", "catch", "char", "class", "continue", "default", "do",
				"double", "else", "extends", "false", "final", "finally",
				"float", "for", "if", "implements", "import", "instanceof",
				"int", "interface", "long", "native", "new", "null", "package",
				"private", "protected", "public", "return", "short", "static",
				"super", "switch", "synchronized", "this", "throw", "throws",
				"transient", "true", "try", "void", "volatile", "while" };
		initialize();
	}

}
