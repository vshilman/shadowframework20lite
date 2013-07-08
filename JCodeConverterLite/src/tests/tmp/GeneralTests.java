package tests.tmp;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tests.TestingUtilities;
import tests.blocks.BlockUtilities;
import codeconverter.Block;
import codeconverter.BlockDataInterpreter;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.DifferentiationResult;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.java.JavaConstructorDeclaration;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.JavaJsCodePatternComparators;
import codeconverter.javatojs.JSCodeTranslator;
import codeconverter.js.JsCodePatternInterpreter;


/**
 * A useful class with general purpose functionalities for Java To JS comparisons
 *
 * @author Alessandro Martinelli
 */
public class GeneralTests {

	private static int notGeneratedInterpretationJava=0;

	private static int notGeneratedInterpretationJs=0;

	private static int totalJavaLineOfCode=0;

	private static int totalJsLineOfCode=0;

	private static int totalJavaFiles=0;

	private static int totalJsFiles=0;

	private static int totalModulesWhichCouldntBeAnalized =0;

	private static int totalIgnoredJavaLines =0;

	private static int totalUnmatchedJavaLines =0;

	private static int totalUnmatchedJsLines =0;

	public static int getTotalJavaLineOfCode() {
		return totalJavaLineOfCode;
	}

	public static void initializeTests() {
		GeneralTests.totalJavaLineOfCode = 0;
		GeneralTests.totalJsLineOfCode = 0;
		GeneralTests.notGeneratedInterpretationJs = 0;
		GeneralTests.notGeneratedInterpretationJava = 0;
		GeneralTests.totalJavaFiles = 0;
		GeneralTests.totalJsFiles = 0;
		GeneralTests.totalModulesWhichCouldntBeAnalized = 0;
	}



	public static int getTotalUnmatchedJsLines() {
		return totalUnmatchedJsLines;
	}

	public static int getTotalIgnoredJavaLines() {
		return totalIgnoredJavaLines;
	}

	public static int getTotalUnmatchedJavaLines() {
		return totalUnmatchedJavaLines;
	}

	public static int getTotalJsLineOfCode() {
		return totalJsLineOfCode;
	}


	public static int getNotGeneratedInterpretationJs() {
		return notGeneratedInterpretationJs;
	}

	public static int getNotGeneratedInterpretationJava() {
		return notGeneratedInterpretationJava;
	}


	public static int getTotalModulesWhichCouldntBeAnalized() {
		return totalModulesWhichCouldntBeAnalized;
	}

	public static int getTotalJavaFiles() {
		return totalJavaFiles;
	}

	public static int getTotalJsFiles() {
		return totalJsFiles;
	}

	public static void matchBlocksTest(String fileName,InputStream str, PrintStream stream,BlockDataInterpreter dataInterpreter) {
		HashMap<CodeModule, CodePattern> interpretation = generateInterpretation(dataInterpreter,getBlocks(str));

		stream.println("\n\nInterpreted Modules from "+fileName+"\n\n");

		Set<CodeModule> interpretedModules=interpretation.keySet();
		for (Iterator<CodeModule> iterator = interpretedModules.iterator(); iterator.hasNext();) {
			CodeModule codeModule = iterator.next();

			CodePattern pattern=interpretation.get(codeModule);

			stream.println("[\t"+codeModule.toString()+"\t\t\t\t  has been interpreted as \t[\t"+pattern.getPatternType()+",\t"+pattern.toString()+"\t]");
		}
		stream.println("\n\n");
	}

	public static HashMap<CodeModule, CodePattern> generateInterpretation(
			BlockDataInterpreter dataInterpreter,Block fileBlock) {

		BlockInterpreter interpreter=new BlockInterpreter(dataInterpreter);
		HashMap<CodeModule, CodePattern> interpretation=interpreter.getInterpretation(fileBlock);
		return interpretation;
	}




	public static Block getBlocks(InputStream stream) {
		return BlockUtilities.generateBlocksFromStream(stream);
	}





