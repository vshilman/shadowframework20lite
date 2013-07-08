package jcodecomparator.core;

import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;

public interface LineBackgroundStylerListener extends LineStyleListener {

	public void cleanToConsider();

	public void setBackground(Point pos, Color color);

}