package codeconverter;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import tests.tmp.GeneralTests;

import codeconverter.comparator.CodePatternComparator;
import codeconverter.comparator.PatternComparator;
import codeconverter.comparator.ignored.IgnoredHolder;
import codeconverter.comparator.ignored.IgnoredPatterns;
import codeconverter.factories.ComparatorFactory;
import codeconverter.factories.LanguagesObjectsFactory;
import codeconverter.utility.FileStringUtility;

public class ComparisonDelegate {

	private ComparatorFactory cf;
	private LanguagesObjectsFactory lof;




	public ComparisonDelegate(ComparatorFactory cf, LanguagesObjectsFactory lof) {
		super();
		this.cf = cf;
		this.lof = lof;
	}


	public DifferentiationResult compareFiles(String firstTest, String secondTest,InputStream firstStream, InputStream secondStream){

		boolean leftToRight=true;
		String ext1=FileStringUtility.getFileExtension(firstTest);
		String ext2=FileStringUtility.getFileExtension(secondTest);

		ComparatorsHolder ch=cf.getComparators(ext1, ext2);
		PatternComparator pc=ch.getPc();

		if(ch.isInOrder(ext1, ext2)==false){
			leftToRight=false;
		}

		if(cf==null){
			return null;
		}

		List<CodePatternComparator> comparators=pc.getComparators();

		BlockDataInterpreter int1=lof.getBlockDataInterpreter(ext1);
		BlockDataInterpreter int2=lof.getBlockDataInterpreter(ext2);

		if(int1==null || int2==null){
			return null;
		}

		Block rootBlock1=GeneralTests.getBlocks(firstStream, ext1);
		Block rootBlock2=GeneralTests.getBlocks(secondStream, ext2);

		HashMap<CodeModule, CodePattern> pattMap1=GeneralTests.generateInterpretation(int1, rootBlock1);
		HashMap<CodeModule, CodePattern> pattMap2=GeneralTests.generateInterpretation(int2, rootBlock2);

		List<CodeModule> firstNotInterpreted=new ArrayList<CodeModule>();

		for (CodeModule codeModule : new ArrayList<CodeModule>(pattMap1.keySet())) {
			if(pattMap1.get(codeModule)==null){
				firstNotInterpreted.add(codeModule);
				pattMap1.remove(codeModule);
			}
		}

		List<CodeModule> secondNotInterpreted=new ArrayList<CodeModule>();

		for (CodeModule codeModule : new ArrayList<CodeModule>(pattMap2.keySet())) {
			if(pattMap2.get(codeModule)==null){
				secondNotInterpreted.add(codeModule);
				pattMap2.remove(codeModule);
			}
		}

		HashMap<CodeModule, CodeModule> matchedModules=new HashMap<CodeModule, CodeModule>();
		if(leftToRight){
			matchedModules=GeneralTests.findComparableLinesOfCode(comparators, pattMap1, pattMap2);
		} else {
			matchedModules=GeneralTests.findComparableLinesOfCode(comparators, pattMap2, pattMap1);
		}

		Set<CodeModule> firstModules=pattMap1.keySet();
		Set<CodeModule> secondModules=pattMap2.keySet();

		List<CodeModule> ignoredModules1=new ArrayList<CodeModule>();
		List<CodeModule> ignoredModules2=new ArrayList<CodeModule>();

		IgnoredHolder ih=cf.getIgnoreds(ext1, ext2);
		IgnoredPatterns ip1,ip2;
		System.out.println(ext1+"    "+ext2);
		if(ih.isInOrder(ext1, ext2)){
			ip1=ih.getFirstIgnored();
			ip2=ih.getSecondIgnored();
		} else {
			ip1=ih.getSecondIgnored();
			ip2=ih.getFirstIgnored();
		}

		for (CodeModule codeModule : firstModules) {
			CodePattern pattern=pattMap1.get(codeModule);
			if(ip1.ignoredContainsAny(pattern.getPatternType())){
				ignoredModules1.add(codeModule);
			}
		}
		firstModules.removeAll(ignoredModules1);

		for (CodeModule codeModule : secondModules) {
			CodePattern pattern=pattMap2.get(codeModule);
			if(ip2.ignoredContainsAny(pattern.getPatternType())){
				ignoredModules2.add(codeModule);
			}
		}
		secondModules.removeAll(ignoredModules2);

		return new DifferentiationResult(firstNotInterpreted, secondNotInterpreted, firstModules, secondModules);

	}


}
