package codeconverter.java.jogl;

import codeconverter.codepieces.BestAlternativeCode;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.java.JavaName;

public class JoglConstant extends CompositeCodePiece {

	public JoglConstant() {
		super();
		add(new BestAlternativeCode(true, new UniqueKeyword("GL"), new UniqueKeyword("GL2")), 
				new UniqueKeyword(".GL_"), new JavaName());
	}
}
