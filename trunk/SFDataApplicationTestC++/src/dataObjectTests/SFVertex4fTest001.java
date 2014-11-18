package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

public class SFVertex4fTest001 extends TestCase{

	@Test
	public void testA() {
		
		NativeLib13 nativeLib = new NativeLib13();
		float[] result = nativeLib.getData();
		
		assertEquals(4, result[0], 0);
		assertEquals(7.8f, result[1], 0);
		assertEquals(-99, result[2], 0);
		assertEquals(8, result[3], 0);
		assertEquals(8, result[4], 0);
		assertEquals(0, result[5], 0);
		assertEquals(0, result[6], 0);
		assertEquals(0, result[7], 0);
		assertEquals(0, result[8], 0);
		assertEquals(7.8f, result[9], 0);
		assertEquals(-99, result[10], 0);
		assertEquals(8, result[11], 0);
		assertEquals(8, result[12], 0);
		assertEquals(17.8f, result[13], 0);
		assertEquals(-89, result[14], 0);  
		assertEquals(18, result[15], 0);
		assertEquals(18, result[16], 0);
		assertEquals(117.8f, result[17], 0);
		assertEquals(11, result[18], 0);
		assertEquals(118, result[19], 0);
		assertEquals(118, result[20], 0);
		assertEquals(2.5f, result[21], 0);
		assertEquals(-2.5f, result[22], 0);
		assertEquals(2.5f, result[23], 0);
		assertEquals(2.5f, result[24], 0);
		assertEquals(0.5, result[25], 0);
		assertEquals(-4.5, result[26], 0);
		assertEquals(0.5, result[27], 0);
		assertEquals(0.5, result[28], 0);
		assertEquals(3.5f, result[29], 0);
		assertEquals(3.5f, result[30], 0);
		assertEquals(3.5f, result[31], 0);
		assertEquals(3.5f, result[32], 0);
		assertEquals(7, result[33], 0);
		assertEquals(7, result[34], 0);
		assertEquals(7, result[35], 0);
		assertEquals(7, result[36], 0);
		
	}

}

class NativeLib13
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}