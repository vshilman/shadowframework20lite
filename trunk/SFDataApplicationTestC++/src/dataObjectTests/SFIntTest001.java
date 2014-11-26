package dataObjectTests;

import org.junit.Test;

import android.os.Environment;
import junit.framework.TestCase;

public class SFIntTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"SFIntTest001.sf";
		
		NativeLib nativeLib = new NativeLib();
		int[] result = nativeLib.getData(fileName);
		
		assertEquals(23, result[0]);
		assertEquals(-8, result[1]);
		assertEquals(23, result[2]);
		assertEquals(15, result[3]);
		assertEquals(1, result[4]);
		//System.out.println(result[3]);
		//System.out.println(result[4]);
		System.out.println();
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
		
	}
	
}

class NativeLib
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native int[] getData(String fileName);
}