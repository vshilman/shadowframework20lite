package jcodecomparator.editors;

import java.util.ResourceBundle;

import jcodecomparator.core.CompareEditorInput;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.contentmergeviewer.ContentMergeViewer;
import org.eclipse.compare.internal.BufferedCanvas;
import org.eclipse.compare.internal.ICompareContextIds;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

public class ProvaViewer extends ContentMergeViewer{

	public static final String BUNDLE_NAME= "org.eclipse.compare.internal.ImageMergeViewerResources";

	private CompareEditorInput rightInput;
	private CompareEditorInput leftInput;

	private StyledText textLeft;
	private StyledText textRight;





	public ProvaViewer (Composite parent, int styles, CompareConfiguration mp) {
		super(styles, ResourceBundle.getBundle(BUNDLE_NAME), mp);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, ICompareContextIds.IMAGE_COMPARE_VIEW);
		buildControl(parent);
		String title= Utilities.getString(getResourceBundle(), "title"); //$NON-NLS-1$
		getControl().setData(CompareUI.COMPARE_VIEWER_TITLE, title);

	}


	protected void updateContent(Object ancestor, Object left, Object right) {

		if(left!=null){
			leftInput= (CompareEditorInput) left;
			//setInput(fLeft, left);
		}
		if(right!=null){
			rightInput= (CompareEditorInput) right;
			//setInput(fRight, right);
		}
	}

	@Override
	protected void copy(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createControls(Composite parent) {
		textRight=new StyledText(parent, SWT.NONE);

	}

	@Override
	protected byte[] getContents(boolean left) {

		if(left){
			return leftInput.getDelegate().getContents().toString().getBytes();
		}

		return rightInput.getDelegate().getContents().toString().getBytes();
	}

	@Override
	protected void handleResizeAncestor(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void handleResizeLeftRight(int x, int y, int width1, int centerWidth, int width2, int height) {
		//fLeft.setBounds(x, y, width1, height);
		//fRight.setBounds(x+width1+centerWidth, y, width2, height);
	}



}
