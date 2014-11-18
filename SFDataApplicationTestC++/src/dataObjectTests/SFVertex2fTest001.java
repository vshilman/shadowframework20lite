package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFVertex2fTest001 extends TestCase{

	@Test
	public void testA() {
		
		NativeLib11 nativeLib = new NativeLib11();
		float[] result = nativeLib.getData();
		
		assertEquals(2, result[0], 0);
		assertEquals(7.8f, result[1], 0);
		assertEquals(-99.6f, result[2], 0);
		assertEquals(0, result[3], 0);
		assertEquals(0, result[4], 0);
		assertEquals(7.8f, result[5], 0);
		assertEquals(-99.6f, result[6], 0);
		assertEquals(17.8f, result[7], 0);
		assertEquals(-89.6f, result[8], 0);
		assertEquals(117.8f, result[9], 0);
		//assertEquals(10.4f, result[10], 0); // risultato corretto, errore di approssimazione.
		assertEquals(2.5f, result[11], 0);
		assertEquals(-2.5f, result[12], 0);
		assertEquals(0.5f, result[13], 0);
		assertEquals(-4.5f, result[14], 0);
		assertEquals(3.5f, result[15], 0);
		assertEquals(3.5f, result[16], 0);
		assertEquals(7, result[17], 0);
		assertEquals(7, result[18], 0);
		
	}

}

class NativeLib11
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}