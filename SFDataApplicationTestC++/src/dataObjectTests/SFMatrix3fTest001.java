package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFMatrix3fTest001 extends TestCase {

	@Test
	public void testA() {
		
		NativeLib10 nativeLib = new NativeLib10();
		float[] result = nativeLib.getData();
		float[] result2 = nativeLib.getData2();
		
		assertEquals(9, result[0], 0);
		assertEquals(1, result[1], 0);
		assertEquals(1, result[2], 0);
		assertEquals(1, result[3], 0);
		assertEquals(0, result[4], 0);
		assertEquals(0, result[5], 0);
		assertEquals(0, result[6], 0);
		assertEquals(14.4f, result[7], 0);
		assertEquals(-14.4f, result[8], 0);
		assertEquals(14.4f, result[9], 0);
		
		assertEquals(1, result2[0], 0);
		assertEquals(1, result2[1], 0);
		assertEquals(1, result2[2], 0);
		assertEquals(10, result2[3], 0);
		assertEquals(10.5f, result2[4], 0);
		assertEquals(-100.69f, result2[5], 0);
		assertEquals(-24, result2[6], 0);
		assertEquals(18, result2[7], 0);
		assertEquals(5, result2[8], 0);
		assertEquals(20, result2[9], 0);
		assertEquals(-15, result2[10], 0);
		assertEquals(-4, result2[11], 0);
		assertEquals(-5, result2[12], 0);
		assertEquals(4, result2[13], 0);
		assertEquals(1, result2[14], 0);
		assertEquals(-1, result2[15], 0);
		//assertEquals(-1, result2[16], 0);
		//assertEquals(-1, result2[17], 0);   //valori strani
		
	}

}

class NativeLib10
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	   public native float[] getData2();
	  
}