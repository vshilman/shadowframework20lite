package jcodecomparator.core;

import org.eclipse.jface.viewers.ISelection;

public interface CompareItemConstructor {

	public ICompareItem getCompareItem(ISelection selection);
	public CompareItemConstructor clone();

}
