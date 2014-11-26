package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFFloatTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"SFFloatTest001.sf";
		
		NativeLib2 nativeLib = new NativeLib2();
		float[] result = nativeLib.getData(fileName);
		
		assertEquals(-11.8f, result[0]);
		assertEquals(88.8f, result[1]);
		assertEquals(0.3f, result[2]);
		assertEquals(44.2112f, result[3]);
		assertEquals(0.001225f, result[4]);
		System.out.println(result[3]);
		System.out.println(result[4]);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}

class NativeLib2
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native float[] getData(String fileName);
}