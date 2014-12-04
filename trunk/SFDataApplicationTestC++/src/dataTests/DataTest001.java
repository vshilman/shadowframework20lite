package dataTests;


import junit.framework.TestCase;

import org.junit.Test;

public class DataTest001 extends TestCase{

	@Test
	public void testA() {
		
	}

}


class NativeLib1
{   
	   {
	      System.loadLibrary("Library"); 
	   }
	   
	   public native int[] getData();
	  
}