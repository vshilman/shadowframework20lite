package jcodecomparator.compare;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.swt.graphics.Image;

import jcodecomparator.core.ICompareItem;
import jcodecomparator.core.ImageByTypeKeeper;

/**
 *
 * @author Nicola Pellicano'
 *
 */


@SuppressWarnings("restriction")
public class CompilationUnitCompareItem implements ICompareItem{


	private CompilationUnit cu;
	private ImageByTypeKeeper ibtk;

	public CompilationUnitCompareItem(ImageByTypeKeeper ibtk) {
		super();
		this.ibtk = ibtk;
	}


	@Override
	public Image getImage() {
		return ibtk.getImageByType(getType());
	}

	@Override
	public String getName() {
		return cu.getElementName();
	}

	@Override
	public String getType() {
		return "java";
	}

	@Override
	public void setInfo(Object element) {
		this.cu=(CompilationUnit)element;

	}

	@Override
	public InputStream getContents() {
		try {
			return new ByteArrayInputStream(cu.getSource().getBytes());
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}


