package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFVertex3fDataTest001 extends TestCase{

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		NativeLib17 nativeLib = new NativeLib17();
		float[] result = nativeLib.getData();
		
		assertEquals(0, result[0], 0);
		assertEquals(0, result[1], 0);
		assertEquals(0, result[2], 0);
		assertEquals(1.5f, result[3], 0);
		assertEquals(-1.5f, result[4], 0);
		assertEquals(1.5f, result[5], 0);
		assertEquals(0, result[6], 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}

class NativeLib17
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}