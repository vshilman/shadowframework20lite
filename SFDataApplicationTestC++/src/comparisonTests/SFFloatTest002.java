package comparisonTests;


import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFFloatTest002 extends TestCase{

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"SFFloatTest002.sf";
		
		NativeLib2 nativeLib = new NativeLib2();
		float[] result = nativeLib.getData(fileName);
		
		assertEquals(10.5f, result[0]);
		assertEquals(-78.8f, result[1]);
		assertEquals(1.5f, result[2]);
		assertEquals(-1.5f, result[3]);
		assertEquals(1.5f, result[4]);
		assertEquals(-1.5f, result[5]);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (C++): "+duration+"ms");
		
	}

}

class NativeLib2
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native float[] getData(String fileName);
}