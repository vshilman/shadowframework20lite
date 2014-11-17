package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFIntArrayTest001 extends TestCase {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		String fileName = DIRECTORY+"/"+"SFIntArrayTest001.sf";
		
		NativeLib3 nativeLib = new NativeLib3();
		int[] result = nativeLib.getData(fileName);
		int[] result2 = nativeLib.getData2();
		//int[] result3 = nativeLib.getData3();  //vettore usato per il metodo che restituisce native crash
		
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
		
//		assertEquals(11, result3[0]);
//		assertEquals(128, result3[1]);
//		assertEquals(10170, result3[2]);
//		assertEquals(-69, result3[3]);
//		assertEquals(-78, result3[4]);

	}

}

class NativeLib3
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native int[] getData(String fileName);
	public native int[] getData2();
	public native int[] getData3();
}
