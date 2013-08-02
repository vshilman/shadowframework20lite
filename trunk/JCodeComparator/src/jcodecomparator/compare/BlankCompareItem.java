package jcodecomparator.compare;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;

import jcodecomparator.core.ICompareItem;

public class BlankCompareItem implements ICompareItem{

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "java";
	}

	@Override
	public void setInfo(Object element) {

	}

	@Override
	public InputStream getContents() {
		return new ByteArrayInputStream(getName().getBytes());
	}



}
