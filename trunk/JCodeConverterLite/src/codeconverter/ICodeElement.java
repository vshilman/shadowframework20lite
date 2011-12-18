package codeconverter;

import java.util.List;

/**
 * An Element in the Code which has been identified
 * 
 * @author Alessandro
 */
public interface ICodeElement{

	/**
	 * Clone this code Element. Clone is usually called 
	 * just after the element has been identified. 
	 * The cloned item keep information which may be lost
	 * on the original element. 
	 * 
	 * @return  
	 */
	public abstract ICodeElement cloneCodePiece();

}