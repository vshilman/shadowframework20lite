package comparisonTests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFIntArray;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFIntArrayTest002JAVA extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		//int[] expectedArray1 = {0,1,2,3,4};
		int[] expectedArray2 = {11,128,10170};
		
		SFIntArray intArray1 = new SFIntArray(5);
		SFIntArray intArray2 = new SFIntArray(3);
		
		for (int var = 0; var < 5; ++var) {

			intArray1.getIntValues()[var] = var;
		}
		
		intArray2.getIntValues()[0] = 11;
		intArray2.getIntValues()[1] = 128;
		intArray2.getIntValues()[2] = 10170;
		
		assertEquals(0, intArray1.getIntValues()[0]);
		assertEquals(1, intArray1.getIntValues()[1]);
		assertEquals(2, intArray1.getIntValues()[2]);
		assertEquals(3, intArray1.getIntValues()[3]);
		assertEquals(4, intArray1.getIntValues()[4]);
		
		assertEquals(expectedArray2[0], intArray2.getIntValues()[0]);
		assertEquals(expectedArray2[1], intArray2.getIntValues()[1]);
		assertEquals(expectedArray2[2], intArray2.getIntValues()[2]);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFIntArrayTest002.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			intArray1.writeOnStream(outputStream);
			intArray2.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		intArray2.getIntValues()[0] = -1;
		intArray2.getIntValues()[1] = -1;
		intArray2.getIntValues()[2] = -1;
	
	try {
		FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFIntArrayTest002.sf");
		SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
		
		intArray1.readFromStream(inputStream);
		intArray2.readFromStream(inputStream);
		
		
		input.close();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	assertEquals(0, intArray1.getIntValues()[0]);
	assertEquals(1, intArray1.getIntValues()[1]);
	assertEquals(2, intArray1.getIntValues()[2]);
	assertEquals(3, intArray1.getIntValues()[3]);
	assertEquals(4, intArray1.getIntValues()[4]);
	
	assertEquals(expectedArray2[0], intArray2.getIntValues()[0]);
	assertEquals(expectedArray2[1], intArray2.getIntValues()[1]);
	assertEquals(expectedArray2[2], intArray2.getIntValues()[2]);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (JAVA): "+duration+"ms");
	
	}

}
