package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFIntByteFieldTest001 extends TestCase {

	@Test
	public void testA() {
		
		NativeLib7 nativeLib = new NativeLib7();
		int[] result = nativeLib.getData();
		
		assertEquals(13, result[0]);
		assertEquals(1, result[1]);
		assertEquals(4, result[2]);
		assertEquals(0, result[3]);
		
	}

}

class NativeLib7
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native int[] getData();
	  
}