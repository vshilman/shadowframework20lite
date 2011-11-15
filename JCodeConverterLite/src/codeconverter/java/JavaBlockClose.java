package codeconverter.java;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.StaticKeyword;

public class JavaBlockClose extends CodePattern{

	public JavaBlockClose() {
		super("}");
		addCodePiece(new StaticKeyword("}"));
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		return this;
	}

	@Override
	public String toString() {
		return "}";
	}
}
