package jcodecomparator.core;

/**
 * Defines a class that can receive a CompareEditorInput on his right "side" or left "side"
 *
 * @author nicolapelicano
 *
 */


public interface IAccettableLeftRight {

	/**
	 * Set the CEI on the right
	 *
	 * @param cei
	 */

	public void acceptRight(CompareEditorInput cei);

	/**
	 * Set the CEI on the left
	 *
	 * @param cei
	 */

	public void acceptLeft(CompareEditorInput cei);

}
