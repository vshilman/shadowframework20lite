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
		
	}
	
	@Test
	public void testB(){
		
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
	
	}
	
	@Test
	public void testC(){
		
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
		
	}
	
	@Test
	public void testD(){
		
		SFIntArray intArray3 = new SFIntArray(55);
		SFIntArray intArray4 = new SFIntArray(11);
		System.out.println(intArray3.toStringValue());
		System.out.println(intArray4.toStringValue());
		
		intArray3 = intArray4.copyDataObject();
		System.out.println(intArray3.toStringValue());
		
		intArray3.getIntValues()[0] = 10;
		intArray3.getIntValues()[4] = 100;
		System.out.println(intArray3.toStringValue());
		
	}
	
	@Test
	public void testE(){
		
		System.out.println(intArray1.toStringValue());
		String value = "256,0,1,8,0";
		int[] expectedArray = {256,0,1,8,0};
		intArray1.setStringValue(value);
		System.out.println(intArray1.toStringValue());
		assertArrayEquals(expectedArray, intArray1.getIntValues());
		
		//String value2 = "256,0/1,8;0";     //Lanciano una Malformed Exception a causa del formato errato della stringa
		//intArray1.setStringValue(value2);
	}

}
;