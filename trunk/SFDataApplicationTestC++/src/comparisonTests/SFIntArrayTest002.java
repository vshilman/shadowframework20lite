package comparisonTests;


import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFIntArrayTest002 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"SFIntArrayTest002.sf";
		
		NativeLib3 nativeLib = new NativeLib3();
		int[] result = nativeLib.getData(fileName);
		
		assertEquals(0, result[0]);
		assertEquals(1, result[1]);
		assertEquals(2, result[2]);
		assertEquals(3, result[3]);  
		assertEquals(4, result[4]);
		
		assertEquals(11, result[5]);
		assertEquals(128, result[6]);
		assertEquals(10170, result[7]);
		
		assertEquals(0, result[8]);
		assertEquals(1, result[9]);
		assertEquals(2, result[10]);
		assertEquals(3, result[11]);  
		assertEquals(4, result[12]);
		
		assertEquals(11, result[13]);
		assertEquals(128, result[14]);
		assertEquals(10170, result[15]);
	
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (C++): "+duration+"ms");
	}

}

class NativeLib3
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native int[] getData(String fileName);
}