	public static HashMap<CodeModule, CodeModule> findComparableLinesOfCode(List<CodePatternComparator> comparators,
			HashMap<CodeModule, CodePattern> javaPatternsMap,
			HashMap<CodeModule, CodePattern> jsPatternsMap) {
		Set<CodeModule> javaModules=javaPatternsMap.keySet();
		Set<CodeModule> jsModules=jsPatternsMap.keySet();

		ArrayList<CodePattern> javaPatterns = new ArrayList<CodePattern>(javaPatternsMap.values());
		ArrayList<CodePattern> jsPatterns = new ArrayList<CodePattern>(jsPatternsMap.values());

		HashMap<CodeModule, CodeModule> matchedPatterns=new HashMap<CodeModule, CodeModule>();

		Iterator<CodeModule> j=javaModules.iterator();
		while (j.hasNext()) {
			CodeModule javaModule=j.next();
			boolean found = false;
			Iterator<CodeModule> k=jsModules.iterator();
			while(!found && k.hasNext()){
					CodeModule jsModule=k.next();
					for (CodePatternComparator codePatternComparator : comparators) {
							//Actually, not the best code all around...
							int javaPatternsIndex=javaPatterns.indexOf(javaPatternsMap.get(javaModule));
							int jsPatternsIndex=jsPatterns.indexOf(jsPatternsMap.get(jsModule));

						boolean result = codePatternComparator
								.compare(javaPatterns.get(javaPatternsIndex) , jsPatterns.get(jsPatternsIndex));
						if (result) {

							matchedPatterns.put(javaModule, jsModule);
							j.remove();
							k.remove();
							found = true;
							break;
						}
					}
			}
		}

		return matchedPatterns;
	}


	//id leftToRight==true js is on the left and java on the right (temporarily inserted waiting for an abstract comparison)

	public static DifferentiationResult compareFiles(String jsTest, String javaTest,InputStream jsStream, InputStream javaStream, StringWriter logWriter,boolean leftToRight) {


		try {
			List<CodePatternComparator> comparators = JavaJsCodePatternComparators.getComparators();
			comparators.add(new PrototypedMethodDeclarationComparator());

			JsCodePatternInterpreter jsCodePatterInterpreter=new JsCodePatternInterpreter();
			jsCodePatterInterpreter.getPatterns().add(0,new JsPrototypedMethodDeclaration());

			Block javaRootBlock=getBlocks(javaStream);
			HashMap<CodeModule, CodePattern> javaPatternsMap=generateInterpretation(new JavaCodePatternInterpreter(),javaRootBlock);
			HashMap<CodeModule, CodePattern> jsPatternsMap=generateInterpretation(jsCodePatterInterpreter,getBlocks(jsStream));

			totalJavaFiles++;
			totalJsFiles++;
			totalJavaLineOfCode+=javaPatternsMap.size();
			totalJsLineOfCode+=jsPatternsMap.size();

			List<CodeModule> javaNotInterpreted=new ArrayList<CodeModule>();

			logWriter.write("\n\n\tSome Java Code lines could not be interpreted\n");
			for (CodeModule codeModule : new ArrayList<CodeModule>(javaPatternsMap.keySet())) {
				if(javaPatternsMap.get(codeModule)==null){
					logWriter.write("\tCannot generate An interpretation for "+codeModule+"\n");
					javaNotInterpreted.add(codeModule);
					javaPatternsMap.remove(codeModule);
					notGeneratedInterpretationJava++;
				}
			}

			List<CodeModule> jsNotInterpreted=new ArrayList<CodeModule>();

			logWriter.write("\n\n\tSome JS Code lines could not be interpreted\n");
			for (CodeModule codeModule : new ArrayList<CodeModule>(jsPatternsMap.keySet())) {
				if(jsPatternsMap.get(codeModule)==null){
					logWriter.write("\t\tCannot generate An interpretation for "+codeModule+"\n");
					jsNotInterpreted.add(codeModule);
					jsPatternsMap.remove(codeModule);
					notGeneratedInterpretationJs++;
				}
			}

			logWriter.write("\n\n\tList of all matched lines of code\n");
			HashMap<CodeModule, CodeModule> matchedModules=findComparableLinesOfCode(comparators, javaPatternsMap, jsPatternsMap);

			for (CodeModule javacodeModule : matchedModules.keySet()) {
				CodeModule jscodeModule=matchedModules.get(javacodeModule);
				logWriter.write("\t\t"+javacodeModule.toString()+" \n\t\t\t  was matched to  \n\t\t\t\t"+jscodeModule.toString()+" \n");
			}

			Set<CodeModule> javaModules=javaPatternsMap.keySet();
			Set<CodeModule> jsModules=jsPatternsMap.keySet();

			logWriter.write("\n\n\tIgnored javaPatterns:\n");
			List<CodeModule> ignoredModules=new ArrayList<CodeModule>();
			for (CodeModule codeModule : javaModules) {
				CodePattern pattern=javaPatternsMap.get(codeModule);
				if(JavaToJsIgnoredPatterns.javaIgnoredContainsAny(pattern.getPatternType())){
					logWriter.write("\t\t"+pattern + "\n");
					ignoredModules.add(codeModule);
					totalIgnoredJavaLines++;
				}
			}
			javaModules.removeAll(ignoredModules);

			logWriter.write("\n\n\tunmatched javaPatterns:\n");
			for (CodeModule codeModule : javaModules) {
				logWriter.write("\t\t +"+codeModule + "\n");
				totalUnmatchedJavaLines++;
			}

			logWriter.write("\n\n\tunmatched jsPatterns:\n");

			for (CodeModule codeModule : jsModules) {
				logWriter.write("\t\t" + codeModule + "\n");
				totalUnmatchedJsLines++;
			}

			//return translate(javaRootBlock, javaPatternsMap);

			if(leftToRight){
				return new DifferentiationResult(jsNotInterpreted, javaNotInterpreted, jsModules, javaModules);
			} else {
				return new DifferentiationResult(javaNotInterpreted, jsNotInterpreted, javaModules, jsModules);
			}

		} catch (ClassCastException e) {
			String errorMessage="Comparison between \n "+javaTest+" and \n"+jsTest+"\n could not end correctly because the following exception was thrown: ";
			logWriter.write(errorMessage+e.getMessage());
			System.err.println(errorMessage);
			e.printStackTrace();
			GeneralTests.totalModulesWhichCouldntBeAnalized ++;
		} catch (Exception e) {
			String errorMessage="Comparison between \n "+javaTest+" and \n"+jsTest+"\n could not end correctly because the following exception was thrown: ";
			logWriter.write(errorMessage+e.getMessage());
			System.err.println(errorMessage);
			e.printStackTrace();
			GeneralTests.totalModulesWhichCouldntBeAnalized ++;
		}

		return null;
	}

