package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

public class SFBinaryFloatArrayObjectTest001 extends TestCase{

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		NativeLib14 nativeLib = new NativeLib14();
		float[] result = nativeLib.getData();
		
		assertEquals(1.5f, result[0], 0);
		assertEquals(-1.5f, result[1], 0);
		assertEquals(1.5f, result[2], 0);
		assertEquals(1.5f, result[3], 0);
		assertEquals(-1.5f, result[4], 0);
		assertEquals(1.5f, result[5], 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
}


class NativeLib14
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}