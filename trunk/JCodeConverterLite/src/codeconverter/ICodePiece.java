package codeconverter;

public interface ICodePiece extends ICodeElement {

	/**
	 * Return -1 if there is no match
	 * 
	 * @param data
	 * @param matchPosition
	 * @return
	 */
	public int elementMatch(String data, int matchPosition);
}
