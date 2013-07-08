package jcodecomparator.editors;

import jcodecomparator.compare.CompareDelegate;
import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.IAccettableLeftRight;
import jcodecomparator.test.TestLineStyleFactory;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class EditorForComparison extends EditorPart implements IAccettableLeftRight{

	private CompareEditorViewer viewer;


	@Override
	public void dispose() {
		super.dispose();

	}


	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite arg0, IEditorInput arg1)
			throws PartInitException {
		setSite(arg0);
		setInput(arg1);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite arg0) {
		int style=SWT.MULTI;
		  viewer=new CompareEditorViewer(arg0, style, new CompareConfiguration(),new CompareDelegate(),new TestLineStyleFactory());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void acceptRight(CompareEditorInput cei) {
		viewer.setRightInput(cei);
	}

	@Override
	public void acceptLeft(CompareEditorInput cei) {
		viewer.setLeftInput(cei);
	}


}
