package jcodecomparator.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPersistableElement;

public class CompareEditorInput implements IEditorInput{

	private static final String NAME="Compare Editor";


	private ICompareItem delegate;


	public CompareEditorInput(ICompareItem delegate) {
		super();
		this.delegate = delegate;
	}



	public ICompareItem getDelegate() {
		return delegate;
	}



	@Override
	public Object getAdapter(Class arg0) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return new ImageDescriptor() {
			@Override
			public ImageData getImageData() {
				return delegate.getImage().getImageData();
			}
		};
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return delegate.getName();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CompareEditorInput){
			InputStream mine=delegate.getContents();
			CompareEditorInput cei=(CompareEditorInput) obj;
			InputStream other=cei.getDelegate().getContents();
			InputStreamReader r1=new InputStreamReader(mine);
			InputStreamReader r2=new InputStreamReader(other);
			try {
				int x=r1.read();
				int y=r2.read();
				while(x!=-1 && y!=-1){
					if(x!=y){
						return false;
					}
					 x=r1.read();
					 y=r2.read();
				}
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}




}
