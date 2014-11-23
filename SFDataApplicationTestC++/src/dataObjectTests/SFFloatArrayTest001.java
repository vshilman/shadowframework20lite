package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFFloatArrayTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		String fileName = DIRECTORY+"/"+"SFFloatTest001.sf";
		
		NativeLib6 nativeLib = new NativeLib6();
		float[] result = nativeLib.getData(fileName);
		float[] result2 = nativeLib.getData2();
		
		assertEquals(44.2112f, result[0]);
		assertEquals(0.001225f, result[1]);
		
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