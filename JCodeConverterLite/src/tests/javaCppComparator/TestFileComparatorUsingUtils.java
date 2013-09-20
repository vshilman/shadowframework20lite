package tests.javaCppComparator;

import java.io.StringWriter;
import java.util.Iterator;

import tests.tmp.GeneralTests;
import codeconverter.CodeModule;
import codeconverter.DifferentiationResult;
import codeconverter.utility.FileStringUtility;

public class TestFileComparatorUsingUtils {

	private static StringWriter logWriter = new StringWriter();

	public static void main(String[] args) {
		String name = "\n SFParameter.java VS SFParameter.js\n";

		System.out.println(name);

		String javaFileName = "SFParameter.java";
		String jsFileName = "SFParameter.js";


		logWriter.write(name + "\n");

		DifferentiationResult res=GeneralTests.compareFiles(jsFileName, javaFileName,FileStringUtility.getStream(jsFileName),FileStringUtility.getStream(javaFileName) , logWriter,true);

		String infos="Non interpretate sinistra:\n";

		for (int j = 0; j < res.getUninterpretatesLeft().size(); j++) {
			infos+=res.getUninterpretatesLeft().get(j).getCode()+"\n\n";
		}

		infos+="Non interpretate destra:\n";

		for (int j = 0; j < res.getUninterpretatesRight().size(); j++) {
			infos+=res.getUninterpretatesRight().get(j).getCode()+"\n\n";
		}


		infos+="Differenti sinistra:\n";

		if(res.getDifferentLeft()!=null){
			for (Iterator<CodeModule> iterator = res.getDifferentLeft().iterator(); iterator.hasNext();) {
				String s=iterator.next().getCode();
				infos+=s+"\n\n";
			}
		}
		infos+="Differenti destra:\n";

		if(res.getDifferentRight()!=null){
			for (Iterator<CodeModule> iterator = res.getDifferentRight().iterator(); iterator.hasNext();) {
				String s=iterator.next().getCode();
				infos+=s+"\n\n";

			}
		}
		System.out.println(infos);
	}
}
