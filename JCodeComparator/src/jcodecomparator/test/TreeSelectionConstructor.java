package jcodecomparator.test;



import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import jcodecomparator.core.CompareItemConstructor;
import jcodecomparator.core.CompareItemGenerator;
import jcodecomparator.core.ICompareItem;

/**
 *
 * @author Nicola Pellicano'
 *
 */

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
         ICompareItem ci=cig.getCompareItem(first, first.getClass().getCanonicalName());
         return ci;
	}

	@Override
	public CompareItemConstructor clone(){
		return new TreeSelectionConstructor(cig);
	}

}
