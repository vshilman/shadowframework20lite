package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

public class SFBinaryArrayObjectTest001 extends TestCase{

	@Test
	public void testA() {
		
		NativeLib15 nativeLib = new NativeLib15();
		int[] result = nativeLib.getData();
		
		assertEquals(1, result[0]);
		assertEquals(44, result[1]);
		assertEquals(23, result[2]);
		assertEquals(-15, result[3]);
		assertEquals(100, result[4]);
	}

}

class NativeLib15
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native int[] getData();
	  
}