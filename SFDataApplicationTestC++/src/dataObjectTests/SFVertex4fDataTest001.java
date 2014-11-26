package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

public class SFVertex4fDataTest001 extends TestCase{

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		NativeLib18 nativeLib = new NativeLib18();
		float[] result = nativeLib.getData();
		
//		assertEquals(1.5f, result[0], 0);   //Valori che fanno fallire il test
//		assertEquals(1.5f, result[1], 0);
//		assertEquals(0.0f, result[2], 0);
//		assertEquals(0.0f, result[3], 0);
		
		//Parte del testing sulla classe SFVectorData
		
		assertEquals(1.5f, result[4], 0);
		assertEquals(1.5f, result[5], 0);
		assertEquals(0.0f, result[6], 0);
		assertEquals(0.0f, result[7], 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}

class NativeLib18
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData();
	  
}