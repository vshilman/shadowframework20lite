package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFFloatArrayTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		String fileName = DIRECTORY+"/"+"SFFloatArrayTest001.sf";
		
		NativeLib6 nativeLib = new NativeLib6();
		float[] result = nativeLib.getData(fileName);
		float[] result2 = nativeLib.getData2();
		
		assertEquals(15.8f, result[0]);
		assertEquals(33.3f, result[1]);
		assertEquals(-1.5f, result[2]);
		assertEquals(877.88f, result[3]);
		assertEquals(-78.37f, result[4]);
		
		
		assertEquals(0.1f, result2[0]);
		assertEquals(0.1f, result2[1]);
		assertEquals(0.1f, result2[2]);
		assertEquals(33.33f, result2[3]);
		assertEquals(-0.1f, result2[4]);
		
	}

}

class NativeLib6
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native float[] getData(String fileName);
	   public native float[] getData2();
}