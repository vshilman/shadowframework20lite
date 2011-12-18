package codeconverter;

public abstract class Value  implements ICodePiece{

	protected String data="";

	public abstract int elementMatch(String data, int matchPosition);

	public Value() {
		super();
	}

	protected boolean stays(char eval, char min, char max) {
		return (min<=eval && eval <=max);
	}

	@Override
	public ICodePiece cloneCodePiece() {
		Name name = new Name();
		name.data=data;
		return name;
	}

	public String getData() {
		return data;
	}

	@Override
	public String toString() {
		return data;
	}

}