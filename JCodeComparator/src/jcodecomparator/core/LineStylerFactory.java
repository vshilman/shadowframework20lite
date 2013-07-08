package jcodecomparator.core;

import org.eclipse.swt.custom.LineStyleListener;

public interface LineStylerFactory {

	public LineBackgroundStylerListener getLineStyler(String type);

}
