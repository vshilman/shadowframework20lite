package jcodecomparator.compare;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import jcodecomparator.core.ICompareItem;
import jcodecomparator.core.ImageByTypeKeeper;
import org.eclipse.swt.graphics.Image;

public class SelectedTextCompareItem implements ICompareItem{

	private String selectedText;
	private String parentName;
	private ImageByTypeKeeper ibtk;


	public SelectedTextCompareItem(ImageByTypeKeeper ibtk) {
		super();
		this.ibtk = ibtk;
	}

	public void setInformations(String selectedText,String parentName){
		this.selectedText = selectedText;
		this.parentName = parentName;
	}

	@Override
	public InputStream getContents() {
		return new ByteArrayInputStream(selectedText.getBytes());
	}

	@Override
	public String getName() {
		return "Selection from "+parentName;
	}

	@Override
	public Image getImage() {
		return ibtk.getImageByType(getType());
	}

	@Override
	public String getType() {
		String ext=parentName.substring(parentName.lastIndexOf(".")+1);
		return parentName.length()==0? UNKNOWN_TYPE : ext;
	}

	@Override
	public void setInfo(Object element) {
		this.selectedText=(String)element;
	}


}
