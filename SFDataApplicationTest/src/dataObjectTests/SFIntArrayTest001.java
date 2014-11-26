package dataObjectTests;

import static org.junit.Assert.*;

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

public class SFIntArrayTest001 extends TestCase{

	SFIntArray intArray1 = new SFIntArray(5);
	SFIntArray intArray2 = new SFIntArray(3);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		int[] expectedArray1 = new int[5];
		int[] expectedArray2 = new int[3];
		
		assertArrayEquals(expectedArray1, intArray1.getIntValues());
		assertArrayEquals(expectedArray2, intArray2.getIntValues());
		
		int[] expectedArray3 = new int[11];
		int[] expectedArray4 = new int[1];
		
		intArray1.setIntValues(expectedArray3);
		intArray2.setIntValues(expectedArray4);
		
		assertArrayEquals(expectedArray3, intArray1.getIntValues());
		assertArrayEquals(expectedArray4, intArray2.getIntValues());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
		
	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		int[] expectedArray1 = {0,1,55,3,4};
		
		for (int i = 0; i < intArray1.getIntValues().length; i++) {
			
			intArray1.getIntValues()[i] = i;
			
			//System.out.println(intArray1.getIntValues()[i]);
			
		}
			
			intArray1.getIntValues()[2] = 55;
		
			try {
				FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFIntArrayTest001.sf");
				SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
				
				intArray1.writeOnStream(outputStream);
				
				output.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFIntArrayTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			intArray1.readFromStream(inputStream);
			
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			assertArrayEquals(expectedArray1, intArray1.getIntValues());
			
			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;
			
			System.out.println("Durata Test B: "+duration+"ms");
	
	}
	
	@Test
	public void testC(){
		
		long startTime = System.nanoTime();
		
		int[] expectedArray1 = {0,1,2,3,4};
		int[] expectedArray2 = {0,-1,-2,-3,128};
		
			for (int i = 0; i < intArray1.getIntValues().length; i++) {
				
				intArray1.getIntValues()[i] = i;	
			}
			
		System.out.println(intArray1.toStringValue());
		assertArrayEquals(expectedArray1, intArray1.getIntValues());
		
		intArray1.getIntValues()[1] = -1;
		intArray1.getIntValues()[2] = -2;
		intArray1.getIntValues()[3] = -3;
		intArray1.getIntValues()[4] = 128;
		
		System.out.println(intArray1.toStringValue());
		assertArrayEquals(expectedArray2, intArray1.getIntValues());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test C: "+duration+"ms");
		
	}
	
	@Test
	public void testD(){
		
		long startTime = System.nanoTime();
		
		SFIntArray intArray3 = new SFIntArray(55);
		SFIntArray intArray4 = new SFIntArray(11);
		System.out.println(intArray3.toStringValue());
		System.out.println(intArray4.toStringValue());
		
		intArray3 = intArray4.copyDataObject();
		System.out.println(intArray3.toStringValue());
		
		intArray3.getIntValues()[0] = 10;
		intArray3.getIntValues()[4] = 100;
		System.out.println(intArray3.toStringValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test D: "+duration+"ms");
		
	}
	
	@Test
	public void testE(){
		
		long startTime = System.nanoTime();
		
		System.out.println(intArray1.toStringValue());
		String value = "256,0,1,8,0";
		int[] expectedArray = {256,0,1,8,0};
		intArray1.setStringValue(value);
		System.out.println(intArray1.toStringValue());
		assertArrayEquals(expectedArray, intArray1.getIntValues());
		
		//String value2 = "256,0/1,8;0";     //Lanciano una Malformed Exception a causa del formato errato della stringa
		//intArray1.setStringValue(value2);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test E: "+duration+"ms");
	}

}
;