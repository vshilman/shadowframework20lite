package codeconverter.js.webgl;

import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

public class WebGlConstant extends CompositeCodePiece {

	public WebGlConstant() {
		super();
		add( new UniqueKeyword("gl."), new Name());
	}
}
