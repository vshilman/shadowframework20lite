package jcodecomparator.editors;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jcodecomparator.core.CompareEditorInput;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;

public class CodeCompareEditor extends EditorPart {

	public static final String ID="jcodecomparator.editors.CodeCompareEditor";

	static final double HSPLIT= 0.5;
	static final double VSPLIT= 0.3;
	static final int CENTER_WIDTH=3;

	private double fHSplit= -1;		// width ratio of left and right panes
	private double fVSplit= VSPLIT;		// height ratio of ancestor and bottom panes

	private Action fCopyLeftToRightAction;	// copy from left to right
	private Action fCopyRightToLeftAction;	// copy from right to left

	private StyledText fLeftLabel;
	private StyledText fRightLabel;

	private CompareEditorInput cei;
	private String textToShow;

	@Override
	public void init(IEditorSite arg0, IEditorInput arg1)
			throws PartInitException {
		if(!(arg1 instanceof CompareEditorInput)){
			//TODO Excecption
		}
		CompareEditorInput input=(CompareEditorInput) arg1;
		this.cei=input;
		setSite(arg0);
		setInput(cei);
		setPartName(cei.getName());
		extractText();
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
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


		fLeftLabel=new StyledText(arg0, SWT.NONE);
		fRightLabel=new StyledText(arg0, SWT.NONE);

		GridLayout gl=new GridLayout(2, true);
		arg0.setLayout(gl);

		int headerHeight= fLeftLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).y;
		Rectangle r= arg0.getClientArea();

		int centerWidth= CENTER_WIDTH;

		int width1= (int)((r.width-centerWidth)*HSPLIT);
		int width2= r.width-width1-centerWidth;

		int height1= 0;
		int height2= r.height-headerHeight;


		fLeftLabel.getSize();	// without this resizing would not always work
		fLeftLabel.setBounds(0, 0, width1, headerHeight);
		fRightLabel.setBounds(width1, 0, r.width-width1, headerHeight);

		fLeftLabel.setText(textToShow);
		fRightLabel.setText(textToShow);

	}

	@Override
	public void setFocus() {

	}

	private void extractText () {
		BufferedReader r=new BufferedReader(new InputStreamReader(cei.getDelegate().getContents()));
		try {
			String s=r.readLine();
			String text="";
			while (s!=null){
				text+=s+"\n";
				s=r.readLine();
			}
			textToShow=text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
