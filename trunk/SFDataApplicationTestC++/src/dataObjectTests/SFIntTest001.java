package dataObjectTests;

import org.junit.Test;

import junit.framework.TestCase;



public class SFIntTest001 extends TestCase{
	
	@Test
	public void testA() {
		
		NativeLib nativeLib = new NativeLib();
		
		assertEquals(23, nativeLib.getData()[0]);
		assertEquals(-8, nativeLib.getData()[1]);
		assertEquals(23, nativeLib.getData()[2]);
		
	}
}

class NativeLib
{   
	   {
	      System.loadLibrary("IntLib"); 
	   }
	   
	public native int[] getData();
}