	public static String translate(Block javaRootBlock,
			HashMap<CodeModule, CodePattern> javaPatternsMap) {
		JSCodeTranslator translator=new JSCodeTranslator();

		Set<CodeModule> keys=javaPatternsMap.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern=javaPatternsMap.get(codeModule);
			if(pattern!=null && (pattern instanceof JavaConstructorDeclaration))
				System.err.println("["+codeModule+"]:"+pattern);
		}

		return translator.translateCode(javaRootBlock, javaPatternsMap);
	}


	public static String newFile(String javaTest,InputStream javaStream, StringWriter logWriter) {
		try {
			List<CodePatternComparator> comparators = JavaJsCodePatternComparators.getComparators();
			comparators.add(new PrototypedMethodDeclarationComparator());

			Block javaRootBlock=getBlocks(javaStream);
			HashMap<CodeModule, CodePattern> javaPatternsMap=generateInterpretation(new JavaCodePatternInterpreter(),javaRootBlock);

			totalJavaFiles++;
			totalJavaLineOfCode+=javaPatternsMap.size();

			logWriter.write("\n\n\tSome Java Code lines could not be interpreted\n");
			for (CodeModule codeModule : new ArrayList<CodeModule>(javaPatternsMap.keySet())) {
				if(javaPatternsMap.get(codeModule)==null){
					logWriter.write("\t\tCannot generate An interpretation for "+codeModule+"\n");
					javaPatternsMap.remove(codeModule);
					notGeneratedInterpretationJava++;
				}
			}

			Set<CodeModule> javaModules=javaPatternsMap.keySet();

			logWriter.write("\n\n\tIgnored javaPatterns:\n");
			List<CodeModule> ignoredModules=new ArrayList<CodeModule>();
			for (CodeModule codeModule : javaModules) {
				CodePattern pattern=javaPatternsMap.get(codeModule);
				if(JavaToJsIgnoredPatterns.javaIgnoredContainsAny(pattern.getPatternType())){
					logWriter.write("\t\t "+pattern + "\n");
					ignoredModules.add(codeModule);
					totalIgnoredJavaLines++;
				}
			}
			javaModules.removeAll(ignoredModules);

			logWriter.write("\n\n\tunmatched javaPatterns:\n");
			for (CodeModule codeModule : javaModules) {
				logWriter.write("\t\t +"+codeModule + "\n");
				totalUnmatchedJavaLines++;
			}

			return translate(javaRootBlock, javaPatternsMap);

		} catch (ClassCastException e) {
			String errorMessage="Evaluation of new \n "+javaTest+" could not end correctly because the following exception was thrown: ";
			logWriter.write(errorMessage+e.getMessage());
			System.err.println(errorMessage);
			e.printStackTrace();

			GeneralTests.totalModulesWhichCouldntBeAnalized ++;
		} catch (Exception e) {
			String errorMessage="Evaluation of new \n "+javaTest+" could not end correctly because the following exception was thrown: ";
			logWriter.write(errorMessage+e.getMessage());
			System.err.println(errorMessage);
			e.printStackTrace();
			GeneralTests.totalModulesWhichCouldntBeAnalized ++;
		}

		return "";
	}
}
