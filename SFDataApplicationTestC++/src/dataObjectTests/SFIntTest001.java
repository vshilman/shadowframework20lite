package dataObjectTests;

import org.junit.Test;

import junit.framework.TestCase;



public class SFIntTest001 extends TestCase{
	
	@Test
	public void testA() {
		
		NativeLib nativeLib = new NativeLib();
		System.out.println(nativeLib.getData());
		assertEquals(10, nativeLib.getData());
		
	}


}

class NativeLib
{   
	   {
	      System.loadLibrary("IntLib"); 
	   }
	   
	public native int getData();
}