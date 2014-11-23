package dataTests;


import junit.framework.TestCase;

import org.junit.Test;


public class SFNamedParametersObjectTest001 extends TestCase{

	@Test
	public void testA() {
		
		NativeLib1 nativeLib = new NativeLib1();
		float[] result = nativeLib.getData();
		
		assertEquals(0.0f, result[0]);
		
	}

}

class NativeLib1
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}