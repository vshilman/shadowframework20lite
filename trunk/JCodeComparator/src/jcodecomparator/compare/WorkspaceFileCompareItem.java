package jcodecomparator.compare;

import java.io.InputStream;
import jcodecomparator.core.ICompareItem;
import jcodecomparator.core.ImageByTypeKeeper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

public class WorkspaceFileCompareItem implements ICompareItem{

	private IFile file;
	private ImageByTypeKeeper ibtk;


	public WorkspaceFileCompareItem(ImageByTypeKeeper ibtk) {
		super();
		this.ibtk = ibtk;
	}


	@Override
	public Image getImage() {
		return ibtk.getImageByType(getType());
	}

	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public String getType() {
		return file.getFileExtension();
	}

	@Override
	public InputStream getContents() {
		try {
			return file.getContents();
		} catch (CoreException e) {
			//Ignore that case
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setInfo(Object element) {
		this.file=(IFile)element;
	}



}
