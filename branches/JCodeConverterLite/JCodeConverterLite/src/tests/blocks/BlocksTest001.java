package tests.blocks;

import java.io.StringWriter;
import java.util.List;

import codeconverter.Block;
import codeconverter.utility.FileStringUtility;

public class BlocksTest001 {

	public static void main(String[] args) {
		List<String> list=FileStringUtility
				.loadTextFile("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java");
		// List<String>
		// list=FileStringUtility.loadTextFile("src/testPackage/ComplexClass.java");

		StringWriter writer=new StringWriter();
		for (String string : list) {
			writer.write(string);
		}

		String totalString=writer.toString();
		char[] totalStringChars=totalString.toCharArray();

		Block fileBlock=BlockUtilities.generateBlocks(totalStringChars);

		System.out.println(fileBlock.print());	
		
	}
}
