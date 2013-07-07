package tests;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Set;

import codeconverter.Block;
import codeconverter.CodeModule;
import codeconverter.CodePattern;

public class TestingUtilities {

	//public static String generateFileString(InputStream stream) 
	//HAS BEEN MOVE TO BlockUtilities, because it was used only there 


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
			int count=0;
			Set<CodeModule> keys=interpretation.keySet();
			for (CodeModule codeModule : keys) {
				CodePattern pattern=interpretation.get(codeModule);
				if(pattern == null){
					count++;
					stream.write("["+codeModule+"] as been translated to ["+pattern+"]\n");
				}
			}
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
