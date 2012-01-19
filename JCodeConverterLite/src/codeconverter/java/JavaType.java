package codeconverter.java;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.elements.Type;

public class JavaType extends JavaName {

	private Type type;

	@Override
	public ICodePiece cloneCodePiece() {
		JavaType type = new JavaType();
		type.data = data;
		type.type=new Type(data);
		return type;
	}
	
	@Override
	public PieceType getPieceType() {
		return PieceType.TYPE;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type.getName();
	}
}
