package jcodecomparator.test;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import jcodecomparator.compare.SelectedTextCompareItem;
import jcodecomparator.compare.WorkspaceFileCompareItem;
import jcodecomparator.core.CompareItemConstructor;
import jcodecomparator.core.ICompareItem;

public class TextSelectionConstructor implements CompareItemConstructor{

	@Override
	public ICompareItem getCompareItem(ISelection selection) {
		ITextSelection ts=(ITextSelection) selection;
		IEditorPart ed=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
           if(ts.getText().isEmpty() || ts.getText()==null){
           	if(ed!=null){
           		IEditorInput ei=ed.getEditorInput();
           		if(ei==null){
           				return null;
           		}
           		 Object adapter = ei.getAdapter(IFile.class);
           	     IFile adfile=(IFile) adapter;
           	     WorkspaceFileCompareItem wfc=new WorkspaceFileCompareItem(new ConcreteImageByTypeKeeper());
           	     wfc.setInfo(adfile);
           	     return wfc;
           	} else {
           		return null;
           	}

           } else {
        	   SelectedTextCompareItem stc=new SelectedTextCompareItem(new ConcreteImageByTypeKeeper());
        	   IFile parentFile=(IFile)ed.getEditorInput().getAdapter(IFile.class);
        	   stc.setInformations(ts.getText(),parentFile.getName());
        	   return stc;
       }
	}

	@Override
	public CompareItemConstructor clone(){
		return new TextSelectionConstructor();
	}

}
