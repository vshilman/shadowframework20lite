package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.CompositeCodePiece;

public class JavaArrayDeclaration extends CompositeCodePiece{

	public JavaArrayDeclaration() {
		add(new JavaNewStatement(),new JavaArrayContent());
		setPieceType(PieceType.ARRAY_DECLARATION);
	}
}
