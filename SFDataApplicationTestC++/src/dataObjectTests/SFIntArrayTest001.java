package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFIntArrayTest001 extends TestCase {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void test() {
		
		String fileName = DIRECTORY+"/"+"SFIntTest001.sf";
		
		NativeLib3 nativeLib = new NativeLib3();
		int[] result = nativeLib.getData(fileName);
		int[] result2 = nativeLib.getData2();
		
		assertEquals(0, result[0]);
		assertEquals(1, result[1]);
		assertEquals(2, result[2]);
		assertEquals(3, result[3]);
		assertEquals(4, result[4]);
		
		assertEquals(0, result2[0]);
		assertEquals(1, result2[1]);
		assertEquals(2, result2[2]);
		assertEquals(3, result2[3]);
		assertEquals(4, result2[4]);
		
		
		
		
	}

}

class NativeLib3
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native int[] getData(String fileName);
	public native int[] getData2();
}
