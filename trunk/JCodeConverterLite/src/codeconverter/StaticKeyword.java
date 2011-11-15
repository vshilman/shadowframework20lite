package codeconverter;

public class StaticKeyword extends Keyword {

	private String[] alternatives=new String[1];

	public StaticKeyword(String keyword) {
		alternatives[0]=keyword;
	}

	@Override
	public String[] getAlternatives() {
		return alternatives;
	}

	@Override
	public ICodePiece cloneCodePiece() {
		return this;
	}

	@Override
	public String toString() {
		return alternatives[0];
	}
}
