package comparisonTests;


import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class StressingCTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		String fileName = DIRECTORY+"/"+"StressingTest001.sf";
		
		NativeLib40 nativeLib = new NativeLib40();
		float result = nativeLib.getData(fileName);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (C++): "+duration+"ms");
		
		assertEquals(-33.5f, result);
		
	}

}

class NativeLib40
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native float getData(String fileName);
}