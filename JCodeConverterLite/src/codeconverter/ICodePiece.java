package codeconverter;

/**
 * A Piece of Code which can identify itself on a String 
 * 
 * @author Alessandro Martinelli
 */
public interface ICodePiece extends ICodeElement {

	/**
	 * Verifiy if this CodePiece is present in the String starting from the matchPosition location
	 * 
	 * Return -1 if there is no match
	 * 
	 * @param data
	 * @param matchPosition
	 * @return
	 */
	public int elementMatch(String data, int matchPosition);
}
