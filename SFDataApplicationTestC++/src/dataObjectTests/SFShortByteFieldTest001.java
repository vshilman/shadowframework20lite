package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFShortByteFieldTest001 extends TestCase{

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		NativeLib9 nativeLib = new NativeLib9();
		int[] result = nativeLib.getData();
		
		assertEquals(4, result[0]);
		assertEquals(0, result[1]);
		assertEquals(120, result[2]);
		assertEquals(3, result[3]);
	
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}

class NativeLib9
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native int[] getData();
	  
}