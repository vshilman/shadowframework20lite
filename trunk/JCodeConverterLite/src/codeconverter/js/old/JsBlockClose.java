package codeconverter.js.old;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;

public class JsBlockClose extends CodePattern{

	//TODO: it may be useful to know if the optional , or ; are part of the pattern
	
	public JsBlockClose() {
		super("}");
		addCodePiece(new UniqueKeyword("}"),new OptionalCode(new UniqueKeyword(",")),
				new OptionalCode(new UniqueKeyword(";")));
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
