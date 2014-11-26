package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFVertex3fTest001 extends TestCase{

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		NativeLib12 nativeLib = new NativeLib12();
		float[] result = nativeLib.getData();
		
		assertEquals(3, result[0], 0);
		assertEquals(7.8f, result[1], 0);
		assertEquals(-99, result[2], 0);
		assertEquals(8, result[3], 0);
		assertEquals(0, result[4], 0);
		assertEquals(0, result[5], 0);
		assertEquals(0, result[6], 0);
		assertEquals(7.8f, result[7], 0);
		assertEquals(-99, result[8], 0);
		assertEquals(8, result[9], 0);
		assertEquals(17.8f, result[10], 0);
		assertEquals(-89, result[11], 0);
		assertEquals(18, result[12], 0);
		assertEquals(117.8f, result[13], 0);
		assertEquals(11, result[14], 0);  
		assertEquals(118, result[15], 0);
		assertEquals(2.5f, result[16], 0);
		assertEquals(-2.5f, result[17], 0);
		assertEquals(2.5f, result[18], 0);
		assertEquals(0.5f, result[19], 0);
		assertEquals(-4.5, result[20], 0);
		assertEquals(0.5f, result[21], 0);
		assertEquals(3.5f, result[22], 0);
		assertEquals(3.5f, result[23], 0);
		assertEquals(3.5f, result[24], 0);
		assertEquals(7, result[25], 0);
		assertEquals(7, result[26], 0);
		assertEquals(7, result[27], 0);
	
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}

class NativeLib12
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}