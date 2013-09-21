package jcodecomparator.compare;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import jcodecomparator.core.ICompareItem;
import jcodecomparator.core.ImageByTypeKeeper;

import org.eclipse.swt.graphics.Image;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class ExternalFileCompareItem implements ICompareItem{

	private File file;
	private ImageByTypeKeeper ibtk;


	public ExternalFileCompareItem(ImageByTypeKeeper ibtk) {
		super();
		this.ibtk = ibtk;
	}


	public ExternalFileCompareItem(ImageByTypeKeeper ibtk,File file) {
		super();
		this.ibtk = ibtk;
		this.file=file;
	}


	private String getFileContents(){
		String fileContent="";
		try {
			BufferedReader r=new BufferedReader(new FileReader(file));
			String s=r.readLine();
			fileContent="";
			while(s!=null){
				fileContent+=s+"\n";
				s=r.readLine();
			}
			r.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
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

		if(file.isDirectory()){
			return FOLDER_TYPE;
		}

		if(file==null){
			return UNKNOWN_TYPE;
		}

		String name=getName();
		String ext=name.substring(name.lastIndexOf(".")+1);

		return name.length()==0? UNKNOWN_TYPE : ext;
	}



	@Override
	public InputStream getContents() {
		return new ByteArrayInputStream(getFileContents().getBytes());
	}


	@Override
	public void setInfo(Object element) {
		this.file=(File) element;
	}



}
