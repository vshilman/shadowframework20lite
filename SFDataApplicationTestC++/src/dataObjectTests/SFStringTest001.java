package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;

public class SFStringTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		
		
		
	}

}

class NativeLib4
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	public native String getData(String fileName);
}