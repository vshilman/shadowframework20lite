package jcodecomparator.core;

import java.io.InputStream;

import org.eclipse.compare.ITypedElement;

public interface ICompareItem extends ITypedElement {

		public void setInfo(Object element);
		public InputStream getContents();

}
