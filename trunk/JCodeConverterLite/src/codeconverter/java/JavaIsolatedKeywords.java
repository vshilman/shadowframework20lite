package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.Keyword;

public class JavaIsolatedKeywords extends CodePattern{

	public Keyword isolatedKeyword=new Keyword() {
		
		private String[] isolatedKeyword={
			"@Override",
			"static{",
			"else",
			"else{",
			"}else{",
		};
		
		@Override
		public ICodeElement cloneCodePiece() {
			return this;
		}
		
		@Override
		public String[] getAlternatives() {
			return isolatedKeyword;
		}
	};
	
	
	public JavaIsolatedKeywords() {
		super("Java Isolated Keyword");
		addCodePiece(isolatedKeyword);
	}

	@Override
	public ICodeElement cloneCodePiece() {
		return new JavaIsolatedKeywords();
	}
	
}
