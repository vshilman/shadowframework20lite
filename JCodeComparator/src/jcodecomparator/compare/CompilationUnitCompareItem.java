package jcodecomparator.compare;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.swt.graphics.Image;

import jcodecomparator.core.ICompareItem;
import jcodecomparator.core.ImageByTypeKeeper;

public class CompilationUnitCompareItem implements ICompareItem{

	private CompilationUnit cu;
	private ImageByTypeKeeper ibtk;

	public CompilationUnitCompareItem(ImageByTypeKeeper ibtk) {
		super();
		this.ibtk = ibtk;
	}

/*	private String extractInfo(){

		String info="";
		File file=cu.getJavaElement().getResource().getFullPath().toFile();
		try {
			BufferedReader r=new BufferedReader(new FileReader(file));
			String s=r.readLine();
			while(s!=null){
				info+=s+"\n";
				s=r.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
*/

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


