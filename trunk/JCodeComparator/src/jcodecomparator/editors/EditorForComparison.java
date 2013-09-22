package jcodecomparator.editors;

import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.IAccettableLeftRight;
import jcodecomparator.views.MainView;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * Editor that contains the view that contains the viewer
 *
 * @author Nicola Pellicano'
 *
 */


public class EditorForComparison extends EditorPart implements IAccettableLeftRight{

	private MainView view;


	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite arg0, IEditorInput arg1)
			throws PartInitException {
		setSite(arg0);
		setInput(arg1);
	}


	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite arg0) {
		view=new MainView();
		view.createPartControl(arg0);
	}


	@Override
	public void setFocus() {
	}

	@Override
	public void acceptRight(CompareEditorInput cei) {
		view.acceptRight(cei);
	}

	@Override
	public void acceptLeft(CompareEditorInput cei) {
		view.acceptLeft(cei);
	}


}
