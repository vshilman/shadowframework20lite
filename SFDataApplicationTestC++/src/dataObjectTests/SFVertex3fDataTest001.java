package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFVertex3fDataTest001 extends TestCase{

	@Test
	public void testA() {
		
		NativeLib17 nativeLib = new NativeLib17();
		float[] result = nativeLib.getData();
		
		assertEquals(0, result[0], 0);
		assertEquals(0, result[1], 0);
		assertEquals(0, result[2], 0);
		assertEquals(1.5f, result[3], 0);
		assertEquals(-1.5f, result[4], 0);
		assertEquals(1.5f, result[5], 0);
		assertEquals(0, result[6], 0);
		
		
	}

}

class NativeLib17
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}