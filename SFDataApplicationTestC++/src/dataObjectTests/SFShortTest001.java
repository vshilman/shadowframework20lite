package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFShortTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"SFShortTest001.sf";
		
		NativeLib1 nativeLib = new NativeLib1();
		short[] result = nativeLib.getData(fileName);
		
		assertEquals(12, result[0]);
		assertEquals(111, result[1]);
		assertEquals(12, result[2]);
		assertEquals((short)1111.111, result[3]);
		assertEquals((short)-88.88, result[4]);
		//System.out.println(result[3]);
		//System.out.println(result[4]);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}


class NativeLib1
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native short[] getData(String fileName);
}
