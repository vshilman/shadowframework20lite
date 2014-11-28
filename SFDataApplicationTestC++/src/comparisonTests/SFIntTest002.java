package comparisonTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFIntTest002 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"SFIntTest002.sf";
		
		NativeLib nativeLib = new NativeLib();
		int[] result = nativeLib.getData(fileName);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (C++): "+duration+"ms");
		
		assertEquals(6, result[0]);
		assertEquals(-15, result[1]);
		assertEquals(11, result[2]);
		assertEquals(11, result[3]);
		assertEquals(-17, result[4]);
		assertEquals(0, result[5]);
		assertEquals(11, result[6]);
		assertEquals(-17, result[7]);
		assertEquals(0, result[8]);
		
	}

}

class NativeLib
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native int[] getData(String fileName);
}