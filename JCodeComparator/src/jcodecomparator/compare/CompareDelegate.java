package jcodecomparator.compare;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import codeconverter.DifferentiationResult;
import codeconverter.utility.FileStringUtility;

import tests.tmp.GeneralTests;

import jcodecomparator.core.ComparisonExecutingDelegate;
import jcodecomparator.core.ICompareItem;

public class CompareDelegate implements ComparisonExecutingDelegate{

	private static final String logFileName="/Users/nicolapelicano/Desktop/compare_log.txt";
    private StringWriter ws=new StringWriter();


    @Override
    public DifferentiationResult executeComparison(ICompareItem left,
            ICompareItem right) {

        InputStream is1=left.getContents();
        InputStream is2=right.getContents();

        ws.write("Comparing "+left.getName()+ " vs "+right.getName());


        DifferentiationResult res=new DifferentiationResult();

        if(left.getType().equals("js") && right.getType().equals("java")){

        	res=GeneralTests.compareFiles(left.getName(), right.getName(), is1, is2, ws, true);
        }

        if(left.getType().equals("java") && right.getType().equals("js")){

        	res=GeneralTests.compareFiles(right.getName(), left.getName(), is2, is1, ws, false);
        }

        FileStringUtility.writeTextFile(logFileName, ws.toString());

        return res;
    }


}
