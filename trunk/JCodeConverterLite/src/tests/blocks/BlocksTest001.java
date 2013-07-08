package tests.blocks;

import codeconverter.Block;
import codeconverter.utility.FileStringUtility;

/**
 * A simple test about the use of Blocks.
 * This test tries to generate blocks from one of SFModules.
 *
 * @author Alessandro
 */
public class BlocksTest001 {

	public static void main(String[] args) {
//		List<String> list=FileStringUtility.loadTextfromStream(FileStringUtility.getStream("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java"));
//
//		//TODO : here we can use TestingUtilities.generateFileString, doing the same thing
//		StringWriter writer=new StringWriter();
//		for (String string : list) {
//			writer.write(string);
//		}
//
//		String totalString=writer.toString();
//		char[] totalStringChars=totalString.toCharArray();

		Block fileBlock=BlockUtilities.generateBlocksFromStream(FileStringUtility.getStream("../ShadowFramework2.0/src/shadow/system/data/SFOutputStream.java"));

		System.out.println(fileBlock.print());

	}
}
