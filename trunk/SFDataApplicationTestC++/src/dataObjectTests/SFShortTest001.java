package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

public class SFShortTest001 extends TestCase{

	@Test
	public void testA() {
		
		NativeLib1 nativeLib = new NativeLib1();
		
		assertEquals(12, nativeLib.getData()[0]);
		assertEquals(111, nativeLib.getData()[1]);
		assertEquals(12, nativeLib.getData()[2]);
	}

}


class NativeLib1
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native short[] getData();
}
