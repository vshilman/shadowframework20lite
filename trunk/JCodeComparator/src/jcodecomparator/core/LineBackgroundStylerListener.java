package jcodecomparator.core;

import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;

/**
 * Sets a different background to the code differences
 *
 * @author Nicola Pellicano'
 *
 */

public interface LineBackgroundStylerListener extends LineStyleListener {

	/**
	 * Remove all differences
	 */
	public void cleanToConsider();


	 /**
	  * Add a difference
	  */

	public void setBackground(Point pos, Color color);

	/**
	 * Clone
	 * @return
	 */

	public LineBackgroundStylerListener clone();

}