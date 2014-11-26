package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFIntByteFieldTest001 extends TestCase {

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		NativeLib7 nativeLib = new NativeLib7();
		int[] result = nativeLib.getData();
		
		assertEquals(13, result[0]);
		assertEquals(1, result[1]);
		assertEquals(4, result[2]);
		assertEquals(0, result[3]);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}

class NativeLib7
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native int[] getData();
	  
}