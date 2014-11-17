package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFShortArrayTest001 extends TestCase{

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	
	@Test
	public void testA() {
		
		String fileName = DIRECTORY+"/"+"SFShortArrayTest001.sf";
		NativeLib5 nativeLib = new NativeLib5();
		short[] result = nativeLib.getData(fileName);
		short[] result2 = nativeLib.getData2();
		
		assertEquals(-158, result[0]);
		assertEquals(-158, result[1]);
		assertEquals(-158, result[2]);
		assertEquals(-158, result[3]);
		assertEquals(-158, result[4]);
		//assertEquals(0, result[5]);  //21
		//assertEquals(0, result[6]); //-78
		
		assertEquals(0, result2[0]);
		assertEquals(1, result2[1]);
		assertEquals(2, result2[2]);
		assertEquals(3, result2[3]);
		assertEquals(4, result2[4]);
		assertEquals(6, result2[5]);
		
	}

}

class NativeLib5
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native short[] getData(String fileName);
	   public native short[] getData2();
}
