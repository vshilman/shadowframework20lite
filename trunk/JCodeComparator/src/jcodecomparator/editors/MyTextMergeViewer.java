package jcodecomparator.editors;

import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.IAccettableLeftRight;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.contentmergeviewer.TextMergeViewer;
import org.eclipse.swt.widgets.Composite;

public class MyTextMergeViewer extends TextMergeViewer implements IAccettableLeftRight{

	private CompareEditorInput rightInput;
	private CompareEditorInput leftInput;

	public MyTextMergeViewer(Composite parent,
			CompareConfiguration configuration) {
		super(parent, configuration);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void acceptRight(CompareEditorInput cei) {
		if(cei!=null){
			rightInput=cei;
			
		}

	}

	@Override
	public void acceptLeft(CompareEditorInput cei) {
		if(cei!=null){
			leftInput=cei;
			updateContent(null,leftInput.getDelegate(),null);
		}

	}




}
