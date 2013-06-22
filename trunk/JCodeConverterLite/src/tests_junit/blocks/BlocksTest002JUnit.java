package tests_junit.blocks;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import tests.blocks.BlockUtilities;

import codeconverter.Block;
import codeconverter.BlockInterpreter;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.utility.FileStringUtility;

public class BlocksTest002JUnit {

	@Test
	public void test() {
		List<String> list = FileStringUtility
				.loadTextfromStream(FileStringUtility.getStream("src/testPackage/House.java"));
		StringWriter writer = new StringWriter();
		for (String string : list) {
			writer.write(string);
		}

		String totalString = writer.toString();
		System.out.println(totalString);
		char[] totalStringChars = totalString.toCharArray();

		Block fileBlock = BlockUtilities.generateBlocks(totalStringChars);

		String s = fileBlock.toString() + "\n";

		BlockInterpreter interpreter = new BlockInterpreter(
				new JavaCodePatternInterpreter());
		HashMap<CodeModule, CodePattern> interpretation = interpreter
				.getInterpretation(fileBlock);

		Set<CodeModule> keys = interpretation.keySet();
		for (CodeModule codeModule : keys) {
			CodePattern pattern = interpretation.get(codeModule);
			if (pattern != null)
				s += "[" + codeModule + "]:" + pattern + "\n";
			else
				s += "[" + codeModule + "]: unidentified" + "\n";
		}
		//System.out.println(s);
		assertEquals(
				"Block\n[sentence:package testPackage]:package  testPackage  \n[BLOCK [/** * an house class * @author Alessandro */public class House]]: unidentified\n[sentence:private int roofHeight]:private int roofHeight   \n[sentence:private int baseWidth]:private int baseWidth   \n[sentence:private int baseHeight]:private int baseHeight   \n[sentence:private int[] houseData]:private int[] houseData   \n[BLOCK [public House(int roofHeight, int baseWidth, int baseHeight)]]:public House   ( int roofHeight  , int baseWidth  , int baseHeight   )\n[sentence:super()]:super (  )\n[sentence:this.roofHeight = roofHeight]:this. roofHeight   =  roofHeight  \n[sentence:this.baseWidth = baseWidth]:this. baseWidth   =  baseWidth  \n[sentence:this.baseHeight = baseHeight]:this. baseHeight   =  baseHeight  \n[sentence:int[] houseData ={1,0,2,1,2}]:int[] houseData   = { 1,0,2,1,2 }\n[sentence:this.houseData = houseData]:this. houseData   =  houseData  \n[BLOCK [public int getRoofHeight()]]: public int getRoofHeight   (  )\n[sentence:return roofHeight]:return   roofHeight  \n[BLOCK [public void setRoofHeight(int roofHeight)]]: public void setRoofHeight   ( int roofHeight   )\n[sentence:this.roofHeight = roofHeight]:this. roofHeight   =  roofHeight  \n[BLOCK [public int getBaseWidth()]]: public int getBaseWidth   (  )\n[sentence:return baseWidth]:return   baseWidth  \n[BLOCK [public void setBaseWidth(int baseWidth)]]: public void setBaseWidth   ( int baseWidth   )\n[sentence:this.baseWidth = baseWidth]:this. baseWidth   =  baseWidth  \n[BLOCK [public int getBaseHeight()]]: public int getBaseHeight   (  )\n[sentence:return baseHeight]:return   baseHeight  \n[BLOCK [public void setBaseHeight(int baseHeight)]]: public void setBaseHeight   ( int baseHeight   )\n[sentence:this.baseHeight = baseHeight]:this. baseHeight   =  baseHeight  \n[BLOCK [public int[] getHouseData()]]: public int[] getHouseData   (  )\n[sentence:return houseData]:return   houseData  \n",s);
	}

}
