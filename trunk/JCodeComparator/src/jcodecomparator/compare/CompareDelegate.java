package jcodecomparator.compare;

import java.io.InputStream;
import codeconverter.ComparisonDelegate;
import codeconverter.DifferentiationResult;
import codeconverter.factories.test.TestComparatorFactory;
import codeconverter.factories.test.TestLanguagesObjectsFactory;
import jcodecomparator.core.ComparisonExecutingDelegate;
import jcodecomparator.core.ICompareItem;

/**
 * @author Nicola Pellicano'
 *
 */

public class CompareDelegate implements ComparisonExecutingDelegate{




    @Override
    public DifferentiationResult executeComparison(ICompareItem left,
            ICompareItem right) {

        InputStream is1=left.getContents();
        InputStream is2=right.getContents();


        DifferentiationResult res=new DifferentiationResult();

        ComparisonDelegate cd=new ComparisonDelegate(new TestComparatorFactory(), new TestLanguagesObjectsFactory());

        res=cd.compareFiles(left.getName(), right.getName(), is1, is2);

        if(res!=null){
        	return res;
        }
        return new DifferentiationResult();
    }


}
