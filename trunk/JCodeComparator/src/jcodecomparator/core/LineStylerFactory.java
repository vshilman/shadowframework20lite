package jcodecomparator.core;


/**
 * Returns the line styler for each language
 *
 * @author Nicola Pellicano'
 *
 */

public interface LineStylerFactory {

	public LineBackgroundStylerListener getLineStyler(String type);

}
