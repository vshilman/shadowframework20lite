package jcodecomparator.core;

import org.eclipse.jface.viewers.ISelection;

public interface CompareItemConstructorFactory {

	public CompareItemConstructor generateCompareItemConstructor(String canonicalName);
}
