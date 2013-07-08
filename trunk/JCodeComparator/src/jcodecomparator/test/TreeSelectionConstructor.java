package jcodecomparator.test;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import jcodecomparator.core.CompareItemConstructor;
import jcodecomparator.core.CompareItemGenerator;
import jcodecomparator.core.ICompareItem;

public class TreeSelectionConstructor implements CompareItemConstructor{

	private CompareItemGenerator cig;


	public TreeSelectionConstructor(CompareItemGenerator cig) {
		super();
		this.cig = cig;
	}

	@Override
	public ICompareItem getCompareItem(ISelection selection) {
		TreeSelection ts = (TreeSelection) selection;
         Object first=ts.getFirstElement();
         MessageConsole myconsole = findConsole("console");
         MessageConsoleStream out = myconsole.newMessageStream();
         out.println(first.getClass().getCanonicalName());
         ICompareItem ci=cig.getCompareItem(first, first.getClass().getCanonicalName());
         return ci;
	}

	@Override
	public CompareItemConstructor clone(){
		return new TreeSelectionConstructor(cig);
	}
	 private MessageConsole findConsole(String name) {
	        ConsolePlugin plugin = ConsolePlugin.getDefault();
	        IConsoleManager conMan = plugin.getConsoleManager();
	        IConsole[] existing = conMan.getConsoles();
	        for (int i = 0; i < existing.length; i++) {
	            if (name.equals(existing[i].getName())) {
	                return (MessageConsole) existing[i];
	            }
	        }
	        //no console found, so create a new one
	        MessageConsole myConsole = new MessageConsole(name, null);
	        conMan.addConsoles(new IConsole[]{myConsole});
	        return myConsole;
	    }
}
