package codeconverter.java;

import codeconverter.ICodePiece;
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

	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type.getName();
	}
}
