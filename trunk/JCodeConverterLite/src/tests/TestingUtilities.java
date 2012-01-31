package tests;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import codeconverter.Block;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.utility.FileStringUtility;

public class TestingUtilities {

	/**
	 * Generate a String from a File Content.
	 * 
	 * Comments lines should get removed (but cross your finger :) )
	 * 
	 * @param filename
	 * @return
	 */
	public static String generateFileString(String filename) {
		List<String> list=FileStringUtility.loadTextFile(filename);
		//List<String> list=FileStringUtility.loadTextFile("src/testPackage/Expressions.java");
		//List<String> list=FileStringUtility.loadTextFile("src/testPackage/House.java");
	
		
		StringWriter writer=new StringWriter();
		for (String string : list) {
			int position=string.indexOf("//");
			if(position>=0)
				string=string.substring(0,position).trim();
			if(string.length()!=0)
				writer.write(string);
		}
		
		String totalString=writer.toString();
		int beginof=totalString.indexOf("/*");
		int endof=totalString.indexOf("*/");
		while(beginof!=-1 && endof!=-1){
			String tmp=totalString.substring(0,beginof);
			tmp+=totalString.substring(endof+2);
			totalString=tmp;
			beginof=totalString.indexOf("/*");
			endof=totalString.indexOf("*/");
			System.out.println();
		}
		return totalString;
	}

	public static void writeString(PrintStream stream,String data){
		stream.println("Begin Of Total String");
		stream.println(data);
		stream.println("End Of Total String");
	}
	
	public static void printBlock(PrintStream stream,Block block){
		stream.println("Begin Of Block");
		stream.println(block.print());
		stream.println("End Of Block");
	}

	public static void writeInterpretations(PrintStream stream,HashMap<CodeModule, CodePattern> interpretation){
		stream.println("Begin Of Interpretation");
		Set<CodeModule> keys=interpretation.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern=interpretation.get(codeModule);
			stream.println("["+codeModule+"] as been translated to ["+pattern+"]");
		}
		stream.println("End Of Interpretation");
	}
	
	public static int reportWrongInterpretation(Writer stream,HashMap<CodeModule, CodePattern> interpretation){
		try {
			stream.write("Begin Of Not Interpreted\n");
			int count=0;
			Set<CodeModule> keys=interpretation.keySet();
			for (CodeModule codeModule : keys) {
				CodePattern pattern=interpretation.get(codeModule);
				if(pattern == null){
					count++;
					stream.write("["+codeModule+"] as been translated to ["+pattern+"]\n");
				}	
			}
			stream.write("End Of Not Interpreted\n");
			return count;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int countWrongInterpretation(HashMap<CodeModule, CodePattern> interpretation){
		
		int count=0;
		Set<CodeModule> keys=interpretation.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern=interpretation.get(codeModule);
			if(pattern == null){
				count++;
			}	
		}
		return count;
	}
}
