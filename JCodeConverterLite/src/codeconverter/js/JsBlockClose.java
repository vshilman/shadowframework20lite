package codeconverter.js;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.OptionalCode;
import codeconverter.StaticKeyword;

public class JsBlockClose extends CodePattern{

	//TODO: it may be useful to know if the optional , or ; are part of the pattern
	
	public JsBlockClose() {
		super("}");
		addCodePiece(new StaticKeyword("}"),new OptionalCode(new StaticKeyword(",")),
				new OptionalCode(new StaticKeyword(";")));
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		//its somehow static...
		return this;
	}

	@Override
	public String toString() {
		return "}";
	}
}
